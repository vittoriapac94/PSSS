package unisannio.vpacchiano.rest.multisala.thesystem;

public class FilmDuplicatoException extends Exception {
	
	public FilmDuplicatoException(String codFilm){
		super("Il film "+codFilm+ " è già presente nel database");
	}

}
