import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public  class EmpList extends JDialog {
	private final JPanel ListaAblak = new JPanel();
	private JTable JTable;
	private EmpTM Lista;
	

	
	public EmpList(JFrame f, EmpTM BeolvasottTabla) {
		super(f, "Társasházak listája", true);
		Lista=BeolvasottTabla;
		
		setBounds(100, 100, 679, 300);
		getContentPane().setLayout(new BorderLayout());
		ListaAblak.setBorder(new EmptyBorder(5,5,5,5));
		getContentPane().add(ListaAblak, BorderLayout.CENTER);
		ListaAblak.setLayout(null);
		{
		
			JButton btnBezar = new JButton("Bez\u00E1r\u00E1s");
			btnBezar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose ();
				}
			});
			btnBezar.setBounds(283, 233, 89, 23);
			ListaAblak.add(btnBezar);
		}
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 643, 211);
		ListaAblak.add(scrollPane);
		
		JTable = new JTable(Lista);
		scrollPane.setViewportView(JTable);
		
		TableColumn oszlopokmeret = null;
		for (int i=0; i<6; i++) {
			oszlopokmeret=JTable.getColumnModel().getColumn(i);
			if (i==0 || i==1 || i==4) oszlopokmeret.setPreferredWidth(30);
			else {oszlopokmeret.setPreferredWidth(100);
		}
		JTable.setAutoCreateRowSorter(true);
		TableRowSorter<EmpTM> AutoSorter= (TableRowSorter<EmpTM>)JTable.getRowSorter();
		AutoSorter.setSortable(0, false);
		
		
	}
	
	
	}
}
