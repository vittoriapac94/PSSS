package unina.vpacchiano.rest.multisala.server;

import java.sql.SQLException;

import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;

import unina.vpacchiano.rest.multisala.domain.Programmazione;
import unina.vpacchiano.rest.multisala.server.Constants;
import unina.vpacchiano.rest.multisala.server.GestoreCinema;

public class MultisalaProgrammazioniAllResourceJSon extends ServerResource {
	
	@Get
	public String getAllProgrammazioni(){
		Gson gson = new Gson();
		GestoreCinema gest = null;
		Status status;
		Programmazione[] prog = null;
		try{
			gest = GestoreCinema.getGestoreCinema();
			prog=gest.getAllProgrammazioni();
		}
		catch(SQLException e){
			status = new Status(Constants.ECCEZIONE_COLLEGAMENTO_DATABASE,"DatabaseError","Errore accesso al DataBase",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		}
		return gson.toJson(prog,Programmazione[].class); 
	}
		
	
	

}
