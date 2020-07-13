package unina.vpacchiano.rest.multisala.domain;



public class Utente {
	
	private String nomeUtente;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private boolean admin;
	
	public Utente(String nomeUtente, String nome, String cognome, String email, String password, boolean admin) {
		this.nomeUtente = nomeUtente;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.admin = admin;
	}

	public String getNomeUtente() {
		return nomeUtente;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}

	public boolean isAdmin() {
		return admin;
	}

	@Override
	public String toString() {
		return "Utente [nomeUtente=" + nomeUtente + ", password=" + password
				+ "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		Utente u = (Utente)obj;
		return this.getNomeUtente().equals(u.getNomeUtente());
	}
	
	
	
}
