package unisannio.vpacchiano.rest.multisala.thesystem;

public class PostiTerminatiException extends Exception {
	
	public PostiTerminatiException(){
		super("Non è disponibile questo numero di posti in sala");
	}

}
