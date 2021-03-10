package application;
import business.*;
import facade.dto.AulaDTO;
import javafx.util.Pair;

import java.util.*;
import javax.ejb.Stateless;
import javax.ejb.EJB;

@Stateless
public class VisualizarService {
	@EJB
    private VisualizarOcupacaoDeInstalacaoHandler visualizarHandler;

    /*public VisualizarService(VisualizarOcupacaoDeInstalacaoHandler visualizarHandler){
        this.visualizarHandler = visualizarHandler;
    }*/

    public List<Pair<AulaDTO, Pair<Date,Date>>> verInstalacao(String nome,Date data){
        return this.visualizarHandler.verInstalacao(nome, data);
    }
}