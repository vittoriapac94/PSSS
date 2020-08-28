package unina.vpacchiano.rest.multisala.server;

import java.sql.SQLException;

import org.restlet.data.Status;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;

import unina.vpacchiano.rest.multisala.domain.Sala;
import unina.vpacchiano.rest.multisala.domain.Utente;
import unina.vpacchiano.rest.multisala.thesystem.ChiaveSconosciutaException;
import unina.vpacchiano.rest.multisala.server.GestoreCinema;
import unina.vpacchiano.rest.multisala.thesystem.SalaDuplicataException;
import unina.vpacchiano.rest.multisala.thesystem.SalaSconosciutaException;
import unina.vpacchiano.rest.multisala.thesystem.UtenteSconosciutoException;



public class MultisalaSalaResourceJSon extends ServerResource {

	@Get
	public String getSala(){
		Gson gson = new Gson();
		GestoreCinema gest = null;
		Status status;
		try {
			gest = GestoreCinema.getGestoreCinema();
			return gson.toJson(gest.getSala(this.getAttribute("nome")),Sala.class);

		} catch (SalaSconosciutaException e) {
			status = new Status(Constants.ECCEZIONE_SALA_INESISTENTE,"SalaNotFound","La sala non � stata trovata",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		} catch (SQLException ex) {
			status = new Status(Constants.ECCEZIONE_COLLEGAMENTO_DATABASE,"DatabaseError","Errore accesso al DataBase",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		}
	}
	
	@Post
	public String addSala(String body){
		Gson gson = new Gson();
		Sala sala = gson.fromJson(body, Sala.class);
		GestoreCinema gest = null;
		Status status;
		
		try {
			gest = GestoreCinema.getGestoreCinema();
			Utente u = gest.getUtenteByChiave((String)this.getAttribute("chiave"));
			if(u.isAdmin()){
				return gson.toJson(gest.addSala(sala),Sala.class);			}
			else{
				status = new Status(Constants.ECCEZIONE_PERMESSO_NEGATO,"� negato l'accesso","Accesso negato",null);
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
			
		} catch (SalaSconosciutaException e) {
			status = new Status(Constants.ECCEZIONE_SALA_INESISTENTE,"SalaNotFound","La sala non � stata trovata",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
			
		} catch (SalaDuplicataException e) {
			status = new Status(Constants.ECCEZIONE_SALA_DUPLICATA,"Sala Duplicata","la sala � gi� presente nel database",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		} catch (ChiaveSconosciutaException e) {
			status = new Status(Constants.ECCEZIONE_CHIAVE_INESISTENTE,"UnknownKey","Chiave sconosciuta",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		}
		
		
	}
	
	@Delete
	public String deleteSala(){
		Gson gson = new Gson();
		GestoreCinema gest = null;
		Status status;
		try{
			gest = GestoreCinema.getGestoreCinema();
			String chiavejson= (String)this.getAttribute("chiave");
			Utente u = gest.getUtenteByChiave(chiavejson);
			if(u.isAdmin()){
				return gson.toJson(gest.removeSala(this.getAttribute("nome")),Sala.class);
			}
			else{
				status = new Status(Constants.ECCEZIONE_PERMESSO_NEGATO,"� negato l'accesso","Accesso negato",null);
				setStatus(status);
				return gson.toJson(status, Status.class);
			}
		}catch (SalaSconosciutaException e) {
			status = new Status(Constants.ECCEZIONE_SALA_INESISTENTE,"SalaNotFound","La sala non � stata trovata",null);
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
	

