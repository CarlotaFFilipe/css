package facade.handlers;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import facade.dto.AulaDTO;
import facade.dto.ModalidadeDTO;

@Remote
public interface ICriarAulaServiceRemote {
	
	public List<ModalidadeDTO> criarAula();

    public AulaDTO especificaAula(String mod, String n, List<String> dias, Date horaI, Date dur);
}
