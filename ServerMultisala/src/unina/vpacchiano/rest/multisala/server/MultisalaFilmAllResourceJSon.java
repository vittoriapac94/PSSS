package unina.vpacchiano.rest.multisala.server;

import java.sql.SQLException;

import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;

import unina.vpacchiano.rest.multisala.domain.Film;
import unina.vpacchiano.rest.multisala.server.Constants;
import unina.vpacchiano.rest.multisala.thesystem.GestoreCinema;

public class MultisalaFilmAllResourceJSon extends ServerResource {
	
	@Get
	public String getAllFilm(){
		Gson gson = new Gson();
		GestoreCinema gest = null;
		Status status;
		Film[] film = null;
		try{
			gest = GestoreCinema.getGestoreCinema();
			film = gest.getAllFilm();
		}
		catch(SQLException e){
			status = new Status(Constants.ECCEZIONE_COLLEGAMENTO_DATABASE,"DatabaseError","Errore accesso al DataBase",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		}
		return gson.toJson(film,Film[].class); 
	}

}

