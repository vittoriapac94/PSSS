package unina.vpacchiano.rest.multisala.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.restlet.data.Status;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import com.google.gson.Gson;

import unina.vpacchiano.rest.multisala.domain.Film;
import unina.vpacchiano.rest.multisala.domain.Prenotazione;
import unina.vpacchiano.rest.multisala.domain.Programmazione;
import unina.vpacchiano.rest.multisala.domain.Sala;
import unina.vpacchiano.rest.multisala.domain.Utente;

public class CinemaController {
	
	private ClientResource cr;
	private Gson gson = new Gson();
	private Status status;
	private String URI;
	private String json;
	private static final String localURL = "http://localhost:8182";
	
	
	public CinemaController() {
		
	}
	
	//METODI LOGIN E REGISTRAZIONE UTENTE
	
<<<<<<< HEAD
	public String login(String username, String password) throws IOException {	
=======
	public String login(String username, String password) throws ResourceException, IOException {	
>>>>>>> giulio
		String key;
		URI = localURL+"/users/login/"+username+"&"+password;
		cr = new ClientResource(URI);
		json = cr.get().getText();
		status = cr.getStatus();
		if (status.getCode() != 200) {
			key = null;
<<<<<<< HEAD
			switch(status.getCode()){
			case Constants.ECCEZIONE_UTENTE_INESISTENTE:
				JOptionPane.showMessageDialog(null,
						new UtenteSconosciutoException(username).getMessage(),
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
				break;
			case Constants.ECCEZIONE_PASSWORD_ERRATA: 
				System.err.println("Password errata");
				JOptionPane.showMessageDialog(null,
						"La password inserita non è corretta",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			 	break;
			case Constants.ECCEZIONE_COLLEGAMENTO_DATABASE: 
				System.err.println("Errore collegamento database");
				JOptionPane.showMessageDialog(null,
						"Connessione con il server persa",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			 	break;
			default: 
				System.err.println("Errore generico");
				break;
			}
=======
			System.exit(status.getCode());
>>>>>>> giulio
		} else {
			key = gson.fromJson(json, String.class);
			JOptionPane.showMessageDialog(null,
					"Benvenuto, "+username,
				    "Login",
				    JOptionPane.INFORMATION_MESSAGE);
		}
		return key;
	}
	
	public void registraUtente(Utente u) throws ResourceException, IOException {
		URI = localURL+"/users/login/"+u.getNomeUtente()+"&"+u.getPassword();
		cr = new ClientResource(URI);
		json = cr.post(gson.toJson(u,Utente.class)).getText();
		status = cr.getStatus();
		if (status.getCode() != 200) {
			switch(status.getCode()){
			case Constants.ECCEZIONE_UTENTE_DUPLICATO: 
				System.err.println("Utente già esistente");
				JOptionPane.showMessageDialog(null,
					new UtenteDuplicatoException(u.getNomeUtente()).getMessage(),
					"Warning",
					JOptionPane.WARNING_MESSAGE);
				break;
			case Constants.ECCEZIONE_COLLEGAMENTO_DATABASE: 
				System.err.println("Errore collegamento database");
				JOptionPane.showMessageDialog(null,
						"Connessione con il server persa",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			 	break;
			default: 
				System.err.println("Errore generico");
				break;
			}
		} else {
			Utente utente = gson.fromJson(json, Utente.class);
			JOptionPane.showMessageDialog(null,
					"Ti sei registrato con successo",
				    "Registrazione avvenuta",
				    JOptionPane.INFORMATION_MESSAGE);
			System.out.println("E' stato aggiunto l'utente: " + utente.toString());
		}	
	}
	
	public Utente getUtente(String chiave) throws ResourceException, IOException {
		Utente utente;
		
		URI = localURL+"/users/"+chiave;
		cr = new ClientResource(URI);
		json = cr.get().getText();
		status = cr.getStatus();
		if (status.getCode() != 200) {
			utente = null;
			switch(status.getCode()){
			case Constants.ECCEZIONE_UTENTE_INESISTENTE: 
				System.err.println("Utente sconosciuto");
				JOptionPane.showMessageDialog(null,
					new UtenteSconosciutoException(chiave).getMessage(),
					"Warning",
					JOptionPane.WARNING_MESSAGE);
				break;
			case Constants.ECCEZIONE_COLLEGAMENTO_DATABASE: 
				System.err.println("Errore collegamento database");
				JOptionPane.showMessageDialog(null,
						"Connessione con il server persa",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			 	break;
			default: 
				System.err.println("Errore generico");
				break;
			}
		} else {
			utente = gson.fromJson(json, Utente.class);	
		}
		return utente;
	}
		
	
	//METODI FILM
	
	public ArrayList<Film> getAllFilms() throws ResourceException, IOException {
		ArrayList<Film> listaFilm = new ArrayList<Film>();
		
		URI = localURL+"/film/all";
		cr = new ClientResource(URI);
		json = cr.get().getText();
		status = cr.getStatus();
		if (status.getCode() != 200) {
			switch(status.getCode()){
			case Constants.ECCEZIONE_COLLEGAMENTO_DATABASE: 
				System.err.println("Errore collegamento database");
				JOptionPane.showMessageDialog(null,
						"Connessione con il server persa",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			 	break;
			default: 
				System.err.println("Errore generico");
				break;
			}
		} else {
			Film[] films = gson.fromJson(json, Film[].class);			
			for(Film f: films) {
				listaFilm.add(f);
			}
		}
		return listaFilm;
	}
	
	public Film getFilm(String idFilm, String chiave) throws ResourceException, IOException {
		Film film;
		
		URI = localURL+"/film/"+idFilm+"&"+chiave;
		cr = new ClientResource(URI);
		json = cr.get().getText();
		status = cr.getStatus();
		if (status.getCode() != 200) {
			film = null;
			switch(status.getCode()){
			case Constants.ECCEZIONE_FILM_INESISTENTE: 
				System.err.println("Film non trovato");
				JOptionPane.showMessageDialog(null,
					new FilmSconosciutoException(idFilm).getMessage(),
					"Warning",
					JOptionPane.WARNING_MESSAGE);
				break;
			case Constants.ECCEZIONE_COLLEGAMENTO_DATABASE: 
				System.err.println("Errore collegamento database");
				JOptionPane.showMessageDialog(null,
						"Connessione con il server persa",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			 	break;
			default: 
				System.err.println("Errore generico");
				break;
			}
		} else {
			film = gson.fromJson(json, Film.class);			
		}
		return film;
	}
	
	public void addFilm(Film f, String chiave) throws ResourceException, IOException {
		URI = localURL+"/film/"+f.getCodFilm()+"&"+chiave;
		cr = new ClientResource(URI);
		json = cr.post(gson.toJson(f,Film.class)).getText();
		status = cr.getStatus();
		if (status.getCode() != 200) {
			switch(status.getCode()){
			case Constants.ECCEZIONE_PERMESSO_NEGATO: 
				System.err.println("Permesso negato");
				JOptionPane.showMessageDialog(null,
					"Non hai i permessi necessari per aggiungere un film",
					"Warning",
					JOptionPane.WARNING_MESSAGE);
				break;
			case Constants.ECCEZIONE_FILM_DUPLICATO: 
				System.err.println("Film duplicato");
				JOptionPane.showMessageDialog(null,
					new FilmDuplicatoException(f.getCodFilm()).getMessage(),
					"Warning",
					JOptionPane.WARNING_MESSAGE);
				break;
			case Constants.ECCEZIONE_COLLEGAMENTO_DATABASE: 
				System.err.println("Errore collegamento database");
				JOptionPane.showMessageDialog(null,
						"Connessione con il server persa",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			 	break;
			default: 
				System.err.println("Errore generico");
				break;
			}
		} else {
			Film film = gson.fromJson(json, Film.class);		
			System.out.println("E' stato aggiunto il film: " + film.toString());
			JOptionPane.showMessageDialog(null,
					"Hai aggiunto correttamente il film: " + film.getCodFilm(),
				    "Messaggio",
				    JOptionPane.INFORMATION_MESSAGE);
		}	
	}
	
	public void removeFilm(Film f, String chiave) throws ResourceException, IOException {
		URI = localURL+"/film/"+f.getCodFilm()+"&"+chiave;
		cr = new ClientResource(URI);
		json = cr.delete().getText();
		status = cr.getStatus();
		if (status.getCode() != 200) {
			switch(status.getCode()){
			case Constants.ECCEZIONE_PERMESSO_NEGATO: 
				System.err.println("Permesso negato");
				JOptionPane.showMessageDialog(null,
					"Non hai i permessi necessari per aggiungere un film",
					"Warning",
					JOptionPane.WARNING_MESSAGE);
				break;
			case Constants.ECCEZIONE_FILM_INESISTENTE: 
				System.err.println("Film sconosciuto");
				JOptionPane.showMessageDialog(null,
					new FilmSconosciutoException(f.getCodFilm()).getMessage(),
					"Warning",
					JOptionPane.WARNING_MESSAGE);
				break;
			case Constants.ECCEZIONE_COLLEGAMENTO_DATABASE: 
				System.err.println("Errore collegamento database");
				JOptionPane.showMessageDialog(null,
						"Connessione con il server persa",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			 	break;
			default: 
				System.err.println("Errore generico");
				break;
			}
		} else {
			Film film = gson.fromJson(json, Film.class);		
			System.out.println("E' stato cancellato il film: " + film.toString());
			JOptionPane.showMessageDialog(null,
					"Hai cancellato correttamente il film: "+film.getCodFilm(),
				    "Messaggio",
				    JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	//METODI SALA
	
	public ArrayList<Sala> getAllSale() throws ResourceException, IOException {
		ArrayList<Sala> listaSale = new ArrayList<Sala>();
		
		URI = localURL+"/sale/all";
		cr = new ClientResource(URI);
		json = cr.get().getText();
		status = cr.getStatus();
		if (status.getCode() != 200) {
			switch(status.getCode()){
			case Constants.ECCEZIONE_COLLEGAMENTO_DATABASE: 
				System.err.println("Errore collegamento database");
				JOptionPane.showMessageDialog(null,
						"Connessione con il server persa",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			 	break;
			default: 
				System.err.println("Errore generico");
				break;
			}
		} else {
			Sala[] sale = gson.fromJson(json, Sala[].class);			
			for(Sala s: sale) {
				listaSale.add(s);
			}
		}
		return listaSale;
	}
	
	public Sala getSala(String idSala, String chiave) throws ResourceException, IOException {
		Sala sala;
		
		URI = localURL+"/sale/"+idSala+"&"+chiave;
		cr = new ClientResource(URI);
		json = cr.get().getText();
		status = cr.getStatus();
		if (status.getCode() != 200) {
			sala = null;
			switch(status.getCode()){
			case Constants.ECCEZIONE_SALA_INESISTENTE: 
				System.err.println("Sala inesistente");
				JOptionPane.showMessageDialog(null,
					new SalaSconosciutaException(idSala).getMessage(),
					"Warning",
					JOptionPane.WARNING_MESSAGE);
				break;
			case Constants.ECCEZIONE_COLLEGAMENTO_DATABASE: 
				System.err.println("Errore collegamento database");
				JOptionPane.showMessageDialog(null,
						"Connessione con il server persa",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			 	break;
			default: 
				System.err.println("Errore generico");
				break;
			}
		} else {
			sala = gson.fromJson(json, Sala.class);			
		}
		return sala;
	}
	
	public void addSala(Sala s, String chiave) throws ResourceException, IOException {
		URI = localURL+"/sale/"+s.getNome()+"&"+chiave;
		cr = new ClientResource(URI);
		json = cr.post(gson.toJson(s,Sala.class)).getText();
		status = cr.getStatus();
		if (status.getCode() != 200) {
			switch(status.getCode()){
			case Constants.ECCEZIONE_PERMESSO_NEGATO: 
				System.err.println("Permesso negato");
				JOptionPane.showMessageDialog(null,
					"Non hai i permessi necessari per aggiungere un film",
					"Warning",
					JOptionPane.WARNING_MESSAGE);
				break;
			case Constants.ECCEZIONE_SALA_DUPLICATA: 
				System.err.println("Sala duplicata");
				JOptionPane.showMessageDialog(null,
					new SalaDuplicataException(s.getNome()).getMessage(),
					"Warning",
					JOptionPane.WARNING_MESSAGE);
				break;
			case Constants.ECCEZIONE_COLLEGAMENTO_DATABASE: 
				System.err.println("Errore collegamento database");
				JOptionPane.showMessageDialog(null,
						"Connessione con il server persa",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			 	break;
			default: 
				System.err.println("Errore generico");
				break;
			}
		} else {
			Sala sala = gson.fromJson(json, Sala.class);		
			System.out.println("E' stata aggiunta la sala: " + sala.toString());
			JOptionPane.showMessageDialog(null,
					"Hai aggiunto correttamente la sala: "+ sala.getNome(),
				    "Messaggio",
				    JOptionPane.INFORMATION_MESSAGE);
		}	
	}
	
	public void removeSala(Sala s, String chiave) throws ResourceException, IOException {
		URI = localURL+"/sale/"+s.getNome()+"&"+chiave;
		cr = new ClientResource(URI);
		json = cr.delete().getText();
		status = cr.getStatus();
		if (status.getCode() != 200) {
			switch(status.getCode()){
			case Constants.ECCEZIONE_PERMESSO_NEGATO: 
				System.err.println("Permesso negato");
				JOptionPane.showMessageDialog(null,
					"Non hai i permessi necessari per aggiungere un film",
					"Warning",
					JOptionPane.WARNING_MESSAGE);
				break;
			case Constants.ECCEZIONE_SALA_INESISTENTE: 
				System.err.println("Sala inesistente");
				JOptionPane.showMessageDialog(null,
					new SalaSconosciutaException(s.getNome()).getMessage(),
					"Warning",
					JOptionPane.WARNING_MESSAGE);
				break;
			case Constants.ECCEZIONE_COLLEGAMENTO_DATABASE: 
				System.err.println("Errore collegamento database");
				JOptionPane.showMessageDialog(null,
						"Connessione con il server persa",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			 	break;
			default: 
				System.err.println("Errore generico");
				break;
			}
		} else {
			Sala sala = gson.fromJson(json, Sala.class);		
			System.out.println("E' stata cancellata la sala: " + sala.toString());
			JOptionPane.showMessageDialog(null,
					"Hai cancellato con successo la sala: "+ sala.getNome(),
				    "Messaggio",
				    JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	//METODI PROGRAMMAZIONE
	
	public ArrayList<Programmazione> getAllProgrammazione() throws ResourceException, IOException {
		ArrayList<Programmazione> listaProgrammazioni = new ArrayList<Programmazione>();
		
		URI = localURL+"/programmazioni/all";
		cr = new ClientResource(URI);
		json = cr.get().getText();
		status = cr.getStatus();
		if (status.getCode() != 200) {
			switch(status.getCode()){
			case Constants.ECCEZIONE_COLLEGAMENTO_DATABASE: 
				System.err.println("Errore collegamento database");
				JOptionPane.showMessageDialog(null,
						"Connessione con il server persa",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			 	break;
			default: 
				System.err.println("Errore generico");
				break;
			}
		} else {
			Programmazione[] programmazioni = gson.fromJson(json, Programmazione[].class);			
			for(Programmazione p: programmazioni) {
				listaProgrammazioni.add(p);
			}
		}
		return listaProgrammazioni;
	}
	
	public Programmazione getProgrammazione(String idProgrammazione, String chiave) throws ResourceException, IOException {
		Programmazione prog;
		
		URI = localURL+"/programmazioni/"+idProgrammazione+"&"+chiave;
		cr = new ClientResource(URI);
		json = cr.get().getText();
		status = cr.getStatus();
		if (status.getCode() != 200) {
			prog = null;
			switch(status.getCode()){
			case Constants.ECCEZIONE_PROGRAMMAZIONE_INESISTENTE: 
				System.err.println("Programmazione inesistente");
				JOptionPane.showMessageDialog(null,
					new ProgrammazioneSconosciutaException(idProgrammazione).getMessage(),
					"Warning",
					JOptionPane.WARNING_MESSAGE);
				break;
			case Constants.ECCEZIONE_COLLEGAMENTO_DATABASE: 
				System.err.println("Errore collegamento database");
				JOptionPane.showMessageDialog(null,
						"Connessione con il server persa",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			 	break;
			default: 
				System.err.println("Errore generico");
				break;
			}
		} else {
			prog = gson.fromJson(json, Programmazione.class);			
		}
		return prog;
	}
	
	public void addProgrammazione(Programmazione p, String chiave) throws ResourceException, IOException {
		URI = localURL+"/programmazioni/"+p.getCodProgrammazione()+"&"+chiave;
		cr = new ClientResource(URI);
		json = cr.post(gson.toJson(p,Programmazione.class)).getText();
		status = cr.getStatus();
		if (status.getCode() != 200) {
			switch(status.getCode()){
			case Constants.ECCEZIONE_PROGRAMMAZIONE_DUPLICATA: 
				System.err.println("Programmazione già esistente");
				JOptionPane.showMessageDialog(null,
					new ProgrammazioneDuplicataException(p.getCodProgrammazione()).getMessage(),
					"Warning",
					JOptionPane.WARNING_MESSAGE);
				break;
			case Constants.ECCEZIONE_FILM_INESISTENTE: 
				System.err.println("Film inesistente");
				JOptionPane.showMessageDialog(null,
					new FilmSconosciutoException(p.getCodFilm()).getMessage(),
					"Warning",
					JOptionPane.WARNING_MESSAGE);
				break;
			case Constants.ECCEZIONE_SALA_INESISTENTE: 
				System.err.println("Sala inesistente");
				JOptionPane.showMessageDialog(null,
					new SalaSconosciutaException(p.getNomeSala()).getMessage(),
					"Warning",
					JOptionPane.WARNING_MESSAGE);
				break;
			case Constants.ECCEZIONE_COLLEGAMENTO_DATABASE: 
				System.err.println("Errore collegamento database");
				JOptionPane.showMessageDialog(null,
						"Connessione con il server persa",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			 	break;
			default: 
				System.err.println("Errore generico");
				break;
			}
		} else {
			Programmazione prog = gson.fromJson(json, Programmazione.class);		
			System.out.println("E' stata aggiunta la programmazione: " + prog.toString());
			JOptionPane.showMessageDialog(null,
					"Hai aggiunto correttamente la programmazione: "+prog.getCodProgrammazione(),
				    "Messaggio",
				    JOptionPane.INFORMATION_MESSAGE);
		}	
	}
	
	public void removeProgrammazione(Programmazione p, String chiave) throws ResourceException, IOException {
		URI = localURL+"/programmazioni/"+p.getCodProgrammazione()+"&"+chiave;
		cr = new ClientResource(URI);
		json = cr.delete().getText();
		status = cr.getStatus();
		if (status.getCode() != 200) {
			switch(status.getCode()){
			case Constants.ECCEZIONE_PROGRAMMAZIONE_INESISTENTE: 
				System.err.println("Programmazione inesistente");
				JOptionPane.showMessageDialog(null,
					new ProgrammazioneSconosciutaException(p.getCodProgrammazione()).getMessage(),
					"Warning",
					JOptionPane.WARNING_MESSAGE);
				break;
			case Constants.ECCEZIONE_COLLEGAMENTO_DATABASE: 
				System.err.println("Errore collegamento database");
				JOptionPane.showMessageDialog(null,
						"Connessione con il server persa",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			 	break;
			default: 
				System.err.println("Errore generico");
				break;
			}
		} else {
			Programmazione prog = gson.fromJson(json, Programmazione.class);		
			System.out.println("E' stata cancellata la programmazione: " + prog.toString());
			JOptionPane.showMessageDialog(null,
					"Hai rimosso correttamente la programmazione: "+ prog.getCodProgrammazione(),
				    "Messaggio",
				    JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	//METODI PRENOTAZIONE
	
	public ArrayList<Prenotazione> getAllPrenotazioni() throws ResourceException, IOException {
		ArrayList<Prenotazione> listaPrenotazioni = new ArrayList<Prenotazione>();
		
		URI = localURL+"/prenotazioni/all";
		cr = new ClientResource(URI);
		json = cr.get().getText();
		status = cr.getStatus();
		if (status.getCode() != 200) {
			switch(status.getCode()){
			case Constants.ECCEZIONE_PERMESSO_NEGATO: 
				System.err.println("Permesso negato");
				JOptionPane.showMessageDialog(null,
					"Non hai i permessi necessari per aggiungere un film",
					"Warning",
					JOptionPane.WARNING_MESSAGE);
				break;
			case Constants.ECCEZIONE_COLLEGAMENTO_DATABASE: 
				System.err.println("Errore collegamento database");
				JOptionPane.showMessageDialog(null,
						"Connessione con il server persa",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			 	break;
			default: 
				System.err.println("Errore generico");
				break;
			}
		} else {
			Prenotazione[] prenotazioni = gson.fromJson(json, Prenotazione[].class);			
			for(Prenotazione p: prenotazioni) {
				listaPrenotazioni.add(p);
			}
		}
		return listaPrenotazioni;
	}
	
	public Prenotazione getPrenotazione(String idPren, String chiave) throws ResourceException, IOException {
		Prenotazione pren;
		
		URI = localURL+"/prenotazioni/"+idPren+"&"+chiave;
		cr = new ClientResource(URI);
		json = cr.get().getText();
		status = cr.getStatus();
		if (status.getCode() != 200) {
			pren = null;
			switch(status.getCode()){
			case Constants.ECCEZIONE_PRENOTAZIONE_INESISTENTE: 
				System.err.println("Sala inesistente");
				JOptionPane.showMessageDialog(null,
					new SalaSconosciutaException(idPren).getMessage(),
					"Warning",
					JOptionPane.WARNING_MESSAGE);
				break;
			case Constants.ECCEZIONE_COLLEGAMENTO_DATABASE: 
				System.err.println("Errore collegamento database");
				JOptionPane.showMessageDialog(null,
						"Connessione con il server persa",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			 	break;
			default: 
				System.err.println("Errore generico");
				break;
			}
		} else {
			pren = gson.fromJson(json, Prenotazione.class);			
		}
		return pren;
	}
	
	public void addPrenotazione(Prenotazione p, String chiave) throws ResourceException, IOException {
		URI = localURL+"/prenotazioni/"+p.getCodPrenotazione()+"&"+chiave;
		cr = new ClientResource(URI);
		json = cr.post(gson.toJson(p,Prenotazione.class)).getText();
		status = cr.getStatus();
		if (status.getCode() != 200) {
			switch(status.getCode()){
			case Constants.ECCEZIONE_PRENOTAZIONE_DUPLICATA: 
				System.err.println("Prenotazione già esistente");
				JOptionPane.showMessageDialog(null,
					new PrenotazioneDuplicataException(p.getCodPrenotazione()).getMessage(),
					"Warning",
					JOptionPane.WARNING_MESSAGE);
				break;
			case Constants.ECCEZIONE_PROGRAMMAZIONE_INESISTENTE: 
				System.err.println("Programmazione inesistente");
				JOptionPane.showMessageDialog(null,
					new ProgrammazioneSconosciutaException(p.getCodProgrammazione()).getMessage(),
					"Warning",
					JOptionPane.WARNING_MESSAGE);
				break;
			case Constants.ECCEZIONE_POSTI_INSUFFICIENTI: 
				System.err.println("Posti terminati");
				JOptionPane.showMessageDialog(null,
					new PostiTerminatiException().getMessage(),
					"Warning",
					JOptionPane.WARNING_MESSAGE);
				break;
			case Constants.ECCEZIONE_COLLEGAMENTO_DATABASE: 
				System.err.println("Errore collegamento database");
				JOptionPane.showMessageDialog(null,
						"Connessione con il server persa",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			 	break;
			default: 
				System.err.println("Errore generico");
				break;
			}
		} else {
			Prenotazione pren = gson.fromJson(json, Prenotazione.class);		
			System.out.println("E' stata aggiunta la prenotazione: " + pren.toString());
			JOptionPane.showMessageDialog(null,
					"Prenotazione effettuata con successo",
				    "Messaggio",
				    JOptionPane.INFORMATION_MESSAGE);
		}	
	}
	
	public void removePrenotazione(Prenotazione p, String chiave) throws ResourceException, IOException {
		URI = localURL+"/prenotazioni/"+p.getCodPrenotazione()+"&"+chiave;
		cr = new ClientResource(URI);
		json = cr.delete().getText();
		status = cr.getStatus();
		if (status.getCode() != 200) {
			switch(status.getCode()){
			case Constants.ECCEZIONE_PRENOTAZIONE_INESISTENTE: 
				System.err.println("Prenotazione inesistente");
				JOptionPane.showMessageDialog(null,
					new PrenotazioneSconosciutaException(p.getCodPrenotazione()).getMessage(),
					"Warning",
					JOptionPane.WARNING_MESSAGE);
				break;
			case Constants.ECCEZIONE_COLLEGAMENTO_DATABASE: 
				System.err.println("Errore collegamento database");
				JOptionPane.showMessageDialog(null,
						"Connessione con il server persa",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			 	break;
			default: 
				System.err.println("Errore generico");
				break;
			}
		} else {
			Prenotazione pren = gson.fromJson(json, Prenotazione.class);		
			System.out.println("E' stata cancellata la prenotazione: " + pren.toString());
			JOptionPane.showMessageDialog(null,
					"E' stata eliminata la prenotazione",
				    "Messaggio",
				    JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
