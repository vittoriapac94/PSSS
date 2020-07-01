package unisannio.vpacchiano.rest.multisala.thesystem;

public class SalaSconosciutaException extends Exception {
	
	public SalaSconosciutaException(String nome){
		super("La sala "+nome+" non è presente nel database");
	}

}
