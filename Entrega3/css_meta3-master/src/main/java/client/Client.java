package client;
import java.util.*;

import facade.services.AtivarAulaService;
import facade.services.CriarAulaService;
import facade.services.InscreverAulaService;
import facade.services.VisualizarService;
import facade.startup.AulaGes;
import javafx.util.*;
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
        Date duracao1 = parseDateTime("01:30");
        Date duracao2 = parseDateTime("02:00");
        Date duracao3 = parseDateTime("01:00");
        Date duracao4 = parseDateTime("03:00");
        Date duracao5 = parseDateTime("-2:00");
        Date duracao6 = parseDateTime("00:02");
        
        List<String> diaDaSemana1= new ArrayList<>();
        diaDaSemana1.add("Sunday");
        diaDaSemana1.add("Wednesday");

        List<String> diaDaSemana2= new ArrayList<>();
        diaDaSemana2.add("Monday");
        diaDaSemana2.add("Tuesday");
        diaDaSemana2.add("Thursday");
        diaDaSemana2.add("Saturday");
        diaDaSemana2.add("Sunday");

        Date horaIni1 = parseDateTime("07:15");
        Date horaIni2 = parseDateTime("08:50");
        Date horaIni3 = parseDateTime("16:50");
        Date horaIni4 = parseDateTime("20:00");
        Date horaIni5 = parseDateTime("14:10");
        Date horaIni6 = parseDateTime("12:00");
        Date horaIni7 = parseDateTime("10:00");
        Date horaIni8 = parseDateTime("15:00");
        Date horaIni9 = parseDateTime("17:30");
        

        Date dia2 = parseDateCal("30-05-2020");
        Date dia1 = parseDateCal("04-12-2020");
        Pair<Date, Date> ga1= new Pair<>(dia2, dia1);

        Date dia3 = parseDateCal("04-12-2020");
        Date dia4 = parseDateCal("04-3-2021");
        Date dia5 = parseDateCal("06-12-2020");
        Pair<Date, Date> ga2= new Pair<>(dia3, dia4);
