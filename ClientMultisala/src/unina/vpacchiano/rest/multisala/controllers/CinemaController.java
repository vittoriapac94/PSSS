package unina.vpacchiano.rest.multisala.controllers;

import java.io.IOException;
import java.util.ArrayList;

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
	
	public String login(String username, String password) throws UtenteSconosciutoException, IOException {	
		String key;
		URI = localURL+"/users/login/"+username+"&"+password;
		cr = new ClientResource(URI);
		json = cr.get().getText();
		status = cr.getStatus();
		if (status.getCode() != 200) {
			key = null;
			switch(status.getCode()){
			case Constants.ECCEZIONE_UTENTE_INESISTENTE:
				System.out.println(new UtenteSconosciutoException(username).getMessage());
				break;
			case Constants.ECCEZIONE_PASSWORD_ERRATA: 
				System.err.println("Password errata");
			 	break;
			default: 
				System.err.println("Errore generico");
				break;
			}
		} else {
			key = gson.fromJson(json, String.class);
			System.out.println(key);
		}
		return key;
	}
	
	public void registraUtente(Utente u) throws ResourceException, IOException {
		URI = localURL+"/users/login/"+u.getNomeUtente()+"&"+u.getPassword();
		cr = new ClientResource(URI);
		json = cr.post(gson.toJson(u,Utente.class)).getText();
		status = cr.getStatus();
		if (status.getCode() != 200) {
			System.exit(status.getCode());
		} else {
			Utente utente = gson.fromJson(json, Utente.class);		
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
			System.exit(status.getCode());
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
			System.exit(status.getCode());
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
			System.exit(status.getCode());
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
			System.exit(status.getCode());
		} else {
			Film film = gson.fromJson(json, Film.class);		
			System.out.println("E' stato aggiunto l'utente: " + film.toString());
		}	
	}
	
	public void removeFilm(Film f, String chiave) throws ResourceException, IOException {
		URI = localURL+"/film/"+f.getCodFilm()+"&"+chiave;
		cr = new ClientResource(URI);
		json = cr.delete().getText();
		status = cr.getStatus();
		if (status.getCode() != 200) {
			System.exit(status.getCode());
		} else {
			Film film = gson.fromJson(json, Film.class);		
			System.out.println("E' stato cancellato il film: " + film.toString());
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
			System.exit(status.getCode());
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
			System.exit(status.getCode());
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
			System.exit(status.getCode());
		} else {
			Sala sala = gson.fromJson(json, Sala.class);		
			System.out.println("E' stata aggiunta la sala: " + sala.toString());
		}	
	}
	
	public void removeSala(Sala s, String chiave) throws ResourceException, IOException {
		URI = localURL+"/sale/"+s.getNome()+"&"+chiave;
		cr = new ClientResource(URI);
		json = cr.delete().getText();
		status = cr.getStatus();
		if (status.getCode() != 200) {
			System.exit(status.getCode());
		} else {
			Sala sala = gson.fromJson(json, Sala.class);		
			System.out.println("E' stata cancellata la sala: " + sala.toString());
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
			System.exit(status.getCode());
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
			System.exit(status.getCode());
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
			System.exit(status.getCode());
		} else {
			Programmazione prog = gson.fromJson(json, Programmazione.class);		
			System.out.println("E' stata aggiunta la programmazione: " + prog.toString());
		}	
	}
	
	public void removeProgrammazione(Programmazione p, String chiave) throws ResourceException, IOException {
		URI = localURL+"/programmazioni/"+p.getCodProgrammazione()+"&"+chiave;
		cr = new ClientResource(URI);
		json = cr.delete().getText();
		status = cr.getStatus();
		if (status.getCode() != 200) {
			System.exit(status.getCode());
		} else {
			Programmazione prog = gson.fromJson(json, Programmazione.class);		
			System.out.println("E' stata cancellata la programmazione: " + prog.toString());
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
			System.exit(status.getCode());
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
			System.exit(status.getCode());
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
			System.exit(status.getCode());
		} else {
			Prenotazione pren = gson.fromJson(json, Prenotazione.class);		
			System.out.println("E' stata aggiunta la prenotazione: " + pren.toString());
		}	
	}
	
	public void removePrenotazione(Prenotazione p, String chiave) throws ResourceException, IOException {
		URI = localURL+"/prenotazioni/"+p.getCodPrenotazione()+"&"+chiave;
		cr = new ClientResource(URI);
		json = cr.delete().getText();
		status = cr.getStatus();
		if (status.getCode() != 200) {
			System.exit(status.getCode());
		} else {
			Prenotazione pren = gson.fromJson(json, Prenotazione.class);		
			System.out.println("E' stata cancellata la prenotazione: " + pren.toString());
		}
	}

}
