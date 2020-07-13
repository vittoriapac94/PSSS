package unina.vpacchiano.rest.multisala.test;

import java.io.IOException;

import org.restlet.data.Status;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import unina.vpacchiano.rest.multisala.controllers.CinemaController;
import unina.vpacchiano.rest.multisala.domain.Film;
import unina.vpacchiano.rest.multisala.domain.Prenotazione;
import unina.vpacchiano.rest.multisala.domain.Programmazione;
import unina.vpacchiano.rest.multisala.domain.Sala;
import unina.vpacchiano.rest.multisala.domain.Utente;


public class TestPOST {

	public static void main(String[] args) throws ResourceException, IOException {
		// TODO Auto-generated method stub
		ClientResource cr;
		Gson gson = new Gson();
		Status status;
		String URI;
		String json;
		
		CinemaController cc = new CinemaController();
		String chiaveProva = "56b211bf00faf47d1c3cb7f28104fc8c";

		// Funzionante add utente REGISTRAZIONE
		//Utente u = new Utente("Giovannina", "Giovanna", "De Matteo", "giovannella@gio.it", "giovannina99", "1999-01-11", false);
		//cc.registraUtente(u);
		
		//Funzionante add FILM
		//Film f = new Film("F01", "Titanic", "Cameron", 1999, "Drammatico");
		//cc.addFilm(f, chiaveProva);
		
		//Funzionante add SALA
		Sala s = new Sala("Andromeda",233);
		cc.addSala(s, chiaveProva);
		
		//Funzionante add PROGRAMMAZIONE
		Programmazione p = new Programmazione ("PR02", "Andromeda", "AF021", "2015-12-01", "12:00", 3);
		cc.addProgrammazione(p, chiaveProva);
		
		//Funzionante add PRENOTAZIONE
		Prenotazione pren = new Prenotazione ("PREN2", "Giovannina", "PR02", 21);
		cc.addPrenotazione(pren, chiaveProva);
		
		
		
	}	

}
