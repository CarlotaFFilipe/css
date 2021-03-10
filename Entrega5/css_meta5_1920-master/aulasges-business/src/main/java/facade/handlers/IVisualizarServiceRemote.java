package facade.handlers;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import business.VisualizarOcupacaoDeInstalacaoHandler;
import facade.dto.AulaDTO;
import javafx.util.Pair;

public interface IVisualizarServiceRemote{
    public List<Pair<AulaDTO, Pair<Date,Date>>> verInstalacao(String nome,Date data);
}