///////////////////////////////////

        Date dia6 = parseDateCal("14-12-2020");
        Date dia7 = parseDateCal("10-04-2021");
        Pair<Date, Date> ga3= new Pair<>(dia6, dia7);


        Date dia8 = parseDateCal("14-05-2020");
        Date dia9 = parseDateCal("14-05-2021");
        Pair<Date, Date> ga4= new Pair<>(dia8, dia9);
        
        
        
        System.out.println("-------Criacao e ativacao de aulas----------");
        System.out.println();
        System.out.println();
        System.out.println("Aula 1: tudo bem");
        //Aula1
        this.criarAulaService.criarAula();
        this.criarAulaService.especificaAula("Natacao", "a2s4d5", diaDaSemana1, horaIni1, duracao1);

        this.ativarAulaService.ativarAula();
        this.ativarAulaService.informacoesAula("a2s4d5", "Piscina", ga1, 2);
        System.out.println("-----------------------------------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println("Aula 2: tudo bem");
        //Aula2
        this.criarAulaService.criarAula();
        this.criarAulaService.especificaAula("Danca de salao", "b4e9g6", diaDaSemana1, horaIni2, duracao2);

        this.ativarAulaService.ativarAula();
        this.ativarAulaService.informacoesAula("b4e9g6", "Estudio", ga2, 15);
        System.out.println("-----------------------------------------------------------------");
        
        System.out.println();
        System.out.println();
        System.out.println("Aula 3: tudo bem");
        //Aula 3
        this.criarAulaService.criarAula();
        this.criarAulaService.especificaAula("Danca de salao", "b4e3g6", diaDaSemana1, horaIni3, duracao2);
        
        this.ativarAulaService.ativarAula();
        this.ativarAulaService.informacoesAula("b4e3g6", "Estudio", ga2, 15);
        System.out.println("-----------------------------------------------------------------");

        System.out.println();
        System.out.println();
        System.out.println("Aula 4: duracao invalida");
        //Aula 5
        this.criarAulaService.criarAula();
        this.criarAulaService.especificaAula("Danca de salao", "a3f4d5", diaDaSemana1, horaIni1, duracao3);

        this.ativarAulaService.ativarAula();
        this.ativarAulaService.informacoesAula("a3f4d5", "Estudio", ga2, 15);
        System.out.println("-----------------------------------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println("Aula 6: tudo bem.");
        //Aula 6 tudo bem
        this.criarAulaService.criarAula();
        this.criarAulaService.especificaAula("Natacao", "abc123", diaDaSemana2, horaIni4, duracao4);

        this.ativarAulaService.ativarAula();
        this.ativarAulaService.informacoesAula("abc123", "Piscina", ga3, 10);
        System.out.println("-----------------------------------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println("Aula 7 modalidade errada");
        //Aula 7 modalidade errada
        this.criarAulaService.criarAula();
        this.criarAulaService.especificaAula("Climbing Cyclism", "78b95e", diaDaSemana2, horaIni3, duracao2);
        
        this.ativarAulaService.ativarAula();
        this.ativarAulaService.informacoesAula("b4e3g6", "Sala de Bicicletas", ga2, 20);
        System.out.println("-----------------------------------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println("Aula 8 tudo bem");
        //Aula 8 tudo bem
        this.criarAulaService.criarAula();
        this.criarAulaService.especificaAula("Ciclismo", "adf859", diaDaSemana2, horaIni5, duracao1);

        this.ativarAulaService.ativarAula();
        this.ativarAulaService.informacoesAula("adf859", "Sala de Bicicletas", ga4, 10);
        System.out.println("-----------------------------------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println("Aula 9: hora inicio no meio de outra");
        //Aula 9 hora inicio no meio de outra  
        this.criarAulaService.criarAula();
        this.criarAulaService.especificaAula("Ciclismo", "adf543", diaDaSemana2, horaIni8, duracao1);

        this.ativarAulaService.ativarAula();
        this.ativarAulaService.informacoesAula("adf543", "Sala de Bicicletas", ga4, 10);
        System.out.println("-----------------------------------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println("Aula 10 nome igual e aula ja esta ativada");
         //Aula 10 nome igual e aula ja esta ativada
         this.criarAulaService.criarAula();
         this.criarAulaService.especificaAula("Ciclismo", "adf859", diaDaSemana2, horaIni5, duracao1);
 
         this.ativarAulaService.ativarAula();
         this.ativarAulaService.informacoesAula("adf859", "Sala de Bicicletas", ga4, 10);
         System.out.println("-----------------------------------------------------------------");
         System.out.println();
         System.out.println();

         System.out.println("Aula 11 nome da aula com formato ilegal (tamanho maior que 6)");
         //Aula 11 nome da aula com formato ilegal (tamanho maior que 6)
        this.criarAulaService.criarAula();
        this.criarAulaService.especificaAula("Ciclismo", "olacomoestas", diaDaSemana2, horaIni8, duracao1);

        this.ativarAulaService.ativarAula();
        this.ativarAulaService.informacoesAula("olacomoestas", "Sala de Bicicletas", ga4, 10);
        System.out.println("-----------------------------------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println("Aula 12: nome da aula com formato ilegal (so com numeros)");
         //Aula 12 nome da aula com formato ilegal (so com numeros)
         this.criarAulaService.criarAula();
         this.criarAulaService.especificaAula("Ciclismo", "123456", diaDaSemana2, horaIni8, duracao1);
 
         this.ativarAulaService.ativarAula();
         this.ativarAulaService.informacoesAula("123456", "Sala de Bicicletas", ga4, 10);
         System.out.println("-----------------------------------------------------------------");
         System.out.println();
         System.out.println();
         
         System.out.println("Aula 13: nome da aula com formato ilegal (com espacos)");
         //Aula 13 nome da aula com formato ilegal (com espacos)
         this.criarAulaService.criarAula();
         this.criarAulaService.especificaAula("Ciclismo", "1234 6", diaDaSemana2, horaIni8, duracao1);
 
         this.ativarAulaService.ativarAula();
         this.ativarAulaService.informacoesAula("1234 6", "Sala de Bicicletas", ga4, 10);
         System.out.println("-----------------------------------------------------------------");
         System.out.println();
         System.out.println();

         System.out.println("Aula 14: duracao negativa");
         //Aula 14 duracao negativa
         this.criarAulaService.criarAula();
         this.criarAulaService.especificaAula("Tenis", "afc676", diaDaSemana1, horaIni8, duracao5);
 
         this.ativarAulaService.ativarAula();
         this.ativarAulaService.informacoesAula("afc676", "Campo de Tenis", ga3, 10);
         System.out.println("-----------------------------------------------------------------");

         System.out.println();
         System.out.println();
         System.out.println("Aula 15 duracao inferior a minima da modalidade");
        //Aula 15 duracao inferior a minima da modalidade
        this.criarAulaService.criarAula();
        this.criarAulaService.especificaAula("Tenis", "afc676", diaDaSemana1, horaIni8, duracao6);

        this.ativarAulaService.ativarAula();
        this.ativarAulaService.informacoesAula("afc676", "Campo de Tenis", ga3, 10);
        System.out.println("-----------------------------------------------------------------");
        System.out.println();
        System.out.println();

        System.out.println("Aula 16: tudo bem.");
        //Aula 6 tudo bem
        this.criarAulaService.criarAula();
        this.criarAulaService.especificaAula("Tenis", "abd123", diaDaSemana2, horaIni4, duracao4);

        this.ativarAulaService.ativarAula();
        this.ativarAulaService.informacoesAula("abd123", "Campo de Tenis", ga2, 10);
        System.out.println("-----------------------------------------------------------------");
        System.out.println();
        System.out.println();
        
        
        System.out.println("-------INSCRICAO----------");
        //Inscrever NA Aula
        System.out.println("Inscricao 1: tudo bem");
        this.inscreverAulaService.inscreverAula();
        this.inscreverAulaService.inscricao("Natacao", "Regular");
        this.inscreverAulaService.indicaAula("a2s4d5", "123456");
        //
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Inscricao 2: tudo bem");
        this.inscreverAulaService.inscreverAula();
        this.inscreverAulaService.inscricao("Natacao", "Avulso");
        this.inscreverAulaService.indicaAula("a2s4d5", "222234");
        System.out.println("-----------------------------------------------------------------");
        //
        System.out.println("Inscricao 3: Nao consegue");
        this.inscreverAulaService.inscreverAula();
        this.inscreverAulaService.inscricao("Natacao", "Regular");
        this.inscreverAulaService.indicaAula("a2s4d5", "123432");
        System.out.println("-----------------------------------------------------------------");


        
        
        System.out.println();
        System.out.println();
        System.out.println("-------Visualizacao----------");
//Ver as Instalacoes
        System.out.print("Estudio: ");
        this.visualizarService.verInstalacao("Estudio", dia5);
        
        System.out.println();
        System.out.print("Piscina: ");
        this.visualizarService.verInstalacao("Piscina", dia5);

        System.out.println();
        System.out.print("Campo de Tenis: ");
        this.visualizarService.verInstalacao("Campo de Tenis", dia5);

        System.out.println();
        System.out.print("Sala de Bicicletas: ");
        this.visualizarService.verInstalacao("Sala de Bicicletas", dia5);
    }                  
                    
    public static void main(String[] args) {
		// Creates the application main object
		AulaGes app = new AulaGes();
		
		// Creates the services used for interaction with the application
		AtivarAulaService ativarAulaService = new AtivarAulaService(app.getAtivarAulaHandler());
        CriarAulaService criarAulaService = new CriarAulaService(app.getCriarAulaHandler());
        InscreverAulaService inscreverAulaService = new InscreverAulaService(app.getInscreverAulaHandler());
        VisualizarService visualizarService= new VisualizarService(app.getVisualizarOcupacaoDeInstalacaoHandler());
		
		// Creates the simple interaction client and passes it the application services
		Client client = new Client(ativarAulaService, criarAulaService,inscreverAulaService,visualizarService);
        client.createEverythingAndSomeMore();
        app.atualizaDados();
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
            return new SimpleDateFormat("dd-MM-yyyy").parse(date);
        } catch (ParseException e) {
            return null;
        }
     }
}