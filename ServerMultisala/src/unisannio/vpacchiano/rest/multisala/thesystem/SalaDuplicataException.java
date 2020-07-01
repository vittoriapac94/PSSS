package unisannio.vpacchiano.rest.multisala.thesystem;

public class SalaDuplicataException extends Exception {
	
	public SalaDuplicataException(String nome){
		super("La sala "+nome+" è già presente nel database");
	}
}
