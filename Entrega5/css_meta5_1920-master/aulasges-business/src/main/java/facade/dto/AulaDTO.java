package facade.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import business.Modalidade;

public class AulaDTO implements Serializable{
	
	private static final long serialVersionUID = -4087131153704256744L;
	private String nome;
	private Date horaInicio;
	private List<String> dias;
	private Date duracao;
	private Modalidade mod;
	
	public AulaDTO(Date horaInicio, List<String> dias, String nome, Date duracao, Modalidade mod) {
		this.horaInicio = horaInicio;
        this.dias = dias;
        this.nome = nome;
        this.duracao = duracao;
        this.mod = mod;
    }
	
	public String getNome() {
		return this.nome;
	}
	
	public Date getHoraInicio() {
		return this.horaInicio;
	}
	
	public Modalidade getModalidade() {
		return this.mod;
	}
	
	public Date getDuracao() {
		return this.duracao;
	}
	
	public List<String> getDias(){
		return this.dias;
	}

}
