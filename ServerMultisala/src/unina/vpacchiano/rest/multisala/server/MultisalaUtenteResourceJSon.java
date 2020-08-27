package unina.vpacchiano.rest.multisala.server;

import java.sql.SQLException;

import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;

import unina.vpacchiano.rest.multisala.domain.Utente;
import unina.vpacchiano.rest.multisala.thesystem.ChiaveSconosciutaException;
import unina.vpacchiano.rest.multisala.server.GestoreCinema;
import unina.vpacchiano.rest.multisala.thesystem.UtenteSconosciutoException;

public class MultisalaUtenteResourceJSon extends ServerResource {
	
	@Get
	public String getUtenteByChiave(){
		Gson gson = new Gson();
		GestoreCinema gest = null;
		Status status;
		try {
			gest = GestoreCinema.getGestoreCinema();
			return gson.toJson(gest.getUtenteByChiave(this.getAttribute("chiave")),Utente.class);

		} catch (UtenteSconosciutoException e) {
			status = new Status(Constants.ECCEZIONE_UTENTE_INESISTENTE,"UserNotFound","L'utente non � stato trovatao",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		} catch (ChiaveSconosciutaException e) {
			status = new Status(Constants.ECCEZIONE_CHIAVE_INESISTENTE,"KayNotFound","L'utente non � stato trovatao",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		} catch (SQLException ex) {
			status = new Status(Constants.ECCEZIONE_COLLEGAMENTO_DATABASE,"DatabaseError","Errore accesso al DataBase",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		}
	}

}
