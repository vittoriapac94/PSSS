package unina.vpacchiano.rest.multisala.server;

import java.sql.SQLException;

import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;

import unina.vpacchiano.rest.multisala.domain.Prenotazione;
import unina.vpacchiano.rest.multisala.thesystem.GestoreCinema;

public class MultisalaPrenotazioniAllResourceJSon extends ServerResource {
	
	@Get
	public String getAllPrenotazioni(){
		Gson gson = new Gson();
		GestoreCinema gest = null;
		Status status;
		Prenotazione[] pren = null;
		try{
			gest = GestoreCinema.getGestoreCinema();
			pren = gest.getAllPrenotazioni();
		}
		catch(SQLException e){
			status = new Status(Constants.ECCEZIONE_COLLEGAMENTO_DATABASE,"DatabaseError","Errore accesso al DataBase",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		}
		return gson.toJson(pren,Prenotazione[].class); 
	}


}
