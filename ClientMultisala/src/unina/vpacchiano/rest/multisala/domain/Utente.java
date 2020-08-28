package unina.vpacchiano.rest.multisala.domain;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Utente {
	
	private String nomeUtente;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private Date nascita;
	private boolean admin;
	
	public Utente(String nomeUtente, String nome, String cognome, String email, String password, String nascita, boolean admin) {
		this.nomeUtente = nomeUtente;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		try {
			this.nascita = (new SimpleDateFormat("yy-MM-dd")).parse(nascita);
		} catch (ParseException e) {
			this.nascita = new Date();
		}
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

	public Date getNascita() {
		return nascita;
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
