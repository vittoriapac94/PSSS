package unisannio.vpacchiano.rest.multisala.server;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;


public class WebApp extends Application {
	
	public Restlet createInboundRoot(){
		Router router = new Router(getContext());
		
		router.attach("/sale/all", MultisalaSaleAllResourceJSon.class);
		router.attach("/sale/{nome}&{chiave}", MultisalaSalaResourceJSon.class);
		
		router.attach("/film/all", MultisalaFilmAllResourceJSon.class);
		router.attach("/film/{nome}&{chiave}", MultisalaFilmResourceJSon.class);
		
		router.attach("/programmazioni/all", MultisalaProgrammazioniAllResourceJSon.class);
		router.attach("/programmazioni/{nome}&{chiave}", MultisalaProgrammazioniResourceJSon.class);
		
		router.attach("/prenotazioni/all", MultisalaPrenotazioniAllResourceJSon.class);
		router.attach("/prenotazioni/{nome}&{chiave}", MultisalaPrenotazioniResourceJSon.class);

		router.attach("/users/login/{username}&{password}", MultisalaLoginResourceJSon.class);
        router.attachDefault(MultisalaFilmAllResourceJSon.class);

		
		return router;
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Component component = new Component();
			component.getServers().add(Protocol.HTTP, 8182);
			component.getDefaultHost().attach(new WebApp());
			component.start();
			new ShowServer();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
