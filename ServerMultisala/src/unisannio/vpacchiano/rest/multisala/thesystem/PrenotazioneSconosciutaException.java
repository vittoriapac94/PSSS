package unisannio.vpacchiano.rest.multisala.thesystem;

public class PrenotazioneSconosciutaException extends Exception {

	public PrenotazioneSconosciutaException(String codPrenotazione){
		super("La prenotazione "+codPrenotazione+" non è presente nel database");
	}
}
