package unina.vpacchiano.rest.multisala.thesystem;

public class PostiTerminatiException extends Exception {
	
	public PostiTerminatiException(){
		super("Non � disponibile questo numero di posti in sala");
	}

}
