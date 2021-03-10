package facade.handlers;


import java.util.List;

import javax.ejb.Remote;

import facade.dto.AulaDTO;
import facade.dto.ModalidadeDTO;


@Remote
public interface IInscreverAulaServiceRemote {

	public List<ModalidadeDTO> inscreverAula();

    public List<AulaDTO> inscricao(String mod,String tipo);

    public AulaDTO indicaAula(String aula, String numUtente);
}
