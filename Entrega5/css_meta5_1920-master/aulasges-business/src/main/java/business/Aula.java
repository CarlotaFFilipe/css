package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
//import main.java.dbutils.*;
import javax.persistence.*;

import javafx.util.Pair;

/**
 * Entity implementation class for Entity: Aula
 *
 */
@Entity
@NamedQuery(name=Aula.FIND_BY_NOME, query="SELECT a FROM Aula a WHERE a.nome = :" + 
		Aula.STRING_NOME)


public class Aula implements Serializable {
	// Named query name constants
	public static final String FIND_BY_NOME = "Aula.findByNome";
	public static final String STRING_NOME= "nome";
	
	
	
	
	
	private List<String> dias;
	@Convert(converter = LocalTimeAttributeConverter.class)
	private Date horaInicio;   
	@Id
	private String nome;
	private boolean estado;
	@Convert(converter = LocalTimeAttributeConverter.class)
	private Date duracao;
	private int maxAlunos;
	@OneToOne
	private Modalidade mod;
	@OneToOne
	private Instalacao inst;
	private Pair<Date,Date> datas;
	private List<Date> todosDias;
	@OneToMany
	private List<Utente> ute;
	private static final long serialVersionUID = 1L;

	Aula(){}
	public Aula(Date horaInicio, List<String> dias, String nome, Date duracao, Modalidade mod) {
        this.horaInicio = horaInicio;
        this.dias = dias;
        this.nome = nome;
        this.duracao = duracao;
        this.mod = mod;
        this.estado = false;
        this.ute= new ArrayList<>();

    }
    
    public Modalidade getModalidade() {
        return this.mod;
    }

    public Instalacao getInstalacao() {
        return this.inst;
    }
    public int getMaxAlunos() {
    	return this.maxAlunos;
    }
    public List<Utente> getUtentes(){
    	return this.ute;
    }

    public void putUtente(Utente utente){
        this.ute.add(utente);
    }

    public void alteraEstado(boolean estado) {
        this.estado = estado;
    }

    public List<Date> getTodosOsDias(){
        return this.todosDias;
    }

    public Date getHoraInicio(){
        return this.horaInicio;
    }

   
    public Date getHoraFinal(){
        return addHoursTo (this.duracao.getHours(),this.duracao.getMinutes());
    }
    
    private Date addHoursTo(int hours,int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.horaInicio);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        calendar.add(Calendar.MINUTE, minutes);
        Date dd= calendar.getTime();
        return calendar.getTime();
    }
 

    public String getName(){
        return this.nome;
    }

    public void removeUtente(Utente u){
        this.ute.remove(u);
    }

    public void ativar(Instalacao ins, Pair<Date, Date> datas, int nMaxAl) {
        this.inst = ins;
        this.datas = datas;
        this.maxAlunos = nMaxAl;
        alteraEstado(true);
        divide(datas.getKey(),datas.getValue());
    }
    


    public List<Date> divideEmDias(Date startDate, Date endDate) {
        List<Date> datesInRange = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);
        Calendar weekDay = new GregorianCalendar();
        while (calendar.before(endCalendar)) {
            Date result = calendar.getTime();
            weekDay.setTime(result);
            if (dias.contains(getDayOfWeek(weekDay.get(Calendar.DAY_OF_WEEK)))) {
                datesInRange.add(result);
            }
            calendar.add(Calendar.DATE, 1);
        }
        return datesInRange;
      }

      public void divide(Date startDate, Date endDate){
        todosDias=divideEmDias(startDate, endDate);
      }

      

    private String getDayOfWeek(int value){
        String day = "";
        switch(value){
        case 1:
            day="Sunday";
            break;
        case 2:
            day="Monday";
            break;
        case 3:
            day="Tuesday";
            break;
        case 4:
            day="Wednesday";
            break;
        case 5:
            day="Thursday";
            break;
        case 6:
            day="Friday";
            break;
        case 7:
            day="Saturday";
            break;
        }
        return day;
    }

    public String toSring(){
        StringBuilder sb= new StringBuilder();
        sb.append("Nome:" + this.nome + ";\n");
        sb.append("Modalidade:" + this.mod+ ";\n");
        sb.append("Estado:" + this.estado+ ";\n");
        sb.append("Maximo de alunos:" + this.maxAlunos+ ";\n");
        return sb.toString();
    }
    
	public List<String> getDias() {
		return this.dias;
	}

	public void setDias(List<String> dias) {
		this.dias = dias;
	}  

	public void sethoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}   
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}   
	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}   
	public Date getDuracao() {
		return this.duracao;
	}

	public void setDuracao(Date duracao) {
		this.duracao = duracao;
	}

	public void setmaxAlunos(int maxAlunos) {
		this.maxAlunos = maxAlunos;
	}

	public void setMod(Modalidade mod) {
		this.mod = mod;
	}

	public void setInst(Instalacao inst) {
		this.inst = inst;
	}   
	public Pair<Date,Date> getDatas() {
		return this.datas;
	}

	public void setDatas(Pair<Date,Date> datas) {
		this.datas = datas;
	}

	public void setTodosDias(List<Date> todosDias) {
		this.todosDias = todosDias;
	}

	public void setUte(List<Utente> ute) {
		this.ute = ute;
	}
   
}

