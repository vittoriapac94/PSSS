package unina.vpacchiano.rest.multisala.test;

import java.io.IOException;
import java.util.ArrayList;

import org.restlet.data.Status;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import unina.vpacchiano.rest.multisala.controllers.CinemaController;
import unina.vpacchiano.rest.multisala.domain.Chiave;
import unina.vpacchiano.rest.multisala.domain.Film;
import unina.vpacchiano.rest.multisala.domain.Prenotazione;
import unina.vpacchiano.rest.multisala.domain.Programmazione;
import unina.vpacchiano.rest.multisala.domain.Sala;
import unina.vpacchiano.rest.multisala.domain.Utente;

public class TestGET {

	public static void main(String[] args) throws ResourceException, IOException {
		// TODO Auto-generated method stub
		
		ClientResource cr;
		Gson gson = new Gson();
		Status status;
		String URI;
		String json;
		
		CinemaController cc = new CinemaController();
		
		//TESTATI
		
		// Login funziona
		//String chiave = cc.login("luigino", "luigino17");
		
		// Get film funziona
		//ArrayList<Film> listaFilm = cc.getAllFilms();
		//System.out.println(listaFilm.toString());
		//Film f = cc.getFilm("F02", null);
		//System.out.println(f.toString());
		
		//Get sale funziona
		//ArrayList<Sala> ls = cc.getAllSale();
		//System.out.println(ls.toString());
		//Sala s = cc.getSala("calliope", null);
		//System.out.println(s.toString());
		
		//Get programmazioni funziona
		//ArrayList<Programmazione> lp = cc.getAllProgrammazione();
		//System.out.println(lp.toString());
		//Programmazione p = cc.getProgrammazione("PR01", null);
		//System.out.println(p.toString());
		
		//Get prenotazioni funziona
		//ArrayList<Prenotazione> lpr = cc.getAllPrenotazioni();
		//System.out.println(lpr.toString());
		//Prenotazione pr = cc.getPrenotazione("PREN1", null);
		//System.out.println(pr.toString());
		
		

	}

}
