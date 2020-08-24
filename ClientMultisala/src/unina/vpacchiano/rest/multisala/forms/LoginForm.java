package unina.vpacchiano.rest.multisala.forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;

public class LoginForm {

	private JFrame frmLogin;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtUsername_1;
	private JTextField txtPassword_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm window = new LoginForm();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 450, 300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 214, 266);
		frmLogin.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblAccessoAmministratore = new JLabel("Accesso amministratore");
		lblAccessoAmministratore.setBounds(6, 6, 202, 16);
		panel.add(lblAccessoAmministratore);
		
		txtUsername = new JTextField();
		txtUsername.setForeground(Color.GRAY);
		txtUsername.setText("Username");
		txtUsername.setBounds(38, 53, 130, 26);
		panel.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setForeground(Color.GRAY);
		txtPassword.setText("Password");
		txtPassword.setColumns(10);
		txtPassword.setBounds(38, 91, 130, 26);
		panel.add(txtPassword);
		
		JButton btnAccedi = new JButton("Accedi");
		btnAccedi.setBounds(91, 231, 117, 29);
		panel.add(btnAccedi);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(232, 6, 212, 266);
		frmLogin.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblAccessoUtente = new JLabel("Accesso utente");
		lblAccessoUtente.setBounds(6, 5, 200, 16);
		panel_1.add(lblAccessoUtente);
		
		txtUsername_1 = new JTextField();
		txtUsername_1.setForeground(Color.GRAY);
		txtUsername_1.setText("Username");
		txtUsername_1.setColumns(10);
		txtUsername_1.setBounds(37, 54, 130, 26);
		panel_1.add(txtUsername_1);
		
		txtPassword_1 = new JTextField();
		txtPassword_1.setForeground(Color.GRAY);
		txtPassword_1.setText("Password");
		txtPassword_1.setColumns(10);
		txtPassword_1.setBounds(37, 92, 130, 26);
		panel_1.add(txtPassword_1);
		
		JButton btnAccedi_1 = new JButton("Accedi");
		btnAccedi_1.setBounds(89, 231, 117, 29);
		panel_1.add(btnAccedi_1);
	}
}
