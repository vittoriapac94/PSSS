package unisannio.vpacchiano.rest.multisala.thesystem;

public class ProgrammazioneDuplicataException extends Exception {

	public ProgrammazioneDuplicataException(String codProgrammazione){
		super("La programmazione "+codProgrammazione+" è già esistente nel database");
	}
}
