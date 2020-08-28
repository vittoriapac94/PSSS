package unina.vpacchiano.rest.multisala.controllers;

public class SalaDuplicataException extends Exception {
	
	public SalaDuplicataException(String nome){
		super("La sala "+nome+" � gi� presente nel database");
	}
}
