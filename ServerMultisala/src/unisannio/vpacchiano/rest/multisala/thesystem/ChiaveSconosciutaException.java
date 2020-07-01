package unisannio.vpacchiano.rest.multisala.thesystem;

public class ChiaveSconosciutaException extends Exception {
	
	public ChiaveSconosciutaException (String key){
		super("La chiave "+key + " non è presente nel database");
	}
}
	
