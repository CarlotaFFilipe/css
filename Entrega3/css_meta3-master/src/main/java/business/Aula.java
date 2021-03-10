package business;
import business.*;
import java.util.*;
import javafx.util.Pair; 
import java.text.*;

public class Aula{
    private Date hora_inicio;
    private List<String> dias;
    private String nome;
    private boolean estado;
    private Date duracao;
    private int max_alunos;
    private Modalidade mod;

    private Instalacao inst;
    private Pair<Date, Date> datas;
    private List<Date> todosDias;
    private List<Utente> ute;

    public Aula(Date hora_inicio, List<String> dias, String nome, Date duracao, Modalidade mod) {
        this.hora_inicio = hora_inicio;
        this.dias = dias;
        this.nome = nome;
        this.duracao = duracao;
        this.mod = mod;
        this.estado = false;
        this.ute= new ArrayList<>();

    }

    public Date getDuracao(){
        return this.duracao;
    }
    
    public Modalidade getModalidade() {
        return this.mod;
    }

    public Instalacao getInstalacao() {
        return this.inst;
    }
    public int getMaxAlunos() {
    	return this.max_alunos;
    }
    public List<Utente> getUtentes(){
    	return this.ute;
    }

    public void putUtente(Utente utente){
        this.ute.add(utente);
    }
    public Pair<Date, Date> getDatas() {
        return this.datas;
    }

    public boolean getEstado() {
        return this.estado;
    }

    public List<String> getDias(){
        return this.dias;
    }
    public void alteraEstado(boolean estado) {
        this.estado = estado;
    }

    public List<Date> getTodosOsDias(){
        return this.todosDias;
    }

    public Date getHoraInicio(){
        return this.hora_inicio;
    }

   
    public Date getHoraFinal(){
        return addHoursTo (this.duracao.getHours(),this.duracao.getMinutes());
    }
    
    private Date addHoursTo(int hours,int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.hora_inicio);
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
        this.max_alunos = nMaxAl;
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
        sb.append("Maximo de alunos:" + this.max_alunos+ ";\n");
        return sb.toString();
    }


}