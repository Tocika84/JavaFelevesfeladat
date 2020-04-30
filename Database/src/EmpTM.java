import javax.swing.table.DefaultTableModel;

public class EmpTM extends DefaultTableModel {
	public EmpTM (Object Mezonev[], int Sorok) {
		super(Mezonev, Sorok);
		}
	
	public boolean isCellSzeeditable(int Sor, int oszlop) {
		if (oszlop==0) {return true ;}
		return false;
	}
	

	public Class<?> getColumnClass (int index){
		if (index==0) return (Boolean.class);
			else if (index==1 || index==4) return (Integer.class);
		return(String.class);
	}
		
}
