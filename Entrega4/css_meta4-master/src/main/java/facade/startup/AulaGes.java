package facade.startup;
import business.*;
import  java.util.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import application.*;
import business.AtivarAulaHandler;
import business.CatalogoAulas;
import business.CatalogoInstalacoes;
import business.CatalogoModalidades;
import business.CatalogoUtentes;
import business.CriarAulaHandler;
import business.InscreverAulaHandler;
import business.VisualizarOcupacaoDeInstalacaoHandler;

import java.text.*;

/**
 * The main application class. It implements the  start up use case. 
 */
public class AulaGes{
	//catalogos
	/*private CatalogoAulas catAul;
	private CatalogoInstalacoes catIns;
	private CatalogoModalidades catMod;
	private CatalogoUtentes catUt;*/
	private EntityManagerFactory emf;
	private AtivarAulaService aus;
	private CriarAulaService cas;
	private InscreverAulaService ias;
	private VisualizarService vis;
	
	public void run(){
		/*this.catAul = new CatalogoAulas();
		this.catIns = new CatalogoInstalacoes();
		this.catMod = new CatalogoModalidades();
		this.catUt = new CatalogoUtentes();*/
		
		emf = Persistence.createEntityManagerFactory("CSSEntrega4");
		aus = new AtivarAulaService(new AtivarAulaHandler(emf));
		cas = new CriarAulaService(new CriarAulaHandler(emf));
		ias = new InscreverAulaService(new InscreverAulaHandler(emf));
		vis = new VisualizarService(new VisualizarOcupacaoDeInstalacaoHandler(emf));
		
		/*Date data1 = parseDateTime("00:50");
		Modalidade mod1 = new Modalidade ("Pilates", data1, 7);

		Date data2 = parseDateTime("00:45");
		Modalidade mod2 = new Modalidade ("STEP", data2, 10);

		Modalidade mod3 = new Modalidade ("GAP", data1, 10);

		Date data3 = parseDateTime("00:55");
		Modalidade mod4 = new Modalidade ("Indoor Cycling", data3, 10);

		Modalidade mod5 = new Modalidade ("Hidroginastica", data2, 15);


		List<Modalidade> estudio1Mod= new ArrayList<>();
		estudio1Mod.add(mod1);
		estudio1Mod.add(mod2);
		estudio1Mod.add(mod3);

		List<Modalidade> estudio2Mod= new ArrayList<>();
		estudio2Mod.add(mod1);

		List<Modalidade> bicicletaMod= new ArrayList<>();
		bicicletaMod.add(mod4);

		List<Modalidade> piscinaMod= new ArrayList<>();
		piscinaMod.add(mod5);

		this.catMod.putMod(mod1);
		this.catMod.putMod(mod2);
		this.catMod.putMod(mod3);
		this.catMod.putMod(mod4);
		this.catMod.putMod(mod5);

		Instalacao ins1= new Instalacao(30, "Estudio 1", estudio1Mod);
		Instalacao ins2= new Instalacao(15, "Estudio 2", estudio2Mod);
		Instalacao ins3= new Instalacao(20, "Biclas", bicicletaMod);
		Instalacao ins4= new Instalacao(10, "Piscina 25", piscinaMod);

		catIns.putInstalacao(ins1);
		catIns.putInstalacao(ins2);
		catIns.putInstalacao(ins3);
		catIns.putInstalacao(ins4);

		Utente ut1 = new Utente("Ulisses", "223842389", "1");
		Utente ut2 = new Utente("David", "256039682", "2");
		Utente ut3 = new Utente("Teresa", "269901841", "3");
		Utente ut4 = new Utente("Querubim", "197672337", "4");
		Utente ut5 = new Utente("Cícero", "221057552", "5");
		catUt.putUtente(ut1);
		catUt.putUtente(ut2);
		catUt.putUtente(ut3);
		catUt.putUtente(ut4);
		catUt.putUtente(ut5);*/

		

	}

	public void close() {
		emf.close();
	}
	
	//TODO:REFAZER MAS COM JPA
	/*public void atualizaDados(){
		List<Utente> utentes = this.catUt.getUtentes();

		for(Utente ute: utentes){
			if(ute.temInscricao())
				if(ute.getInscricao().getTipo().equals("Avulso")){
				    Date d24 = ute.getInscricao().getData();
				    Calendar c = Calendar.getInstance(); 
				    c.setTime(d24); 
				c.add(Calendar.DATE, 1);
				d24 = c.getTime();
				Date dNow = new Date();
				if(dNow.after(d24)){
					ute.getInscricao().getAula().removeUtente(ute);
				}
            }
            
		}
	}*/

	public AtivarAulaService getAtivarAulaService() {
		return this.aus;
	}
	public InscreverAulaService getInscreverAulaService() {
		return this.ias;
	}public VisualizarService getVisualizarService() {
		return this.vis;
	}	
	public CriarAulaService getCriarAulaService(){
		return this.cas;
	}
	
	/*public AtivarAulaHandler getAtivarAulaHandler(){
		return new AtivarAulaHandler(this.catAul,this.catIns);
	}

	public CriarAulaHandler getCriarAulaHandler(){
		return new CriarAulaHandler(this.catAul, this.catMod);
	}


	public InscreverAulaHandler getInscreverAulaHandler() {
		return new InscreverAulaHandler(this.catAul,this.catUt,this.catMod);
	}

	public VisualizarOcupacaoDeInstalacaoHandler getVisualizarOcupacaoDeInstalacaoHandler() {
		return new VisualizarOcupacaoDeInstalacaoHandler(this.catIns);
	}*/

	public static Date parseDateTime(String date) {
		try {
			return new SimpleDateFormat("hh:mm").parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date parseDateCal(String date) {
		try {
			return new SimpleDateFormat("DD-MM-YYYY").parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
}