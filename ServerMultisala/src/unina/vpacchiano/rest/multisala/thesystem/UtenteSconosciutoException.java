package unina.vpacchiano.rest.multisala.thesystem;

public class UtenteSconosciutoException extends Exception {
	
	public UtenteSconosciutoException(String nomeUtente){
		super("L'utente "+nomeUtente+" non � presente nel database");
	}

}
