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
import javax.swing.JLabel;
import javax.swing.JTextField;

public  class EmpMod extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private EmpTM lista;
	private Checker Ellenorzo = new Checker();
	private DbMethods TarsashazModositas = new DbMethods(); 
	private JTextField Azonosito;
	private JTextField Cim;
	private JTextField Terulet;
	private JTextField Kozoskoltseg;
	private JTextField Utolsobefizetes;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
 
	
	public EmpMod(JFrame f, EmpTM BeolvasottTabla) {
		super(f, "Társasházak Módosítása", true);
		lista=BeolvasottTabla;
		
		setBounds(100, 100, 680, 359);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5,5,5,5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
		
			JButton btnBezar = new JButton("Bez\u00E1r\u00E1s");
			btnBezar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose ();
				}
			});
			btnBezar.setBounds(283, 263, 89, 23);
			contentPanel.add(btnBezar);
		}
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 643, 175);
		contentPanel.add(scrollPane);
		
		table = new JTable(lista);
		scrollPane.setViewportView(table);
		
		JButton btnmodosit = new JButton("M\u00F3dos\u00EDt\u00E1s");
		btnmodosit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db=0, Jel=0, x=0;
				for(x = 0; x<lista.getRowCount();x++)
					if ((Boolean)lista.getValueAt(x,0)) {db++; Jel=x;}
					if (db==0) Ellenorzo.Uzenetkuldo("Nincs kijelölve módosítandó Társasház!", 0);
					
					if (db>1) Ellenorzo.Uzenetkuldo("Több Társasház van kijelölve! (Egyszerre csak egy módosítható)", 0); 
					
					if (db==1) {
						if (Adatmodositas() >0) {
							boolean ok= true;
							if (Ellenorzo.kitoltott(Azonosito)) ok= Ellenorzo.JoszamAdat(Azonosito,"Azonosító");
							if (ok && Ellenorzo.kitoltott(Kozoskoltseg)) ok = Ellenorzo.JoszamAdat(Kozoskoltseg, "Közösköltség");
							if(ok) {
								String mkod = lista.getValueAt(Jel, 1).toString();
								TarsashazModositas.kapcsolodas();
								if(Ellenorzo.kitoltott(Cim)) TarsashazModositas.TarsashazModositas(mkod, "cím", Ellenorzo.Mezoellenorzes(Cim));
								if(Ellenorzo.kitoltott(Terulet)) TarsashazModositas.TarsashazModositas(mkod, "terület", Ellenorzo.Mezoellenorzes(Terulet));
								if(Ellenorzo.kitoltott(Kozoskoltseg)) TarsashazModositas.TarsashazModositas(mkod, "közkölt", Ellenorzo.Mezoellenorzes(Kozoskoltseg));
								if(Ellenorzo.kitoltott(Utolsobefizetes)) TarsashazModositas.TarsashazModositas(mkod, "utbefiz", Ellenorzo.Mezoellenorzes(Utolsobefizetes));
								if(Ellenorzo.kitoltott(Azonosito)) TarsashazModositas.TarsashazModositas(mkod, "id", Ellenorzo.Mezoellenorzes(Azonosito));
								TarsashazModositas.szetkapcsolas();
								
								if (Ellenorzo.kitoltott(Azonosito)) lista.setValueAt(Ellenorzo.strinToInt(Ellenorzo.Mezoellenorzes(Azonosito)), Jel, 1);
								if (Ellenorzo.kitoltott(Cim)) lista.setValueAt(Ellenorzo.Mezoellenorzes(Cim), Jel, 2);
								if (Ellenorzo.kitoltott(Terulet)) lista.setValueAt(Ellenorzo.Mezoellenorzes(Terulet), Jel, 3);
								if (Ellenorzo.kitoltott(Kozoskoltseg)) lista.setValueAt(Ellenorzo.strinToInt(Ellenorzo.Mezoellenorzes(Kozoskoltseg)), Jel, 4);
								if (Ellenorzo.kitoltott(Utolsobefizetes)) lista.setValueAt(Ellenorzo.Mezoellenorzes(Utolsobefizetes), Jel, 5);
								Ellenorzo.Uzenetkuldo("A Társasház Módosítva",1);
								
								
							
							}
							else {
								Ellenorzo.Uzenetkuldo("Nincs kitöltve egy módosító mezezõ sem!",0);
							}
						}
					}
					
			}
		});
		btnmodosit.setBounds(23, 263, 116, 23);
		contentPanel.add(btnmodosit);
		
		Azonosito = new JTextField();
		Azonosito.setBounds(81, 197, 47, 20);
		contentPanel.add(Azonosito);
		Azonosito.setColumns(10);
		
		Cim = new JTextField();
		Cim.setBounds(138, 197, 135, 20);
		contentPanel.add(Cim);
		Cim.setColumns(10);
		
		Terulet = new JTextField();
		Terulet.setBounds(283, 197, 148, 20);
		contentPanel.add(Terulet);
		Terulet.setColumns(10);
		
		Kozoskoltseg = new JTextField();
		Kozoskoltseg.setBounds(441, 197, 65, 20);
		contentPanel.add(Kozoskoltseg);
		Kozoskoltseg.setColumns(10);
		
		Utolsobefizetes = new JTextField();
		Utolsobefizetes.setBounds(531, 197, 98, 20);
		contentPanel.add(Utolsobefizetes);
		Utolsobefizetes.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Azonos\u00EDt\u00F3");
		lblNewLabel.setBounds(80, 228, 59, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblCm = new JLabel("C\u00EDm");
		lblCm.setBounds(196, 228, 46, 14);
		contentPanel.add(lblCm);
		
		lblNewLabel_1 = new JLabel("Ter\u00FClet");
		lblNewLabel_1.setBounds(335, 228, 46, 14);
		contentPanel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("K\u00F6z\u00F6sk\u00F6lts\u00E9g");
		lblNewLabel_2.setBounds(441, 228, 86, 14);
		contentPanel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Utols\u00F3 Befizet\u00E9s");
		lblNewLabel_3.setBounds(541, 228, 99, 14);
		contentPanel.add(lblNewLabel_3);
		
		TableColumn oszlopok= null;
		for (int i=0; i<6; i++) {
			oszlopok=table.getColumnModel().getColumn(i);
			if (i==0 || i==1 || i==4) oszlopok.setPreferredWidth(30);
			else {oszlopok.setPreferredWidth(100);
		}
		table.setAutoCreateRowSorter(true);
		TableRowSorter<EmpTM>Autosorter= (TableRowSorter<EmpTM>)table.getRowSorter();
		Autosorter.setSortable(0, false);
		
		
	}
		
	
	
	}
	public int Adatmodositas() {
		int modosito=0;
		if (Ellenorzo.kitoltott(Azonosito)) modosito++;
		if (Ellenorzo.kitoltott(Cim)) modosito++;
		if (Ellenorzo.kitoltott(Terulet)) modosito++;
		if (Ellenorzo.kitoltott(Kozoskoltseg)) modosito++;
		if (Ellenorzo.kitoltott(Utolsobefizetes)) modosito++;
		return modosito;
	}
	 
	
}
