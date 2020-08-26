package unina.vpacchiano.rest.multisala.server;

import java.sql.SQLException;

import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;

import unina.vpacchiano.rest.multisala.domain.Sala;
import unina.vpacchiano.rest.multisala.server.GestoreCinema;


public class MultisalaSaleAllResourceJSon extends ServerResource {

	@Get
	public String getAllSale(){
		Gson gson = new Gson();
		GestoreCinema gest = null;
		Status status;
		Sala[] sale = null;
		try{
			gest = GestoreCinema.getGestoreCinema();
			sale=gest.getAllSale();
		}
		catch(SQLException e){
			status = new Status(Constants.ECCEZIONE_COLLEGAMENTO_DATABASE,"DatabaseError","Errore accesso al DataBase",null);
			setStatus(status);
			return gson.toJson(status, Status.class);
		}
		return gson.toJson(sale,Sala[].class); 
	}
		
	}

