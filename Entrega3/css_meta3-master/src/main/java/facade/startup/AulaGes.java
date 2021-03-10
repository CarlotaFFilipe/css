package facade.startup;
import business.*;
import  java.util.*;
import java.text.*;

/**
 * The main application class. It implements the  start up use case. 
 */
public class AulaGes{
	//catalogos
	private CatalogoAulas catAul;
	private CatalogoInstalacoes catIns;
	private CatalogoModalidades catMod;
	private CatalogoUtentes catUt;

	public AulaGes(){
		this.catAul = new CatalogoAulas();
		this.catIns = new CatalogoInstalacoes();
		this.catMod = new CatalogoModalidades();
		this.catUt = new CatalogoUtentes();

		Date data1 = parseDateTime("01:15");
		Modalidade mod1 = new Modalidade ("Ciclismo", data1, 3);

		Date data2 = parseDateTime("01:30");
		Modalidade mod2 = new Modalidade ("Natacao", data2, 3);

		Date data3 = parseDateTime("02:00");
		Modalidade mod3 = new Modalidade ("Danca de salao", data3, 4.37);

		Date data4 = parseDateTime("01:00");
		Modalidade mod4 = new Modalidade ("Tenis", data4, 5);


		List<Modalidade> listMods1= new ArrayList<>();
		listMods1.add(mod1);

		List<Modalidade> listMods2= new ArrayList<>();
		listMods2.add(mod3);

		List<Modalidade> listMods3= new ArrayList<>();
		listMods3.add(mod2);

		List<Modalidade> listMods4= new ArrayList<>();
		listMods4.add(mod4);

		this.catMod.putMod(mod1);
		this.catMod.putMod(mod2);
		this.catMod.putMod(mod3);
		this.catMod.putMod(mod4);

		Instalacao ins1= new Instalacao(30, "Sala de Bicicletas", listMods1);
		Instalacao ins2= new Instalacao(15, "Estudio", listMods2);
		Instalacao ins3= new Instalacao(20, "Piscina", listMods3);
		Instalacao ins4= new Instalacao(10, "Campo de Tenis", listMods4);

		catIns.putInstalacao(ins1);
		catIns.putInstalacao(ins2);
		catIns.putInstalacao(ins3);
		catIns.putInstalacao(ins4);

		Utente ut1 = new Utente("Warlota", "123456789", "123456");
		Utente ut2 = new Utente("Barlota", "123452312", "123432");
		Utente ut3 = new Utente("Farlota", "123433789", "222234");
		catUt.putUtente(ut1);
		catUt.putUtente(ut2);
		catUt.putUtente(ut3);

	}

	public void atualizaDados(){
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
	}



	public AtivarAulaHandler getAtivarAulaHandler(){
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
	}

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