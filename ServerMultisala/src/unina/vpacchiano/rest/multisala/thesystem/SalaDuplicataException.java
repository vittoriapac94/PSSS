package unina.vpacchiano.rest.multisala.thesystem;

public class SalaDuplicataException extends Exception {
	
	public SalaDuplicataException(String nome){
		super("La sala "+nome+" � gi� presente nel database");
	}
}
