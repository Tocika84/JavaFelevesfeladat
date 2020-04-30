
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Checker {
	
	public boolean filled (JTextField mezo, String adat) {
		String ellenorzo =Mezoellenorzes (mezo);
		if (ellenorzo.length()>0) return true;
		else {
			Uzenetkuldo("Hiba: a(z) "+adat+" mezõ üres",0);
			return false;
		}
	}
	public boolean JoszamAdat(JTextField mezo ,String adat) {
		String ellenorzo =Mezoellenorzes (mezo);
		boolean Joszamadat = filled(mezo,adat);
		if (Joszamadat) try {
			Integer.parseInt(ellenorzo);
		} catch(NumberFormatException e){
			Uzenetkuldo("A(z) "+adat+" mezõben hibás számadat!",0);
			Joszamadat = false;
		}
		return Joszamadat;
	}
	
	public String Mezoellenorzes(JTextField joadat) {
		return joadat.getText();
	}
	
	public void Uzenetkuldo(String uzenet, int tipus) {
		JOptionPane.showMessageDialog(null, uzenet, "Program üzenet", tipus);
	}
	
	public boolean Datumellenorzor(String datum) {
		SimpleDateFormat Jodatum = new SimpleDateFormat("yyyy.MM.dd");
		try {
			java.util.Date pdate = Jodatum.parse (datum);
			if (!Jodatum.format(pdate).equals(datum)) {return false;}
			return true;
		} catch(ParseException ef) {return false;}
	}
	
	public boolean JoDatum(JTextField mezo, String adat) {
		String ellenorzo=Mezoellenorzes(mezo);
		boolean b = filled (mezo,adat);
		if (b && Datumellenorzor(ellenorzo))return true;
		else {
			Uzenetkuldo ("A(z) "+adat+" Mezõben hibás a dátum",0);
			return false;	
		}
		
	}
	
	public boolean kitoltott (JTextField mezo) {
		String ellenorzo =Mezoellenorzes (mezo);
		if (ellenorzo.length()>0) return true;
		else return false;
	}
	
	public int strinToInt(String adat) {
		int szam=-1;
		try { szam=Integer.valueOf(adat);}
		catch (NumberFormatException nfe) {
			Uzenetkuldo("strinToInt: "+nfe.getMessage(),0);
		}
		return szam;
	}
}

