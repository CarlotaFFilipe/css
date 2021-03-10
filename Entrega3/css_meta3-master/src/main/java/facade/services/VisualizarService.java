package facade.services;
import business.*;
import java.util.*;

public class VisualizarService {

    private VisualizarOcupacaoDeInstalacaoHandler visualizarHandler;

    public VisualizarService(VisualizarOcupacaoDeInstalacaoHandler visualizarHandler){
        this.visualizarHandler = visualizarHandler;
    }

    public void verInstalacao(String nome,Date data){
        visualizarHandler.verInstalacao(nome, data);
    }
}