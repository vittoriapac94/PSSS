package unisannio.vpacchiano.rest.multisala.domain;

public class Chiave {

	
	public static String generaChiave(Utente u){
		String username = u.getNomeUtente();
		String pass = u.getPassword();
		String key = Crypt.encrypt(username.concat(pass));
		
		return key;
	}
	
	

}
