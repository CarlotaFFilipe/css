package application;
import business.*;
import facade.dto.AulaDTO;
import facade.dto.ModalidadeDTO;

import java.util.*;
import javax.ejb.Stateless;
import javax.ejb.EJB;

@Stateless
public class CriarAulaService {
	
	@EJB
    private CriarAulaHandler criarHandler;

    /*public CriarAulaService(CriarAulaHandler criarHandler){
        this.criarHandler = criarHandler;
    }*/

    public List<ModalidadeDTO> criarAula(){
        return this.criarHandler.criarAula();
    }

    public AulaDTO especificaAula(String mod, String n, List<String> dias, Date horaI, Date dur){
        return this.criarHandler.especificaAula(mod, n, dias, horaI, dur);

    }
}