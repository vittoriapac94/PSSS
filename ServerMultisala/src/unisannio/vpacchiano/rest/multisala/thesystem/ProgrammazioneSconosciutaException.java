package unisannio.vpacchiano.rest.multisala.thesystem;

public class ProgrammazioneSconosciutaException extends Exception {

	public ProgrammazioneSconosciutaException(String codProgrammazione){
		super("La programmazione "+codProgrammazione+" non è presente nel database");
	}
}

