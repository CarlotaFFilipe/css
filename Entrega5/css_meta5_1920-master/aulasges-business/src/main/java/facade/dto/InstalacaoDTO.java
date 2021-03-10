package facade.dto;

import java.io.Serializable;
import java.util.List;


import business.Modalidade;

public class InstalacaoDTO implements Serializable{
	private static final long serialVersionUID = -4087131153704256744L;
	private int capacidade;
	private List<Modalidade> modPermitidas;   
	private String nome;
	
	public InstalacaoDTO(int capacidade, List<Modalidade> modPermitidas,String nome) {
		this.capacidade=capacidade;
        this.nome= nome;
        this.modPermitidas = modPermitidas;
	}
	
	public int getCapacidade() {
		return this.capacidade;
	}

	public List<Modalidade> getModalidadesPermitidas(){
        return this.modPermitidas;
    }

    public String getNome(){
        return this.nome;
    }
}
