package unina.vpacchiano.rest.multisala.domain;

public class Prenotazione {
	private String codPrenotazione;
	private String nomeUtente; 
	private String codProgrammazione;
	private int postiPrenotati;
	
	public Prenotazione(String codPrenotazione, String nomeUtente,
			String codProg, int postiPrenotati) {
		this.codPrenotazione = codPrenotazione;
		this.nomeUtente = nomeUtente;
		this.codProgrammazione = codProg;
		this.postiPrenotati=postiPrenotati;
	}

	public String getCodPrenotazione() {
		return codPrenotazione;
	}

	public String getNomeUtente() {
		return nomeUtente;
	}

	public String getCodProgrammazione() {
		return codProgrammazione;
	}
	
	public int getPostiPrenotati() {
		return postiPrenotati;
	}

	@Override
	public String toString() {
		
		return "Programmazione: "+codProgrammazione+" Numero Posti: "+postiPrenotati;
	}

	@Override
	public boolean equals(Object obj) {
		Prenotazione tmp = (Prenotazione) obj;
		return tmp.getCodPrenotazione().equals(this.getCodPrenotazione());
	}
	
	
	
}
