package unina.vpacchiano.rest.multisala.controllers;

public class FilmDuplicatoException extends Exception {
	
	public FilmDuplicatoException(String codFilm){
		super("Il film "+codFilm+ " � gi� presente nel database");
	}

}
