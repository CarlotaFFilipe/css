package business;
import business.*;
import java.util.*;
import javafx.util.Pair; 
import java.text.*;

public class Instalacao{
    private int capacidade;
    private List<Modalidade> modPermitidas;
    private String nome;
    private List<Aula> aulas;


    public Instalacao(int capacidade, String nome, List<Modalidade> modPermitidas){
        this.capacidade=capacidade;
        this.nome= nome;
        this.modPermitidas = modPermitidas;
        this.aulas = new ArrayList<>();
    }


    public List<Modalidade> getModalidadesPermitidas(){
        return this.modPermitidas;
    }

    public String getName(){
        return this.nome;
    }

    public void putAula(Aula aula){
        aulas.add(aula);
    }

    public int getCapacidade(){
        return this.capacidade;
    }

    public List<Pair<Aula, Pair<Date,Date>>> getAulaCData(Date data){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        List<Pair<Aula, Pair<Date,Date>>> novaLista = new ArrayList<Pair<Aula, Pair<Date,Date>>>();
        for(Aula aula:this.aulas){
            for(Date data1: aula.getTodosOsDias()) {
                if((data1.getDate()==(data.getDate())) && data1.getMonth()==data.getMonth()){

                    Pair<Date,Date> novoPar= new Pair<Date,Date>(aula.getHoraInicio(), aula.getHoraFinal());
                    Pair<Aula, Pair<Date,Date>> no = new  Pair<Aula, Pair<Date,Date>> (aula, novoPar);
                    novaLista.add(no);
                    sb.append("("+ aula.getName()+", "+ aula.getHoraInicio().toLocaleString().split(" ")[1]+" - " + aula.getHoraFinal().toLocaleString().split(" ")[1]+") ");
                    
                }
            }
        }
        sb.append("]");
        System.out.println(sb.toString());
        
        
		return novaLista;
    }
    
    public boolean instalacaoNOcupada(List<Date> datas,Date horaInicio, Date horaFinal){
    	if(this.aulas.isEmpty()) {
    		System.out.println("Nao ha aulas nesta instalacao");
    		return true;
    	}
        for(Aula aula : this.aulas)
            for(Date data: datas){
                for(Date data1: aula.getTodosOsDias()){
                    if((data1.getDate()==(data.getDate())) && data1.getMonth()==data.getMonth()){
                    	if((aula.getHoraInicio().before(horaInicio) && horaInicio.before(aula.getHoraFinal()) )|| aula.getHoraInicio().equals(horaInicio)){
                            return false;
                        }else if((horaFinal.before(aula.getHoraFinal()) &&  aula.getHoraInicio().before(horaFinal)) || aula.getHoraFinal().equals(horaFinal))
                            return false;
                    }
                }    
            }
        return true;
    }

    public String toString() {
        StringBuilder sb= new StringBuilder();
        sb.append("Nome:" + this.nome+ ";\n");
        for(int i=0; i<modPermitidas.size();i++)
            sb.append("modalidade perm: "+ modPermitidas.get(i).toString()+";\n");
        for(int i=0; i<aulas.size();i++)
            sb.append("aulas: "+ aulas.get(i).toSring()+"\n");
        sb.append("Capacidade: " + this.capacidade+ ";\n");
    	return sb.toString();
    }


        
}