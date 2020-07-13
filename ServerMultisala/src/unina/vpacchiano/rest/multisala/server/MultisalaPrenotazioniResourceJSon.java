package unina.vpacchiano.rest.multisala.server;

import java.sql.SQLException;

import org.restlet.data.Status;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;

import unina.vpacchiano.rest.multisala.domain.Prenotazione;
import unina.vpacchiano.rest.multisala.domain.Utente;
import unina.vpacchiano.rest.multisala.thesystem.ChiaveSconosciutaException;
import unina.vpacchiano.rest.multisala.thesystem.GestoreCinema;
import unina.vpacchiano.rest.multisala.thesystem.PostiTerminatiException;
import unina.vpacchiano.rest.multisala.thesystem.PrenotazioneDuplicataException;
import unina.vpacchiano.rest.multisala.thesystem.PrenotazioneSconosciutaException;
import unina.vpacchiano.rest.multisala.thesystem.ProgrammazioneSconosciutaException;
import unina.vpacchiano.rest.multisala.thesystem.SalaSconosciutaException;
import unina.vpacchiano.rest.multisala.thesystem.UtenteSconosciutoException;

public class MultisalaPrenotazioniResourceJSon extends ServerResource {
	
	@Get
	public String getPrenotazione(){
		Gson gson = new Gson();
		GestoreCinema gest = null;
		Status status;
		try {
			gest = GestoreCinema.getGestoreCinema();
			return gson.toJson(gest.getPrenotazione(this.getAttribute("nome")),Prenotazione.class);

		} catch (PrenotazioneSconosciutaException e) {
			status = new Status(Constants.ECCEZIONE_PRENOTAZIONE_INESISTENTE,"ProgrammazioneNotFound","La programmazione non � stata trovatao",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		} catch (SQLException ex) {
			status = new Status(Constants.ECCEZIONE_COLLEGAMENTO_DATABASE,"DatabaseError","Errore accesso al DataBase",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		}
	}
	
	@Post
	public String addPrenotazione(String body){
		Gson gson = new Gson();
		Prenotazione pr = gson.fromJson(body, Prenotazione.class);
		GestoreCinema gest = null;
		Status status;
		
		try {
			gest = GestoreCinema.getGestoreCinema();
			Utente u = gest.getUtenteByChiave((String)this.getAttribute("chiave"));
			if(u.isAdmin()){
				return gson.toJson(gest.addPrenotazione(pr),Prenotazione.class);			}
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
			
		} catch (PrenotazioneSconosciutaException e) {
			status = new Status(Constants.ECCEZIONE_PRENOTAZIONE_INESISTENTE,"PrenotazioneNotFound","La prenotazione non � stata trovata",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
			
		} catch (PrenotazioneDuplicataException e) {
			status = new Status(Constants.ECCEZIONE_PRENOTAZIONE_DUPLICATA,"ProgrammazioneDuplicata","La prenotazione � gi� presente nel database",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
			
		} catch (ChiaveSconosciutaException e) {
			status = new Status(Constants.ECCEZIONE_CHIAVE_INESISTENTE,"UnknownKey","Chiave sconosciuta",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
			
		} catch (ProgrammazioneSconosciutaException e) {
			status = new Status(Constants.ECCEZIONE_PROGRAMMAZIONE_INESISTENTE,"Programmazione sconosciuta","La programmazione non esiste",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
			
		} catch (SalaSconosciutaException e) {
			status = new Status(Constants.ECCEZIONE_SALA_INESISTENTE,"UnknownSala","Sala sconosciuta",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
			
		} catch (PostiTerminatiException e) {
			status = new Status(Constants.ECCEZIONE_POSTI_INSUFFICIENTI,"Posti terminati","I posti sono terminati",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		}
		
		
	}
	
	@Delete
	public String deletePrenotazione(){
		Gson gson = new Gson();
		GestoreCinema gest = null;
		Status status;
		try{
			gest = GestoreCinema.getGestoreCinema();
			String chiavejson= (String)this.getAttribute("chiave");
			Utente u = gest.getUtenteByChiave(chiavejson);
			if(u.isAdmin()){
				return gson.toJson(gest.removePrenotazione(this.getAttribute("nome")), Prenotazione.class);
			}
			else{
				status = new Status(Constants.ECCEZIONE_PERMESSO_NEGATO,"� negato l'accesso","Accesso negato",null);
				setStatus(status);
				return gson.toJson(status, Status.class);
			}
		}catch (PrenotazioneSconosciutaException e) {
			status = new Status(Constants.ECCEZIONE_PRENOTAZIONE_INESISTENTE,"PrenotazioneNotFound","La prenotazione non � stata trovata",null);
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
