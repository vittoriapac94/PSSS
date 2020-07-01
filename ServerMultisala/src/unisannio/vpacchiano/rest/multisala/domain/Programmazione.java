package unisannio.vpacchiano.rest.multisala.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Programmazione {
	
	private String codProgrammazione;
	private String nomeSala;
	private String codFilm;
	private Date data;
	private String orario;
	private int postiPrenotati;
	
	public Programmazione(String codProgrammazione, String nomeSala, String codFilm, String data, String orario, int postiPrenotati){
		this.codProgrammazione = codProgrammazione;
		this.nomeSala = nomeSala;
		this.codFilm = codFilm;
		try {
			this.data = (new SimpleDateFormat("yyyy-MM-dd")).parse(data);
		} catch (ParseException e) {
			this.data = new Date();
		}
		this.orario = orario;
		this.postiPrenotati = postiPrenotati;
	}

	public String getCodProgrammazione() {
		return codProgrammazione;
	}

	public String getNomeSala() {
		return nomeSala;
	}

	public String getCodFilm() {
		return codFilm;
	}

	public Date getData() {
		return data;
	}

	public String getOrario() {
		return orario;
	}

	public int getPostiPrenotati() {
		return postiPrenotati;
	}

	@Override
	public String toString() {
		return this.codProgrammazione+" Film: "+this.codFilm+" Sala: "+this.nomeSala+" "+this.data+" "+this.orario+" prenotati: "+this.postiPrenotati;
	}

	@Override
	public boolean equals(Object arg0) {
		Programmazione tmp = (Programmazione)arg0;
		return this.getCodProgrammazione().equalsIgnoreCase(tmp.getCodProgrammazione());
	}
	
	

}
