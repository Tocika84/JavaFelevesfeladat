import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Program extends JFrame {
	
	
	private JPanel Foablak;
	private DbMethods Metodus = new DbMethods();
	private EmpTM Lista;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program keret = new Program();
					keret.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
	}

	/**
	 * Create the frame.
	 */
	public Program() {
		Metodus.Regisztracio();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		Foablak = new JPanel();
		Foablak.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Foablak);
		Foablak.setLayout(null);
		
		
		
		JButton btnLista = new JButton("T\u00E1rsash\u00E1zak list\u00E1z\u00E1sa");
		btnLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Metodus.kapcsolodas();
				Lista= Metodus.Beolvasas();
				Metodus.szetkapcsolas();
				EmpList el = new EmpList(Program.this, Lista);
				el.setVisible(true);
				
			}
		});
		btnLista.setBounds(129, 21, 171, 23);
		Foablak.add(btnLista);
		
		JButton btnujhaz = new JButton("\u00DAj T\u00E1rsash\u00E1z");
		btnujhaz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewEmp ne = new NewEmp();
				ne.setVisible(true);
			}
		});
		btnujhaz.setBounds(129, 67, 171, 23);
		Foablak.add(btnujhaz);
		
		JButton btnTorles = new JButton("T\u00E1rsash\u00E1z T\u00F6rl\u00E9se");
		btnTorles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Metodus.kapcsolodas();
				Lista= Metodus.Beolvasas();
					Metodus.szetkapcsolas();
					EmpDel ed = new EmpDel(Program.this, Lista);
					ed.setVisible(true);
			}
		});
		btnTorles.setBounds(129, 117, 171, 23);
		Foablak.add(btnTorles);
		
		JButton btnModosit = new JButton("T\u00E1rsash\u00E1z M\u00F3dos\u00EDt\u00E1sa");
		btnModosit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Metodus.kapcsolodas();
				Lista= Metodus.Beolvasas();
				Metodus.szetkapcsolas();
				EmpMod em = new EmpMod(Program.this, Lista);
				em.setVisible(true);
			}
		});
		btnModosit.setBounds(129, 178, 171, 23);
		Foablak.add(btnModosit);
		
		Object Mezonevek[]= {"Jel", "Azonosító", "Cím", "Terület", "Közösköltség","Utolsó Befizetés"};
		EmpTM Mezok = new EmpTM(Mezonevek,0);
	
		
}
	}

