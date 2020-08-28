package unina.vpacchiano.rest.multisala.forms;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import org.restlet.data.Status;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import com.google.gson.Gson;

import unina.vpacchiano.rest.multisala.controllers.CinemaController;
<<<<<<< HEAD
import unina.vpacchiano.rest.multisala.controllers.UtenteSconosciutoException;
import unina.vpacchiano.rest.multisala.domain.Film;
=======
>>>>>>> giulio
import unina.vpacchiano.rest.multisala.domain.Prenotazione;
import unina.vpacchiano.rest.multisala.domain.Programmazione;
import unina.vpacchiano.rest.multisala.domain.Utente;

import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;

public class MainForm {

	private JFrame frmInProgramma;
	ClientResource cr;
	Gson gson = new Gson();
	Status status;
	String URI;
	String json;
	static String chiave; 
	
	CinemaController cc = new CinemaController();
	private JTextField txtNumeroPosti;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm(chiave);
					window.frmInProgramma.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainForm(String chiave) {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
<<<<<<< HEAD
		//System.out.println("chiave qui: "+chiave);
=======
>>>>>>> giulio
		frmInProgramma = new JFrame();
		frmInProgramma.setTitle("In programma");
		frmInProgramma.setBounds(100, 100, 450, 300);
		frmInProgramma.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInProgramma.getContentPane().setLayout(null);
		
		JPanel programmazioniPanel = new JPanel();
		programmazioniPanel.setBounds(6, 52, 306, 220);
		frmInProgramma.getContentPane().add(programmazioniPanel);
		programmazioniPanel.setLayout(null);
		
		
		ArrayList<Programmazione> listaProgrammazioni;
		DefaultListModel<Programmazione> dlm = new DefaultListModel<Programmazione>();
		
		try {
			listaProgrammazioni = cc.getAllProgrammazione();
			for(Programmazione p : listaProgrammazioni){
			     dlm.addElement(p);
			}    	
			
		} catch (ResourceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JList<Programmazione> list = new JList<Programmazione>(dlm);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(0, 0, 306, 220);
		programmazioniPanel.add(scrollPane);
		
		JPanel userInfoPanel = new JPanel();
		userInfoPanel.setBounds(6, 6, 438, 34);
		frmInProgramma.getContentPane().add(userInfoPanel);
		userInfoPanel.setLayout(null);
		
		JLabel lblNonSeiLoggato = new JLabel("Non sei loggato");
		lblNonSeiLoggato.setBounds(6, 6, 303, 16);
		userInfoPanel.add(lblNonSeiLoggato);
		
		JButton btnVaiAlLogin = new JButton("Vai al login");
		btnVaiAlLogin.setBounds(315, 1, 117, 29);
		userInfoPanel.add(btnVaiAlLogin);
		
		JPanel actionPanel = new JPanel();
		actionPanel.setBounds(318, 52, 126, 220);
		frmInProgramma.getContentPane().add(actionPanel);
		
		txtNumeroPosti = new JTextField();
		txtNumeroPosti.setForeground(Color.GRAY);
		txtNumeroPosti.setText("Numero posti");
		txtNumeroPosti.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            	txtNumeroPosti.setText("");
            	txtNumeroPosti.setForeground(Color.BLACK);
            }
        });
		
		JButton btnPrenota = new JButton("Prenota");
		btnPrenota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//PRENOTA SOLO SE LOGGATO
				Programmazione selected = list.getSelectedValue();
				try {
					Utente u = cc.getUtente(chiave);
					String codPren = "Pr_"+selected.getCodProgrammazione()+"_"+u.getNomeUtente();
					int numeroPosti = Integer.parseInt(txtNumeroPosti.getText());
					Prenotazione pren = new Prenotazione (codPren, u.getNomeUtente(), selected.getCodProgrammazione(), numeroPosti);
					cc.addPrenotazione(pren, chiave);
					System.out.println("prenotazione aggiunta");
				} catch (ResourceException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
		actionPanel.add(txtNumeroPosti);
		txtNumeroPosti.setColumns(10);
		actionPanel.add(btnPrenota);
<<<<<<< HEAD
		
		JButton btnLeMiePrenotazioni = new JButton("Le mie prenotazioni");
		btnLeMiePrenotazioni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PrenotazioniUtenteForm(chiave);
				frmInProgramma.dispose();
			}
		});
		btnLeMiePrenotazioni.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		actionPanel.add(btnLeMiePrenotazioni);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(6, 6, 438, 266);
		frmInProgramma.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		if(chiave == null) {
			actionPanel.setVisible(false);
		}
=======
>>>>>>> giulio
	}
}
