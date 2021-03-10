package business;
import java.util.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import business.Aula;
import business.Avulso;
import business.Inscricao;
import business.Modalidade;
import business.Regular;
import business.Utente;
import facade.dto.AulaDTO;
import facade.dto.InscricaoDTO;
import facade.dto.ModalidadeDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Stateless
public class InscreverAulaHandler{
    private Aula aula;
	@EJB
    private CatalogoAulas catAu;
	@EJB
    private CatalogoUtentes catUt;
	@EJB
    private CatalogoModalidades catMod;
    private Inscricao inscricao;
    private boolean ehRegular;
    private List<AulaDTO> aulasInscrever;
    
    
    /*
    private EntityManagerFactory emf;
    private EntityManager em;


    public InscreverAulaHandler(EntityManagerFactory emf) {
       this.emf=emf;
    }*/

	public List<ModalidadeDTO> inscreverAula(){
		//this.em= emf.createEntityManager();
		//CatalogoModalidades catMod = new CatalogoModalidades(this.em);
        //System.out.println(this.catMod.getModalidades().toString());
		List<ModalidadeDTO> result = new ArrayList<>();
		List<Modalidade> old = this.catMod.getModalidades();
		for(Modalidade mod : old)
			result.add(new ModalidadeDTO(mod.getName(),mod.getDuracao(), mod.getPreco()));
		return result;
    }

	public List<AulaDTO> inscricao(String mod, String tipo) {
		//CatalogoModalidades catMod = new CatalogoModalidades(this.em);
		//CatalogoAulas catAu = new CatalogoAulas(this.em);
		
        Modalidade modalidade= this.catMod.getMod(mod);
        if(!tipo.equals("Avulso")  && !tipo.equals("Regular")){
            System.out.println("Inscricao invalida");
            return null;
        }
        if(tipo.equals("Regular")){
            this.aulasInscrever = new ArrayList<>();
            this.ehRegular= true;
            List<Aula> aulas = this.catAu.getAulas();
            StringBuilder sb= new StringBuilder();
            sb.append("[");
            for(Aula au: aulas){
                if(au.getModalidade().equals(modalidade) && au.getEstado()){
                    if(au.getUtentes().size() < au.getMaxAlunos()){
                    	//em.getTransaction().begin();
                        this.aulasInscrever.add(new AulaDTO(au.getHoraInicio(),au.getDias(),au.getNome(),au.getDuracao(),au.getModalidade()));
                        //em.getTransaction().commit();
                        sb.append("( " + au.getName() + ", "+ au.getDias().toString()+", "+ (au.getMaxAlunos()-au.getUtentes().size()) +" )");
                    }
                }
            }
           
            sb.append("]");
            //System.out.println(sb);
            return this.aulasInscrever;
            
        }else{
            //Avulso
            this.aulasInscrever = new ArrayList<>();
            this.ehRegular= false;
            List<Aula> aulas = this.catAu.getAulas();
            StringBuilder sb= new StringBuilder();
            sb.append("[");
            for(Aula au: aulas){
                if(au.getModalidade().equals(modalidade) && au.getEstado()){
                    if(au.getUtentes().size() < au.getMaxAlunos() && checkHours(au.getTodosOsDias())){
                    	//em.getTransaction().begin();
                        //this.aulasInscrever.add(au);
                    	this.aulasInscrever.add(new AulaDTO(au.getHoraInicio(),au.getDias(),au.getNome(),au.getDuracao(),au.getModalidade()));
                        //em.getTransaction().commit();
                        sb.append("( " + au.getName() + ", <"+ au.getHoraInicio().toLocaleString().split(" ")[0]+", "+ au.getHoraFinal().toLocaleString().split(" ")[0] +">, "+ (au.getMaxAlunos()-au.getUtentes().size()) +" )");
                    }
                }
            }
            sb.append("]");
            //System.out.println(sb);
            return this.aulasInscrever;
        }
            
	}

