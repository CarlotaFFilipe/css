package business;
import javafx.util.Pair; 
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import business.Aula;
import business.Instalacao;
import facade.dto.AulaDTO;
import facade.dto.InstalacaoDTO;
import facade.dto.ModalidadeDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.text.*;

@Stateless
public class AtivarAulaHandler{
	private Aula aula;
	@EJB
	private CatalogoAulas catAu;
	@EJB
	private CatalogoInstalacoes catIns;
	/*
    private EntityManagerFactory emf;
    private EntityManager em;*/

	/*public AtivarAulaHandler(EntityManagerFactory emf) {
        this.emf = emf;
    }*/

	public List<InstalacaoDTO> ativarAula(){
		//em = emf.createEntityManager();
		//CatalogoInstalacoes catInst = new CatalogoInstalacoes();
		//System.out.println(this.catIns.getInstalacoes().toString());
		List<InstalacaoDTO> result = new ArrayList<>();
		List<Instalacao> old = this.catIns.getInstalacoes();
		for(Instalacao inst : old)
			result.add(new InstalacaoDTO(inst.getCapacidade(),inst.getModalidadesPermitidas(),inst.getName()));
		return result;
	}

	public AulaDTO informacoesAula(String nomeAula,String ins, Pair<Date, Date> datas, int nMaxAl){
		//CatalogoAulas catAu = new CatalogoAulas(this.em);
		//CatalogoInstalacoes catIns= new CatalogoInstalacoes(this.em);
		this.aula = this.catAu.getAula(nomeAula);
		if(this.aula == null){
			System.out.println("Nao existe aula com este nome.");
			return null;
		}else if(aula.getEstado()) {
			System.out.println("Aula ja foi ativada.");
			return null;
		}
		Instalacao inst;
		if((inst = this.catIns.getInstalacao(ins) )== null){
			System.out.println("Instalacao invalida");
			return null;
		}

		if(!inst.getModalidadesPermitidas().contains(this.aula.getModalidade())){
			System.out.println("Instalacao nao permite essa modalidade");
			return null;
		}

		Date dNow = DataCorrente.getDataCorrente();
		SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
		ft.format(dNow);

		Boolean b=datas.getKey().getDate()==dNow.getDate() && datas.getKey().getMonth()==dNow.getMonth() && datas.getKey().getYear()==dNow.getYear();



		if(datas.getKey().before(dNow) && !b){
			System.out.println("O par de datas nao define um periodo no futuro");
			return null;
		}
		if(nMaxAl > inst.getCapacidade()){
			System.out.println("Numero de participantes e superior a capacidade da instalacao");
			return null;
		}

		List<Date> nh = this.aula.divideEmDias(datas.getKey(), datas.getValue());
		if(!inst.instalacaoNOcupada(nh, this.aula.getHoraInicio(),this.aula.getHoraFinal())){
			System.out.println("Instalacao indisponivel nesta hora");
			return null;
		}
		//em.getTransaction().begin();
		this.aula.ativar(inst, datas, nMaxAl);
		this.catIns.putAula(inst, aula);
		//em.getTransaction().commit();
		System.out.println("A aula foi bem ativada!");
		return new AulaDTO(this.aula.getHoraInicio(),this.aula.getDias(),this.aula.getNome(),this.aula.getDuracao(),this.aula.getModalidade());
	}

	public void DivideAula(Date startDate, Date endDate){
		System.out.println(this.aula.divideEmDias(startDate, endDate).toString());
	}

}