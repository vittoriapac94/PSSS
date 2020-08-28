package unina.vpacchiano.rest.multisala.controllers;

public class PostiTerminatiException extends Exception {
	
	public PostiTerminatiException(){
		super("Non � disponibile questo numero di posti in sala");
	}

}
