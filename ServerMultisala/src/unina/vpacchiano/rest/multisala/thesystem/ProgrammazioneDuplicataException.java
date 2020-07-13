package unina.vpacchiano.rest.multisala.thesystem;

public class ProgrammazioneDuplicataException extends Exception {

	public ProgrammazioneDuplicataException(String codProgrammazione){
		super("La programmazione "+codProgrammazione+" � gi� esistente nel database");
	}
}
