package unina.vpacchiano.rest.multisala.server;

import java.sql.SQLException;

import org.restlet.data.Status;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;

import unina.vpacchiano.rest.multisala.domain.Programmazione;
import unina.vpacchiano.rest.multisala.domain.Utente;
import unina.vpacchiano.rest.multisala.thesystem.ChiaveSconosciutaException;
import unina.vpacchiano.rest.multisala.server.GestoreCinema;
import unina.vpacchiano.rest.multisala.thesystem.ProgrammazioneDuplicataException;
import unina.vpacchiano.rest.multisala.thesystem.ProgrammazioneSconosciutaException;
import unina.vpacchiano.rest.multisala.thesystem.UtenteSconosciutoException;

public class MultisalaProgrammazioniResourceJSon extends ServerResource {

	@Get
	public String getProgrammazione(){
		Gson gson = new Gson();
		GestoreCinema gest = null;
		Status status;
		try {
			gest = GestoreCinema.getGestoreCinema();
			return gson.toJson(gest.getProgrammazione(this.getAttribute("nome")),Programmazione.class);

		} catch (ProgrammazioneSconosciutaException e) {
			status = new Status(Constants.ECCEZIONE_PROGRAMMAZIONE_INESISTENTE,"ProgrammazioneNotFound","La programmazione non � stata trovatao",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		} catch (SQLException ex) {
			status = new Status(Constants.ECCEZIONE_COLLEGAMENTO_DATABASE,"DatabaseError","Errore accesso al DataBase",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		}
	}
	
	@Post
	public String addProgrammazione(String body){
		Gson gson = new Gson();
		Programmazione prog = gson.fromJson(body, Programmazione.class);
		GestoreCinema gest = null;
		Status status;
		
		try {
			gest = GestoreCinema.getGestoreCinema();
			Utente u = gest.getUtenteByChiave((String)this.getAttribute("chiave"));
			if(u.isAdmin()){
				return gson.toJson(gest.addProgrammazione(prog),Programmazione.class);			}
			else{
				status = new Status(Constants.ECCEZIONE_PERMESSO_NEGATO,"� negato l'accesso","Accesso negato",null);
				setStatus(status);
				return gson.toJson(status, Status.class);
			}
		} catch (SQLException ex) {
			status = new Status(Constants.ECCEZIONE_COLLEGAMENTO_DATABASE,"DatabaseError","Errore accesso al DataBase",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
			
		} catch (UtenteSconosciutoException e) {
			status = new Status(Constants.ECCEZIONE_UTENTE_INESISTENTE,"UnknownUser","Utente non conosciuto",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
			
		} catch (ProgrammazioneSconosciutaException e) {
			status = new Status(Constants.ECCEZIONE_PROGRAMMAZIONE_INESISTENTE,"ProgrammazioneNotFound","La programmazione non � stata trovato",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
			
		} catch (ProgrammazioneDuplicataException e) {
			status = new Status(Constants.ECCEZIONE_PROGRAMMAZIONE_DUPLICATA,"Programmazione Duplicata","La programmazione � gi� presente nel database",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
			
		} catch (ChiaveSconosciutaException e) {
			status = new Status(Constants.ECCEZIONE_CHIAVE_INESISTENTE,"UnknownKey","Chiave sconosciuta",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		}
		
		
	}
	
	@Delete
	public String deleteProgrammazione(){
		Gson gson = new Gson();
		GestoreCinema gest = null;
		Status status;
		try{
			gest = GestoreCinema.getGestoreCinema();
			String chiavejson= (String)this.getAttribute("chiave");
			Utente u = gest.getUtenteByChiave(chiavejson);
			if(u.isAdmin()){
				return gson.toJson(gest.removeProgrammazione(this.getAttribute("nome")), Programmazione.class);
			}
			else{
				status = new Status(Constants.ECCEZIONE_PERMESSO_NEGATO,"� negato l'accesso","Accesso negato",null);
				setStatus(status);
				return gson.toJson(status, Status.class);
			}
		}catch (ProgrammazioneSconosciutaException e) {
			status = new Status(Constants.ECCEZIONE_PROGRAMMAZIONE_INESISTENTE,"ProgrammazioneNotFound","La programmazione non � stata trovata",null);
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
