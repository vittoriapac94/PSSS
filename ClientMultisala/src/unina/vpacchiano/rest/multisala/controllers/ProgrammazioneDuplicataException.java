package unina.vpacchiano.rest.multisala.controllers;

public class ProgrammazioneDuplicataException extends Exception {

	public ProgrammazioneDuplicataException(String codProgrammazione){
		super("La programmazione "+codProgrammazione+" � gi� esistente nel database");
	}
}
