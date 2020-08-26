package unina.vpacchiano.rest.multisala.controllers;

public class SalaSconosciutaException extends Exception {
	
	public SalaSconosciutaException(String nome){
		super("La sala "+nome+" non � presente nel database");
	}

}
