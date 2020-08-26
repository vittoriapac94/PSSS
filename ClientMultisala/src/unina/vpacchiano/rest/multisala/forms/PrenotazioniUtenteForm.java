package unina.vpacchiano.rest.multisala.forms;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.restlet.data.Status;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import com.google.gson.Gson;

import unina.vpacchiano.rest.multisala.controllers.CinemaController;
import unina.vpacchiano.rest.multisala.domain.Film;
import unina.vpacchiano.rest.multisala.domain.Prenotazione;
import unina.vpacchiano.rest.multisala.domain.Programmazione;
import unina.vpacchiano.rest.multisala.domain.Utente;

public class PrenotazioniUtenteForm {

	private JFrame frmLeMiePrenotazioni;
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
					PrenotazioniUtenteForm window = new PrenotazioniUtenteForm();
					window.frmLeMiePrenotazioni.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrenotazioniUtenteForm() {
		initialize();
	}
	
	public PrenotazioniUtenteForm(String chiave) {
		this.chiave = chiave;
		initialize();
		frmLeMiePrenotazioni.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLeMiePrenotazioni = new JFrame();
		frmLeMiePrenotazioni.setTitle("Le mie prenotazioni");
		frmLeMiePrenotazioni.setBounds(100, 100, 450, 300);
		frmLeMiePrenotazioni.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLeMiePrenotazioni.getContentPane().setLayout(null);
		
		JPanel prenotazioniPanel = new JPanel();
		prenotazioniPanel.setBounds(6, 52, 306, 220);
		frmLeMiePrenotazioni.getContentPane().add(prenotazioniPanel);
		prenotazioniPanel.setLayout(null);
		
		
		ArrayList<Prenotazione> listaPrenotazioni;
		DefaultListModel<Prenotazione> dlm = new DefaultListModel<Prenotazione>();
		
		
		try {
			Utente u = cc.getUtente(chiave);
			listaPrenotazioni = cc.getAllPrenotazioni();
			for(Prenotazione p : listaPrenotazioni){
				if (p.getNomeUtente().equals(u.getNomeUtente())) {
					 dlm.addElement(p);
				}
			}    	
			
		} catch (ResourceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JList<Prenotazione> list = new JList<Prenotazione>(dlm);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(0, 0, 306, 145);
		prenotazioniPanel.add(scrollPane);
		
		JPanel prenotazioneInfoPanel = new JPanel();
		prenotazioneInfoPanel.setBounds(0, 144, 306, 76);
		prenotazioniPanel.add(prenotazioneInfoPanel);
		prenotazioneInfoPanel.setLayout(null);
		
		JLabel lblFilm = new JLabel("Film:");
		lblFilm.setBounds(6, 6, 294, 16);
		prenotazioneInfoPanel.add(lblFilm);
		
		JLabel lblSala = new JLabel("Sala:");
		lblSala.setBounds(6, 26, 294, 16);
		prenotazioneInfoPanel.add(lblSala);
		
		JLabel lblDataEOra = new JLabel("Data e ora:");
		lblDataEOra.setBounds(6, 54, 294, 16);
		prenotazioneInfoPanel.add(lblDataEOra);
		
		JPanel userInfoPanel = new JPanel();
		userInfoPanel.setBounds(6, 6, 438, 34);
		frmLeMiePrenotazioni.getContentPane().add(userInfoPanel);
		userInfoPanel.setLayout(null);
		
		try {
			String textUtente = "Sei loggato come "+ cc.getUtente(chiave).getNomeUtente();
			JLabel lblLogin = new JLabel(textUtente);
			lblLogin.setBounds(6, 6, 303, 16);
			userInfoPanel.add(lblLogin);
		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chiave = null;
				new MainForm();
				frmLeMiePrenotazioni.dispose();
			}
		});
		btnLogout.setBounds(315, 1, 117, 29);
		userInfoPanel.add(btnLogout);
		
		JPanel actionPanel = new JPanel();
		actionPanel.setBounds(318, 52, 126, 220);
		frmLeMiePrenotazioni.getContentPane().add(actionPanel);
		
		
		
		list.addListSelectionListener(new ListSelectionListener() {
	        @Override
	        public void valueChanged(ListSelectionEvent arg0) {
	        	if (!arg0.getValueIsAdjusting()) {
	        		Prenotazione selected = list.getSelectedValue();
	        		if (selected != null) {
	        			Programmazione p;
	            		Film f;
	            		try {
	            			p = cc.getProgrammazione(selected.getCodProgrammazione(), null);
	            			f = cc.getFilm(p.getCodFilm(), null);
	            			lblFilm.setText("Film: "+f.getNome());
	            			lblSala.setText("Sala: "+p.getNomeSala());
	            			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	            			String dataStr = sdf.format(p.getData());
	            			lblDataEOra.setText("Data e ora: "+dataStr+" - "+p.getOrario());
	            		} catch (ResourceException e1) {
	            			// TODO Auto-generated catch block
	            			e1.printStackTrace();
	           			} catch (IOException e1) {
	           				// TODO Auto-generated catch block
	           				e1.printStackTrace();
	            		}
	            	}
	            }
	        }
	    });
		
		JButton btnRimuovi = new JButton("Rimuovi");
		btnRimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
	            JOptionPane.showConfirmDialog (null, "Sei sicuro di voler eliminare la prenotazione?","Messaggio", dialogButton);
	            if(dialogButton == JOptionPane.YES_OPTION) {
	            	Prenotazione selected = list.getSelectedValue();
	            	System.out.println("prenotazione selezionata: "+selected);
	                //CANCELLA LA PRENOTAZIONE
	            	try {
						cc.removePrenotazione(selected, chiave);
						//RICARICA LA LISTA
						frmLeMiePrenotazioni.dispose();
						new PrenotazioniUtenteForm(chiave);
					} catch (ResourceException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            if(dialogButton == JOptionPane.NO_OPTION) {
	                  frmLeMiePrenotazioni.remove(dialogButton);
	                }
	              }
			}
		});
		actionPanel.add(btnRimuovi);
		
	}

}
