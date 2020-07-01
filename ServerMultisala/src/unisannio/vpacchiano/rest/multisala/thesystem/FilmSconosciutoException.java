package unisannio.vpacchiano.rest.multisala.thesystem;

public class FilmSconosciutoException extends Exception {
	
	public FilmSconosciutoException(String codFilm){
		super("Il film "+codFilm + " non è presente nel database");
	}

}
