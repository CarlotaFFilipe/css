package facade.handlers;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import facade.dto.AulaDTO;
import facade.dto.InstalacaoDTO;
import javafx.util.Pair;

@Remote
public interface IAtivarAulaServiceRemote {
	
	public List<InstalacaoDTO> ativarAula();
	public AulaDTO informacoesAula(String nomeAula,String ins, Pair<Date, Date> datas, int nMaxAl);

}
