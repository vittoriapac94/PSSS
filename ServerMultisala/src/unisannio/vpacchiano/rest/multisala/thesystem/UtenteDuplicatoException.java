package unisannio.vpacchiano.rest.multisala.thesystem;

public class UtenteDuplicatoException extends Exception {
	
	public UtenteDuplicatoException(String nomeUtente){
		super("L'utente "+nomeUtente+" è già presente nel database");
	}

}
