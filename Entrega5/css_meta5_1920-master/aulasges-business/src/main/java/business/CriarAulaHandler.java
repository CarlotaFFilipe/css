package business;
import java.util.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import business.Modalidade;
import facade.dto.AulaDTO;
import facade.dto.ModalidadeDTO;

@Stateless
public class CriarAulaHandler{
	@EJB
    private CatalogoAulas catAu;
	@EJB
    private CatalogoModalidades catMod;
    //private EntityManagerFactory emf;
    //private EntityManager em;


    /*public CriarAulaHandler(EntityManagerFactory emf){
    	 this.emf = emf;
    }*/

    public List<ModalidadeDTO> criarAula(){
    	//this.em = emf.createEntityManager();
    	//CatalogoModalidades catMod = new CatalogoModalidades(this.em);
        //System.out.println(this.catMod.getModalidades().toString());
    	List<ModalidadeDTO> result = new ArrayList<>();
		List<Modalidade> old = this.catMod.getModalidades();
		for(Modalidade mod : old)
			result.add(new ModalidadeDTO(mod.getName(),mod.getDuracao(), mod.getPreco()));
		return result;
    }

    public AulaDTO especificaAula(String mod, String n, List<String> dias, Date horaI, Date dur){
    	//CatalogoModalidades catMod = new CatalogoModalidades(this.em);
    	//CatalogoAulas catAu = new CatalogoAulas(this.em);
    	Modalidade modalidade = this.catMod.getMod(mod);
        if(modalidade == null){
            System.out.println("Nao existe modalidade");
            return null;
        }
        //em.getTransaction().begin();
        this.catAu.putAula(horaI, dias, n, dur, modalidade);
        return new AulaDTO(horaI,dias,n,dur,modalidade);
        //em.getTransaction().commit();
    }
}