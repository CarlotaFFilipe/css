package business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataCorrente {

	public static Date getDataCorrente() {		
		try {
			String format = "20/04/2020 14:00";
			return new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(format);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date getDataCorrenteSoDia() {		
		try {
			String format = "20/04/2020";
			return new SimpleDateFormat("dd/MM/yyyy").parse(format);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
