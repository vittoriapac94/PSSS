package unina.vpacchiano.rest.multisala.thesystem;

public class SalaSconosciutaException extends Exception {
	
	public SalaSconosciutaException(String nome){
		super("La sala "+nome+" non � presente nel database");
	}

}
