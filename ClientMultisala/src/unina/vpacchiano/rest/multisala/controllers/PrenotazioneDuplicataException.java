package unina.vpacchiano.rest.multisala.controllers;

public class PrenotazioneDuplicataException extends Exception {
	
	public PrenotazioneDuplicataException(String codPrenotazione){
		super("La prenotazione "+codPrenotazione+" � gi� presente nel database");
	}

}
