package unina.vpacchiano.rest.multisala.test;

import java.io.IOException;

import org.restlet.data.Status;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import unina.vpacchiano.rest.multisala.domain.Film;

public class TestGET {

	public static void main(String[] args) throws ResourceException, IOException {
		// TODO Auto-generated method stub
		
		ClientResource cr;
		Gson gson = new Gson();
		Status status;
		String URI;
		String json;
		
		// GET A FILM
		URI = "http://localhost:8182/film/"+"AF021&null"; // use a wrong URI (> size) to test exit path
		System.out.println("Testing get a person with URI: " + URI);
		cr = new ClientResource(URI);
		json = cr.get().getText();
		status = cr.getStatus();
		if (status.getCode() != 200) {
			JsonObject o = gson.fromJson(json, JsonObject.class);
			System.out.println(o.toString());
			System.exit(status.getCode());
		} else {
			JsonObject o = gson.fromJson(json, JsonObject.class);		
			System.out.println(o.toString());
			
			Film f = gson.fromJson(json, Film.class);
			System.out.println(f.toString());
			
			//JsonElement jNome = o.get("nome");
			//String nome = jNome.getAsString();
			//System.out.println(nome);
			
			
		}

	}

}
