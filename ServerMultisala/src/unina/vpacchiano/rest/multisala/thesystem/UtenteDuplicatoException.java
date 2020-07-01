package unina.vpacchiano.rest.multisala.thesystem;

public class UtenteDuplicatoException extends Exception {
	
	public UtenteDuplicatoException(String nomeUtente){
		super("L'utente "+nomeUtente+" � gi� presente nel database");
	}

}
