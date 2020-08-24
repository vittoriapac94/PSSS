package unina.vpacchiano.rest.multisala.forms;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
import unina.vpacchiano.rest.multisala.domain.Film;
import unina.vpacchiano.rest.multisala.domain.Prenotazione;
import unina.vpacchiano.rest.multisala.domain.Programmazione;
import unina.vpacchiano.rest.multisala.domain.Utente;

import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;

public class MainForm {

	private JFrame frmInProgramma;
	ClientResource cr;
	Gson gson = new Gson();
	Status status;
	String URI;
	String json;
	String chiave; 
	
	CinemaController cc = new CinemaController();
	private JTextField txtNumeroPosti;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
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
		this.chiave = chiave;
		initialize();
		frmInProgramma.setVisible(true);
	}
	
	public MainForm() {
		initialize();
		frmInProgramma.setVisible(true);
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		System.out.println("chiave qui: "+chiave);
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
		scrollPane.setBounds(0, 0, 306, 145);
		programmazioniPanel.add(scrollPane);
		
		JPanel programmazioneInfoPanel = new JPanel();
		programmazioneInfoPanel.setBounds(0, 144, 306, 76);
		programmazioniPanel.add(programmazioneInfoPanel);
		programmazioneInfoPanel.setLayout(null);
		
		JLabel lblFilm = new JLabel("Film:");
		lblFilm.setBounds(6, 6, 294, 16);
		programmazioneInfoPanel.add(lblFilm);
		
		JLabel lblSala = new JLabel("Sala:");
		lblSala.setBounds(6, 26, 294, 16);
		programmazioneInfoPanel.add(lblSala);
		
		JLabel lblDataEOra = new JLabel("Data e ora:");
		lblDataEOra.setBounds(6, 54, 294, 16);
		programmazioneInfoPanel.add(lblDataEOra);
		
		JPanel userInfoPanel = new JPanel();
		userInfoPanel.setBounds(6, 6, 438, 34);
		frmInProgramma.getContentPane().add(userInfoPanel);
		userInfoPanel.setLayout(null);
		
		
		String textUtente = "Non sei loggato";
		
		if(chiave != null) {
			try {
				textUtente = "Sei loggato come "+ cc.getUtente(chiave).getNomeUtente();
			} catch (ResourceException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
			
		JLabel lblNonSeiLoggato = new JLabel(textUtente);
		lblNonSeiLoggato.setBounds(6, 6, 303, 16);
		userInfoPanel.add(lblNonSeiLoggato);
		
		JButton btnVaiAlLogin = new JButton("Vai al login");
		btnVaiAlLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginForm();
				frmInProgramma.dispose();
			}
		});
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
		
		
		list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                	Programmazione selected = list.getSelectedValue();
            		if (selected != null) {
            			Film f;
            			try {
            				f = cc.getFilm(selected.getCodFilm(), null);
            				lblFilm.setText("Film: "+f.getNome());
            				lblSala.setText("Sala: "+selected.getNomeSala());
            				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            				String dataStr = sdf.format(selected.getData());
            				lblDataEOra.setText("Data e ora: "+dataStr+" - "+selected.getOrario());
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
		
		if(chiave == null) {
			actionPanel.setVisible(false);
		}
	}
}
