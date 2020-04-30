import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import com.sun.jdi.Method;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public  class EmpDel extends JDialog {
	private final JPanel Torloablak = new JPanel();
	private JTable table;
	private EmpTM lista;
	private Checker Ellenorzo = new Checker();
	private DbMethods Tarsashaztorlese = new DbMethods(); 
 
	
	public EmpDel(JFrame Frame, EmpTM BeolvasottTabla) {
		super(Frame, "Társasházak Törlése", true);
		lista=BeolvasottTabla;
		
		setBounds(100, 100, 679, 300);
		getContentPane().setLayout(new BorderLayout());
		Torloablak.setBorder(new EmptyBorder(5,5,5,5));
		getContentPane().add(Torloablak, BorderLayout.CENTER);
		Torloablak.setLayout(null);
		{
		
			JButton btnBezar = new JButton("Bez\u00E1r\u00E1s");
			btnBezar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose ();
				}
			});
			btnBezar.setBounds(283, 233, 89, 23);
			Torloablak.add(btnBezar);
		}
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 643, 211);
		Torloablak.add(scrollPane);
		
		table = new JTable(lista);
		scrollPane.setViewportView(table);
		
		JButton btnTrls = new JButton("T\u00F6rl\u00E9s");
		btnTrls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db=0, Jel=0, x=0;
				for(x = 0; x<lista.getRowCount();x++)
					if ((Boolean)lista.getValueAt(x,0)) {db++; Jel=x;}
					if (db==0) Ellenorzo.Uzenetkuldo("Nincs kijelölve törlendõ Társasház!", 0);
					if (db>1) Ellenorzo.Uzenetkuldo("Több Társasház van kijelölve! (Egyszerre csak egy törölhetõ)", 0); 
					if (db==1) {
						String id= lista.getValueAt(Jel, 1).toString();
						Tarsashaztorlese.kapcsolodas();
						Tarsashaztorlese.TarsashazTorles(id);
						Tarsashaztorlese.szetkapcsolas();
						lista.removeRow(Jel);
						Ellenorzo.Uzenetkuldo("A Társasház törölve",1);
					}
			}
		});
		btnTrls.setBounds(20, 233, 116, 23);
		Torloablak.add(btnTrls);
		
		TableColumn tc= null;
		for (int i=0; i<6; i++) {
			tc=table.getColumnModel().getColumn(i);
			if (i==0 || i==1 || i==4) tc.setPreferredWidth(30);
			else {tc.setPreferredWidth(100);
		}
		table.setAutoCreateRowSorter(true);
		TableRowSorter<EmpTM> Autosorter= (TableRowSorter<EmpTM>)table.getRowSorter();
		Autosorter.setSortable(0, false);
		
		
	}
		
	
	
	}
}
