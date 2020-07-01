package unisannio.vpacchiano.rest.multisala.server;

import java.sql.SQLException;

import org.restlet.data.Status;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import unisannio.vpacchiano.rest.multisala.domain.Film;
import unisannio.vpacchiano.rest.multisala.domain.Utente;
import unisannio.vpacchiano.rest.multisala.thesystem.ChiaveSconosciutaException;
import unisannio.vpacchiano.rest.multisala.thesystem.FilmDuplicatoException;
import unisannio.vpacchiano.rest.multisala.thesystem.FilmSconosciutoException;
import unisannio.vpacchiano.rest.multisala.thesystem.GestoreCinema;
import unisannio.vpacchiano.rest.multisala.thesystem.UtenteSconosciutoException;

import com.google.gson.Gson;

public class MultisalaFilmResourceJSon extends ServerResource {
	
	@Get
	public String getFilm(){
		Gson gson = new Gson();
		GestoreCinema gest = null;
		Status status;
		try {
			gest = GestoreCinema.getGestoreCinema();
			return gson.toJson(gest.getFilm(this.getAttribute("nome")),Film.class);

		} catch (FilmSconosciutoException e) {
			status = new Status(Constants.ECCEZIONE_FILM_INESISTENTE,"FilmNotFound","Il film non è stato trovatao",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		} catch (SQLException ex) {
			status = new Status(Constants.ECCEZIONE_COLLEGAMENTO_DATABASE,"DatabaseError","Errore accesso al DataBase",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		}
	}
	
	@Post
	public String addFilm(String body){
		Gson gson = new Gson();
		Film film = gson.fromJson(body, Film.class);
		GestoreCinema gest = null;
		Status status;
		
		try {
			gest = GestoreCinema.getGestoreCinema();
			Utente u = gest.getUtenteByChiave((String)this.getAttribute("chiave"));
			if(u.isAdmin()){
				return gson.toJson(gest.addFilm(film),Film.class);			}
			else{
				status = new Status(Constants.ECCEZIONE_PERMESSO_NEGATO,"è negato l'accesso","Accesso negato",null);
				setStatus(status);
				return gson.toJson(status, Status.class);
			}
		} catch (SQLException e) {
			status = new Status(Constants.ECCEZIONE_COLLEGAMENTO_DATABASE,"DatabaseError","Errore accesso al DataBase",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
			
		} catch (UtenteSconosciutoException e) {
			status = new Status(Constants.ECCEZIONE_UTENTE_INESISTENTE,"UnknownUser","Utente non conosciuto",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
			
		} catch (FilmSconosciutoException e) {
			status = new Status(Constants.ECCEZIONE_FILM_INESISTENTE,"FilmNotFound","Il film non è stato trovato",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
			
		} catch (FilmDuplicatoException e) {
			status = new Status(Constants.ECCEZIONE_FILM_DUPLICATO,"Film Duplicato","Il film è già presente nel database",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		} catch (ChiaveSconosciutaException e) {
			status = new Status(Constants.ECCEZIONE_CHIAVE_INESISTENTE,"UnknownKey","Chiave sconosciuta",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		}
		
		
	}
	
	@Delete
	public String deleteFilm(){
		Gson gson = new Gson();
		GestoreCinema gest = null;
		Status status;
		try{
			gest = GestoreCinema.getGestoreCinema();
			String chiavejson= (String)this.getAttribute("chiave");
			Utente u = gest.getUtenteByChiave(chiavejson);
			if(u.isAdmin()){
				return gson.toJson(gest.removeFilm(this.getAttribute("nome")), Film.class);
			}
			else{
				status = new Status(Constants.ECCEZIONE_PERMESSO_NEGATO,"è negato l'accesso","Accesso negato",null);
				setStatus(status);
				return gson.toJson(status, Status.class);
			}
		}catch (FilmSconosciutoException e) {
			status = new Status(Constants.ECCEZIONE_FILM_INESISTENTE,"FilmNotFound","Il film non è stata trovato",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		}catch (SQLException ex) {
			status = new Status(Constants.ECCEZIONE_COLLEGAMENTO_DATABASE,"DatabaseError","Errore accesso al DataBase",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		} catch (UtenteSconosciutoException e) {
			status = new Status(Constants.ECCEZIONE_UTENTE_INESISTENTE,"UnknownUser","Utente non conosciuto",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		} catch (ChiaveSconosciutaException e) {
			status = new Status(Constants.ECCEZIONE_CHIAVE_INESISTENTE,"UnknownKey","Chiave sconosciuta",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		}
	}

}
