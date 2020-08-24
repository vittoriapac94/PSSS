package unina.vpacchiano.rest.multisala.controllers;

public class UtenteSconosciutoException extends Exception {
	
	public UtenteSconosciutoException(String nomeUtente){
		super("L'utente "+nomeUtente+" non � presente nel database");
	}

}
