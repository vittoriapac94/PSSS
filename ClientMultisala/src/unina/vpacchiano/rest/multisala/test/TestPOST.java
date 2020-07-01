package unina.vpacchiano.rest.multisala.test;

import java.io.IOException;

import org.restlet.data.Status;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import unina.vpacchiano.rest.multisala.domain.Film;


public class TestPOST {

	public static void main(String[] args) throws ResourceException, IOException {
		// TODO Auto-generated method stub
		ClientResource cr;
		Gson gson = new Gson();
		Status status;
		String URI;
		String json;
		
		// Cancel the archive file to test how state handling

		// ADD A PERSON
		URI = "http://localhost:8182/film/F02&56b211bf00faf47d1c3cb7f28104fc8c";    // use a wrong URI to test exit path
		System.out.println("Testing add a film with URI: " + URI);
		cr = new ClientResource(URI);
		Film f = new Film("F02", "La carica dei 101", "Mio Zio", 1999, "Cartone animato");
		json = cr.post(gson.toJson(f,Film.class)).getText();
		status = cr.getStatus();
		if (status.getCode() != 200) {
			JsonObject o = gson.fromJson(json, JsonObject.class);
			System.out.println(o.toString());
			System.exit(status.getCode());
		} else {
			f = gson.fromJson(json, Film.class);		
			System.out.println("E' stato aggiunto il film: " + f.toString());
		}	
	}	

}
