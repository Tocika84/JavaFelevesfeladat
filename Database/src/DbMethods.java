import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DbMethods {
	private Statement allapot = null;
	private Connection kapcsolat= null;
	private ResultSet eredmeny = null;
	
	public void Regisztracio() {
		try {
			Class.forName("org.sqlite.JDBC");
		//	 Uzenetkuldo("Sikeres driver regisztr�ci�" ,1);
		} catch (ClassNotFoundException e ) {
			 Uzenetkuldo("Hib�s driver regisztr�ci�!"+e.getMessage(),0 );
		}
	}
	public void Uzenetkuldo(String msg, int tipus) {
		JOptionPane.showMessageDialog(null, msg, "Program �zenet", tipus);
	}
	
	public void kapcsolodas() {
		try {
			String eleres= "jdbc:sqlite:C:/JDBC/T�rsash�z";
			kapcsolat = DriverManager.getConnection(eleres);
		//	 Uzenetkuldo("Sikeres Kapcsolat",1);
		}	catch (SQLException e) {
			 Uzenetkuldo("JDBC Connect: "+e.getMessage(),0);
			
		}
		
	}
	
	public void szetkapcsolas() {
		try {
			kapcsolat.close();
	//		Uzenetkuldo("Sz�tkapcsolva!", 1);
		}	catch (SQLException e) { Uzenetkuldo(e.getMessage(),0);}
		
	}
	
	public EmpTM Beolvasas() {
		Object Tarsashaz[]= {"Jel", "Azonos�t�", "C�m", "Ter�let", "K�z�sk�lts�g","Utols� Befizet�s"};
		EmpTM etm = new EmpTM(Tarsashaz,0);
		String cim= "", ter="", ubfiz="", x="\t";
		int id=0, kozklt=0;
		String sqlp= "select id, c�m, ter�let, k�zk�lt, utbefiz from T�rsash�z";
		try {
			allapot = kapcsolat.createStatement();
			eredmeny = allapot.executeQuery(sqlp);
			while (eredmeny.next()) {
				id= eredmeny.getInt("id");
				cim= eredmeny.getString("c�m");
				ter= eredmeny.getString("ter�let");
				kozklt= eredmeny.getInt("k�zk�lt");
				ubfiz= eredmeny.getString("utbefiz");
				etm.addRow(new Object[] {false, id, cim, ter, kozklt, ubfiz});
		
			}
			eredmeny.close();
		}catch (SQLException e) {Uzenetkuldo(e.getMessage(),0);}
		return etm;
	}
	
	public void UjTarsashaz(String id, String cim, String ter, String kozklt, String ubfiz ) {
		
		String SqlpAdatbazis ="insert into T�rsash�z values("+id+",\""+cim +"\", \""+ter+"\", "+kozklt+",\""+ubfiz+"\")";
		try {
			allapot= kapcsolat.createStatement();
			allapot.execute(SqlpAdatbazis);
			 Uzenetkuldo("�j T�rsash�z sikeresen L�trehozva!",1);
		}	catch(SQLException e) {
			 Uzenetkuldo("JDBC insert: "+e.getMessage(),0 );
		
		}
	}
	
	public void TarsashazTorles(String id) {
		String SqlpAdatbazis = "delete from T�rsash�z where id =" +id;
		try {
			allapot = kapcsolat.createStatement();
			allapot.execute(SqlpAdatbazis);}
		catch (SQLException e) {
			 Uzenetkuldo("JDBC Delete: "+e.getMessage(),0);
			
		}
	}
	
	public void TarsashazModositas(String id, String mnev, String madat) {
		String SqlpAdatbazis = "update T�rsash�z set "+mnev+"='"+madat+"' where id="+id;
		try {
			allapot = kapcsolat.createStatement();
			allapot.execute(SqlpAdatbazis);}
		catch (SQLException e) {
			 Uzenetkuldo("JDBC Update: "+e.getMessage(),0);
			
		}
		
	}
	
}


