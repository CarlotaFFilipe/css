package business;
import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class InscreverAulaHandler{
    private Aula aula;
    private CatalogoAulas catAu;
    private CatalogoUtentes catUt;
    private CatalogoModalidades catMod;
    private Inscricao inscricao;
    private boolean ehRegular;
    private List<Aula> aulasInscrever;

    public InscreverAulaHandler(CatalogoAulas catAu,CatalogoUtentes catUt,CatalogoModalidades catMod) {
        this.catAu = catAu;
        this.catUt = catUt;
        this.catMod = catMod;
    }

	public void inscreverAula(){
        System.out.println(catMod.getModalidades().toString());
    }

	public void inscricao(String mod, String tipo) {
        Modalidade modalidade= catMod.getMod(mod);
        if(!tipo.equals("Avulso")  && !tipo.equals("Regular")){
            System.out.println("Inscricao invalida");
            return;
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
                        this.aulasInscrever.add(au);
                        sb.append("( " + au.getName() + ", "+ au.getDias().toString()+", "+ (au.getMaxAlunos()-au.getUtentes().size()) +" )");
                    }
                }
            }
            sb.append("]");
            System.out.println(sb);
            
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
                        this.aulasInscrever.add(au);
                        sb.append("( " + au.getName() + ", <"+ au.getHoraInicio().toLocaleString().split(" ")[0]+", "+ au.getHoraFinal().toLocaleString().split(" ")[0] +">, "+ (au.getMaxAlunos()-au.getUtentes().size()) +" )");
                    }
                }
            }
            sb.append("]");
            System.out.println(sb);
        }
            
        
	}

	public void indicaAula(String aula2, String numUtente) {      
        Utente u = this.catUt.getUtente(numUtente);
        if(u == null){
            System.out.println("Utente nao inscrito (numero utente)");
            return;
        }

        this.aula = this.catAu.getAula(aula2); 
        if(this.aula == null){
            System.out.println("Aula invalida (para o utente)");
            return;
        }
        
        if(this.aula.getMaxAlunos()-this.aula.getUtentes().size() == 0){
            System.out.println("Nao ha vagas nesta aula");
            return;
        }

        if(this.aulasInscrever.isEmpty()){
            System.out.println("Nao ha aulas para se inscrever");
            return;
        }

        if(this.ehRegular){
            this.aula.putUtente(u);
            double precoHora = this.aula.getModalidade().getPreco();
            //buscar as horas e minutos
            String[] horaEMin = this.aula.getDuracao().toLocaleString().split(" ")[1].split(":");
            int hora = Integer.parseInt(horaEMin[0]);
            double min = Double.parseDouble(horaEMin[1])/60;
            
            int nSessoes = this.aula.getDias().size();
            //so duas casas decimais
            BigDecimal bd = new BigDecimal(precoHora*(hora+min)*nSessoes*4).setScale(2, RoundingMode.HALF_UP);
            double newInput = bd.doubleValue();
            this.inscricao = new Regular(newInput);
            u.putIns(this.inscricao);
            this.inscricao.putAulaEData(this.aula, new Date());
            System.out.println("Inscricao regular feita com o custo: " + newInput+"€");
        }else{
            this.aula.putUtente(u);
            double precoHora = this.aula.getModalidade().getPreco();
            //buscar as horas e minutos
            String[] horaEMin = this.aula.getDuracao().toLocaleString().split(" ")[1].split(":");
            int hora = Integer.parseInt(horaEMin[0]);
            double min = Double.parseDouble(horaEMin[1])/60;
            BigDecimal bd = new BigDecimal(precoHora*(hora+min)).setScale(2, RoundingMode.HALF_UP);
            double newInput = bd.doubleValue();
            this.inscricao = new Avulso(newInput);
            u.putIns(this.inscricao);
            this.inscricao.putAulaEData(this.aula, new Date());
            System.out.println("Inscricao avulso feita com o custo: " + newInput+"€");
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

}