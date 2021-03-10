package client;
import java.util.*;

import business.DataCorrente;
import facade.startup.AulaGes;
import javafx.util.*;
import application.AtivarAulaService;
import application.CriarAulaService;
import application.InscreverAulaService;
import application.VisualizarService;
import java.text.*;


public class Client {
    private AtivarAulaService ativarAulaService;
    private CriarAulaService criarAulaService;
    private InscreverAulaService inscreverAulaService;
    private VisualizarService visualizarService; 
    
    public Client(AtivarAulaService ativarAulaService, CriarAulaService criarAulaService,
                    InscreverAulaService inscreverAulaService, VisualizarService visualizarService){
            this.ativarAulaService = ativarAulaService;
            this.criarAulaService = criarAulaService;
            this.inscreverAulaService = inscreverAulaService;
            this.visualizarService = visualizarService;            
        }



    public void createEverythingAndSomeMore(){
        //-----------------------------DIAS DA SEMANA-----------------------------------------------
        List<String> diaDaSemana1= new ArrayList<>();
        diaDaSemana1.add("Tuesday");
        diaDaSemana1.add("Thursday");

        List<String> diaDaSemana2= new ArrayList<>();
        diaDaSemana2.add("Monday");
        diaDaSemana2.add("Wednesday");
        diaDaSemana2.add("Friday");

        //-----------------------------HORAS DE INICIO--------------------------------------------
        Date horaIni1 = parseDateTime("09:15");
        Date horaIni2 = parseDateTime("12:15");
        Date horaIni3 = parseDateTime("09:00");

        //---------------------------------DURACOES-------------------------------------------
        Date duracao1 = parseDateTime("00:55");
        Date duracao2 = parseDateTime("00:45");
        Date duracao3 = parseDateTime("00:50");

        //----------------------------INTERVALO DE DATAS--------------------------------------------
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //SERA QUE EH ASSIM
        Date dia1 = DataCorrente.getDataCorrente();
        System.out.println(dia1.toLocaleString());
        Date dia2 = parseDateCal("31/07/2021");
        Pair<Date, Date> datas1= new Pair<>(dia1, dia2);


        Date dt = new Date();
        Calendar c = Calendar.getInstance(); 
        c.setTime(dt); 
        c.add(Calendar.DATE, 1);
        dt = c.getTime();

        Date dia3 = parseDateCal(dateFormat.format(dt));

        //---------------------------------CRIAR AULAS----------------------------------------------
        //Aula 1: eh criada
        this.criarAulaService.criarAula();
        this.criarAulaService.especificaAula("Pilates", "PLT001", diaDaSemana1, horaIni1, duracao1);

        //Aula 2: eh criada 
        this.criarAulaService.criarAula();
        this.criarAulaService.especificaAula("Pilates", "PLT002", diaDaSemana1, horaIni2, duracao1);

        //Aula 3: eh criada         
        this.criarAulaService.criarAula();
        this.criarAulaService.especificaAula("Pilates", "PLT003", diaDaSemana2, horaIni2, duracao1);

        //Aula 4: nao eh criada ( duracao invalida)         
        this.criarAulaService.criarAula();
        this.criarAulaService.especificaAula("GAP", "GAP001", diaDaSemana2, horaIni3, duracao2);

        //Aula 5: eh criada         
        this.criarAulaService.criarAula();
        this.criarAulaService.especificaAula("GAP", "GAP001", diaDaSemana2, horaIni3, duracao3);

        //Aula 6: eh criada         
        this.criarAulaService.criarAula();
        this.criarAulaService.especificaAula("STEP", "STP001", diaDaSemana2, horaIni1, duracao2);

        //-------------------------------ATIVAR AULAS---------------------------------------------------

        //Ativar aula 1: eh ativada
        this.ativarAulaService.ativarAula();
        this.ativarAulaService.informacoesAula("PLT001", "Estudio 1", datas1, 2);
 
        //Ativar aula 2: eh ativada
        this.ativarAulaService.ativarAula();
        this.ativarAulaService.informacoesAula("PLT002", "Estudio 1", datas1, 2);
 
        //Ativar aula 5: nao eh ativada (hora final esta no meio de outra) PROFESSORES ERRADOS??
        this.ativarAulaService.ativarAula();
        this.ativarAulaService.informacoesAula("GAP001", "Estudio 1", datas1, 2);

        //Ativar aula 6: nao eh ativada (instalacao invalida)
        this.ativarAulaService.ativarAula();
        this.ativarAulaService.informacoesAula("STP001", "Estudio 2", datas1, 2);

        //Ativar aula 6: nao eh ativada (par de datas)
        this.ativarAulaService.ativarAula();
        this.ativarAulaService.informacoesAula("STP001", "Estudio 1", datas1, 2);
 
        //-------------------------------INSCREVER---------------------------------------------------------
        
        //Inscrever na aula 1: eh inscrito
        this.inscreverAulaService.inscreverAula();
        this.inscreverAulaService.inscricao("Pilates", "Regular");
        this.inscreverAulaService.indicaAula("PLT001", "1");

        //Inscrever na aula 2: eh inscrito
        this.inscreverAulaService.inscreverAula();
        this.inscreverAulaService.inscricao("Pilates", "Regular");
        this.inscreverAulaService.indicaAula("PLT002", "3");

        //Inscrever na aula 1: eh inscrito
        this.inscreverAulaService.inscreverAula();
        this.inscreverAulaService.inscricao("Pilates", "Regular");
        this.inscreverAulaService.indicaAula("PLT001", "2");

        //Inscrever na aula 2: eh inscrito
        this.inscreverAulaService.inscreverAula();
        this.inscreverAulaService.inscricao("Pilates", "Regular");
        this.inscreverAulaService.indicaAula("PLT002", "4");

        //Inscrever na aula 2: nao eh inscrito (nao ha aula possivel)
        this.inscreverAulaService.inscreverAula();
        this.inscreverAulaService.inscricao("Pilates", "Avulso");
        this.inscreverAulaService.indicaAula("PLT002", "5");

        //Inscrever na aula 2:
        this.inscreverAulaService.inscreverAula();
        this.inscreverAulaService.inscricao("Pilates", "Regular");
        this.inscreverAulaService.indicaAula("PLT002", "5");

        //-------------------------------------------VISUALIZAR-----------------------------------------------------
        //Visualizar instalacao Estudio 1
        this.visualizarService.verInstalacao("Estudio 1", dia3);
    }                  
                    
    public static void main(String[] args) {
		// Creates the application main object
		AulaGes app = new AulaGes();
		
		app.run();
		
		// Creates the services used for interaction with the application
		AtivarAulaService ativarAulaService = app.getAtivarAulaService();
        CriarAulaService criarAulaService = app.getCriarAulaService();
        InscreverAulaService inscreverAulaService = app.getInscreverAulaService();
        VisualizarService visualizarService= app.getVisualizarService();
		
		// Creates the simple interaction client and passes it the application services
		Client client = new Client(ativarAulaService, criarAulaService,inscreverAulaService,visualizarService);
        client.createEverythingAndSomeMore();
        //app.atualizaDados();
        app.close();
    }  
    
    public static Date parseDateTime(String date) {
        try {
            return new SimpleDateFormat("HH:mm").parse(date);
        } catch (ParseException e) {
            return null;
        }
     }

     public static Date parseDateCal(String date) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            return null;
        }
     }
}