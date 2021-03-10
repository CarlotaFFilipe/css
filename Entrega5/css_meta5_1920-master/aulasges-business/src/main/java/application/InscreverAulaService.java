package application;
import business.*;
import facade.dto.AulaDTO;
import facade.dto.ModalidadeDTO;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless
public class InscreverAulaService {
	@EJB
    private InscreverAulaHandler inscreverHandler;

   /* public InscreverAulaService(InscreverAulaHandler inscreverHandler){
        this.inscreverHandler = inscreverHandler;
    }*/

    public List<ModalidadeDTO> inscreverAula(){
        return this.inscreverHandler.inscreverAula();
    }


    public List<AulaDTO> inscricao(String mod,String tipo){
        return this.inscreverHandler.inscricao(mod,tipo);
    }

    public AulaDTO indicaAula(String aula, String numUtente){
        return this.inscreverHandler.indicaAula(aula,numUtente);
    }
}