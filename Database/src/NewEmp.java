import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewEmp extends JDialog {
	private JTextField Azonosíto;
	private JTextField Cim;
	private JTextField Terulet;
	private JTextField Kozoskoltseg;
	private JTextField UtolsoBefiz;
	private DbMethods UjTarsashaz = new DbMethods();
	

	
	public NewEmp() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblAzonosito = new JLabel("Azonos\u00EDt\u00F3:");
		lblAzonosito.setBounds(51, 21, 126, 25);
		getContentPane().add(lblAzonosito);
		
		JLabel lblcim = new JLabel("C\u00EDm:");
		lblcim.setBounds(51, 58, 126, 14);
		getContentPane().add(lblcim);
		
		JLabel lblTerulet = new JLabel("Ter\u00FClet:");
		lblTerulet.setBounds(51, 89, 126, 14);
		getContentPane().add(lblTerulet);
		
		JLabel lblkozklt = new JLabel("K\u00F6z\u00F6s k\u00F6lts\u00E9g:");
		lblkozklt.setBounds(51, 120, 126, 14);
		getContentPane().add(lblkozklt);
		
		JLabel lblbefizdat = new JLabel("Utols\u00F3 befizet\u00E9s D\u00E1tuma:");
		lblbefizdat.setBounds(51, 157, 142, 14);
		getContentPane().add(lblbefizdat);
		
		Azonosíto = new JTextField();
		Azonosíto.setBounds(228, 23, 172, 20);
		getContentPane().add(Azonosíto);
		Azonosíto.setColumns(10);
		
		Cim = new JTextField();
		Cim.setBounds(228, 55, 172, 20);
		getContentPane().add(Cim);
		Cim.setColumns(10);
		
		Terulet = new JTextField();
		Terulet.setColumns(10);
		Terulet.setBounds(228, 86, 172, 20);
		getContentPane().add(Terulet);
		
		Kozoskoltseg = new JTextField();
		Kozoskoltseg.setBounds(228, 117, 172, 20);
		getContentPane().add(Kozoskoltseg);
		Kozoskoltseg.setColumns(10);
		
		UtolsoBefiz = new JTextField();
		UtolsoBefiz.setColumns(10);
		UtolsoBefiz.setBounds(228, 154, 172, 20);
		getContentPane().add(UtolsoBefiz);
		
		JButton btnbeszur = new JButton("Besz\u00FAr\u00E1s");
		btnbeszur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Checker c = new Checker();
				if (c.JoszamAdat(Azonosíto, "Azonosító"))
					if (c.filled(Cim, "Cím"))
					if (c.filled(Terulet, "Terület"))
							if(c.JoszamAdat(Kozoskoltseg, "Közösköltség"))
								if (c.JoDatum(UtolsoBefiz, "Utolsó Befizetés")) {
						
				UjTarsashaz.kapcsolodas();
				UjTarsashaz. UjTarsashaz(Mezoellenorzes(Azonosíto), Mezoellenorzes(Cim), Mezoellenorzes(Terulet), Mezoellenorzes(Kozoskoltseg), Mezoellenorzes(UtolsoBefiz));
				UjTarsashaz.szetkapcsolas();
				}
			}
		});
		btnbeszur.setBounds(167, 212, 89, 23);
		getContentPane().add(btnbeszur);

	}
	public String Mezoellenorzes (JTextField Jomezo) {
		return Jomezo.getText();
	}
}