	public AulaDTO indicaAula(String aula2, String numUtente) {
		//CatalogoAulas catAu = new CatalogoAulas(this.em);  
		//CatalogoUtentes catUt = new CatalogoUtentes(this.em);
        Utente u = this.catUt.getUtente(numUtente);
        if(u == null){
            System.out.println("Utente nao inscrito (numero utente)");
            return null;
        }

        this.aula = this.catAu.getAula(aula2); 
        if(this.aula == null){
            System.out.println("Aula invalida (para o utente)");
            return null;
        }
        
        if(this.aula.getMaxAlunos()-this.aula.getUtentes().size() == 0){
            System.out.println("Nao ha vagas nesta aula");
            return null;
        }

        if(this.aulasInscrever.isEmpty()){
            System.out.println("Nao ha aulas para se inscrever");
            return null;
        }

        if(this.ehRegular){
        	//em.getTransaction().begin();
            this.aula.putUtente(u);
            //em.getTransaction().commit();
            double precoHora = this.aula.getModalidade().getPreco();
            //buscar as horas e minutos
            String[] horaEMin = this.aula.getDuracao().toLocaleString().split(" ")[1].split(":");
            int hora = Integer.parseInt(horaEMin[0]);
            double min = Double.parseDouble(horaEMin[1])/60;
            
            int nSessoes = this.aula.getDias().size();
            //so duas casas decimais
            BigDecimal bd = new BigDecimal(precoHora*(hora+min)*nSessoes*4).setScale(2, RoundingMode.HALF_UP);
            double newInput = bd.doubleValue();
            //em.getTransaction().begin();
            this.inscricao = new Regular(newInput);
            //em.persist(inscricao);
            u.putIns(this.inscricao);
            String aux = DataCorrente.getDataCorrenteSoDia().toLocaleString().split(" ")[0];            
            Date newDate = parseDateCal(aux);
            //System.out.println("            "+newDate.toLocaleString());
            this.inscricao.putAulaEData(this.aula, newDate);
            //em.getTransaction().commit();
            //System.out.println("Inscricao regular feita com o custo: " + newInput+"�");
            return new AulaDTO(this.aula.getHoraInicio(),this.aula.getDias(),this.aula.getNome(),this.aula.getDuracao(),this.aula.getModalidade());
        }else{
            this.aula.putUtente(u);
            double precoHora = this.aula.getModalidade().getPreco();
            //buscar as horas e minutos
            String[] horaEMin = this.aula.getDuracao().toLocaleString().split(" ")[1].split(":");
            int hora = Integer.parseInt(horaEMin[0]);
            double min = Double.parseDouble(horaEMin[1])/60;
            BigDecimal bd = new BigDecimal(precoHora*(hora+min)).setScale(2, RoundingMode.HALF_UP);
            double newInput = bd.doubleValue();
            //em.getTransaction().begin();
            this.inscricao = new Avulso(newInput);
            //em.persist(inscricao);
            u.putIns(this.inscricao);
            String aux = DataCorrente.getDataCorrente().toLocaleString().split(" ")[0];
            Date newDate =InscreverAulaHandler.parseDateCal(aux);
            this.inscricao.putAulaEData(this.aula,  newDate);
            //em.getTransaction().commit();
            //System.out.println("Inscricao avulso feita com o custo: " + newInput+"�");
            return new AulaDTO(this.aula.getHoraInicio(),this.aula.getDias(),this.aula.getNome(),this.aula.getDuracao(),this.aula.getModalidade());
        }
        
    }
    
    public boolean checkHours(List<Date> datas){
        Date d24 = new Date();
        Calendar c = Calendar.getInstance(); 
        c.setTime(d24); 
        c.add(Calendar.DATE, 1);
        d24 = c.getTime();
        for(Date data: datas){
            if(data.before(d24)){
                return true;
            }
        }
        return false;
    }
    
    public static Date parseDateCal(String date) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            return null;
        }
     }

}