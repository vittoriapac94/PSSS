package unisannio.vpacchiano.rest.multisala.server;

import java.sql.SQLException;

import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;

import unisannio.vpacchiano.rest.multisala.domain.Utente;
import unisannio.vpacchiano.rest.multisala.thesystem.GestoreCinema;
import unisannio.vpacchiano.rest.multisala.thesystem.UtenteDuplicatoException;
import unisannio.vpacchiano.rest.multisala.thesystem.UtenteSconosciutoException;


public class MultisalaLoginResourceJSon extends ServerResource {
	
	@Get
	public String getUtente(){
		Gson gson = new Gson();
		GestoreCinema gest;
		Status status;
		
		try {
			gest = GestoreCinema.getGestoreCinema();
			Utente u = gest.getUtenteByUsername(this.getAttribute("username"));
			if(u.getPassword().equals(this.getAttribute("password"))){
					String key = gest.getChiaveByUtente(u.getNomeUtente());
					return gson.toJson(key, String.class);
			}
			else{
				status = new Status(Constants.ECCEZIONE_PASSWORD_ERRATA,"Password errata","La password inserita non � corretta",null);
				setStatus(status);
				return gson.toJson(status, Status.class);
			}
		} catch (UtenteSconosciutoException e) {
			status = new Status(Constants.ECCEZIONE_UTENTE_INESISTENTE,"UserNotFound","L'utente non � presente nel database",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		} catch (SQLException ex) {
			status = new Status(Constants.ECCEZIONE_COLLEGAMENTO_DATABASE,"DatabaseError","Errore accesso al DataBase",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		}
	}
		
		
	@Post	
	public String addUtente(String body) {
		Gson gson = new Gson();
		Utente nuovo = gson.fromJson(body, Utente.class);
		GestoreCinema gest;
		Status status;
		
		try {
			gest = GestoreCinema.getGestoreCinema();
			return gson.toJson(gest.addUtente(nuovo),Utente.class);
		

		} catch (UtenteDuplicatoException e) {
 			status = new Status(Constants.ECCEZIONE_UTENTE_DUPLICATO,"Utente Duplicato","L'utente � gi� presente nel database",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		}
		catch (UtenteSconosciutoException e) {
			status = new Status(Constants.ECCEZIONE_UTENTE_INESISTENTE,"UtenteNotFound","L'utente non � stato trovato nel database",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		}catch (SQLException ex) {
			status = new Status(Constants.ECCEZIONE_COLLEGAMENTO_DATABASE,"DatabaseError","Errore accesso al DataBase",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		}
	
	}

		
	}

