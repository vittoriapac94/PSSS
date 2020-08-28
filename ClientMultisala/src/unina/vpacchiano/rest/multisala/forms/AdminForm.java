package unina.vpacchiano.rest.multisala.forms;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.restlet.data.Status;
import org.restlet.resource.ClientResource;

import com.google.gson.Gson;

import unina.vpacchiano.rest.multisala.controllers.CinemaController;

public class AdminForm {

	private JFrame frmAdmin;
	
	ClientResource cr;
	Gson gson = new Gson();
	Status status;
	String URI;
	String json;
	String chiave; 
	
	CinemaController cc = new CinemaController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminForm window = new AdminForm();
					window.frmAdmin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminForm() {
		initialize();
	}
	
	public AdminForm(String chiave) {
		this.chiave = chiave;
		initialize();
		frmAdmin.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdmin = new JFrame();
		frmAdmin.setTitle("Admin");
		frmAdmin.setBounds(100, 100, 450, 300);
		frmAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
