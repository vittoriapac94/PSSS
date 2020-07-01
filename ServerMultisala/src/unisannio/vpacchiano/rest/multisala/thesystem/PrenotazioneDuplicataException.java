package unisannio.vpacchiano.rest.multisala.thesystem;

public class PrenotazioneDuplicataException extends Exception {
	
	public PrenotazioneDuplicataException(String codPrenotazione){
		super("La prenotazione "+codPrenotazione+" è già presente nel database");
	}

}
