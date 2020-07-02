package unina.vpacchiano.rest.multisala.test;

import java.io.IOException;

import org.restlet.data.Status;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import com.google.gson.Gson;

import unina.vpacchiano.rest.multisala.controllers.CinemaController;
import unina.vpacchiano.rest.multisala.domain.Film;
import unina.vpacchiano.rest.multisala.domain.Prenotazione;
import unina.vpacchiano.rest.multisala.domain.Programmazione;
import unina.vpacchiano.rest.multisala.domain.Sala;

public class TestDELETE {

	public static void main(String[] args) throws ResourceException, IOException {
		// TODO Auto-generated method stub
		
		ClientResource cr;
		Gson gson = new Gson();
		Status status;
		String URI;
		String json;
		
		CinemaController cc = new CinemaController();
		String chiaveProva = "56b211bf00faf47d1c3cb7f28104fc8c";

		
		//Funzionante delete FILM
		//Film f = cc.getFilm("F01", null);
		//cc.removeFilm(f, chiaveProva);
		
		//Funzionante remove SALA
		//Sala s = cc.getSala("Andromeda", null);
		//cc.removeSala(s, chiaveProva);
				
		//Funzionante remove PROGRAMMAZIONE
		//Programmazione p = cc.getProgrammazione("PR01", null);
		//cc.removeProgrammazione(p, chiaveProva);
				
		//Funzionante remove PRENOTAZIONE
		//Prenotazione pren = new Prenotazione ("PREN2", "Giovannina", "PR02", 21);
		//cc.removePrenotazione(pren, chiaveProva);

	}

}
