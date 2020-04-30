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
		//	 Uzenetkuldo("Sikeres driver regisztráció" ,1);
		} catch (ClassNotFoundException e ) {
			 Uzenetkuldo("Hibás driver regisztráció!"+e.getMessage(),0 );
		}
	}
	public void Uzenetkuldo(String msg, int tipus) {
		JOptionPane.showMessageDialog(null, msg, "Program üzenet", tipus);
	}
	
	public void kapcsolodas() {
		try {
			String eleres= "jdbc:sqlite:C:/JDBC/Társasház";
			kapcsolat = DriverManager.getConnection(eleres);
		//	 Uzenetkuldo("Sikeres Kapcsolat",1);
		}	catch (SQLException e) {
			 Uzenetkuldo("JDBC Connect: "+e.getMessage(),0);
			
		}
		
	}
	
	public void szetkapcsolas() {
		try {
			kapcsolat.close();
	//		Uzenetkuldo("Szétkapcsolva!", 1);
		}	catch (SQLException e) { Uzenetkuldo(e.getMessage(),0);}
		
	}
	
	public EmpTM Beolvasas() {
		Object Tarsashaz[]= {"Jel", "Azonosító", "Cím", "Terület", "Közösköltség","Utolsó Befizetés"};
		EmpTM etm = new EmpTM(Tarsashaz,0);
		String cim= "", ter="", ubfiz="", x="\t";
		int id=0, kozklt=0;
		String sqlp= "select id, cím, terület, közkölt, utbefiz from Társasház";
		try {
			allapot = kapcsolat.createStatement();
			eredmeny = allapot.executeQuery(sqlp);
			while (eredmeny.next()) {
				id= eredmeny.getInt("id");
				cim= eredmeny.getString("cím");
				ter= eredmeny.getString("terület");
				kozklt= eredmeny.getInt("közkölt");
				ubfiz= eredmeny.getString("utbefiz");
				etm.addRow(new Object[] {false, id, cim, ter, kozklt, ubfiz});
		
			}
			eredmeny.close();
		}catch (SQLException e) {Uzenetkuldo(e.getMessage(),0);}
		return etm;
	}
	
	public void UjTarsashaz(String id, String cim, String ter, String kozklt, String ubfiz ) {
		
		String SqlpAdatbazis ="insert into Társasház values("+id+",\""+cim +"\", \""+ter+"\", "+kozklt+",\""+ubfiz+"\")";
		try {
			allapot= kapcsolat.createStatement();
			allapot.execute(SqlpAdatbazis);
			 Uzenetkuldo("Új Társasház sikeresen Létrehozva!",1);
		}	catch(SQLException e) {
			 Uzenetkuldo("JDBC insert: "+e.getMessage(),0 );
		
		}
	}
	
	public void TarsashazTorles(String id) {
		String SqlpAdatbazis = "delete from Társasház where id =" +id;
		try {
			allapot = kapcsolat.createStatement();
			allapot.execute(SqlpAdatbazis);}
		catch (SQLException e) {
			 Uzenetkuldo("JDBC Delete: "+e.getMessage(),0);
			
		}
	}
	
	public void TarsashazModositas(String id, String mnev, String madat) {
		String SqlpAdatbazis = "update Társasház set "+mnev+"='"+madat+"' where id="+id;
		try {
			allapot = kapcsolat.createStatement();
			allapot.execute(SqlpAdatbazis);}
		catch (SQLException e) {
			 Uzenetkuldo("JDBC Update: "+e.getMessage(),0);
			
		}
		
	}
	
}


