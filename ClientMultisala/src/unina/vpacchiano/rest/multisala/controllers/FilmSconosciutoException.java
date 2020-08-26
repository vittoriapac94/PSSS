package unina.vpacchiano.rest.multisala.controllers;

public class FilmSconosciutoException extends Exception {
	
	public FilmSconosciutoException(String codFilm){
		super("Il film "+codFilm + " non � presente nel database");
	}

}
