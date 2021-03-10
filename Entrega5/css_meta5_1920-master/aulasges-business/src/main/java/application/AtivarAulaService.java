package application;
import business.*;
import facade.dto.AulaDTO;
import facade.dto.InstalacaoDTO;

import java.util.*;
import javafx.util.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless
public class AtivarAulaService{
	@EJB
    private AtivarAulaHandler ativarHandler;

   /* public AtivarAulaService(AtivarAulaHandler ativarHandler){
        this.ativarHandler = ativarHandler;
    }*/

    public List<InstalacaoDTO> ativarAula(){
        return this.ativarHandler.ativarAula();
    }

    public AulaDTO informacoesAula(String nomeAula,String ins, Pair<Date, Date> datas, int nMaxAl){
        return this.ativarHandler.informacoesAula(nomeAula, ins, datas, nMaxAl);
    }
}