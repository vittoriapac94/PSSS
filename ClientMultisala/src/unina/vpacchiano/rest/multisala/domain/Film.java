package unina.vpacchiano.rest.multisala.domain;


public class Film {
	
	private String codFilm;
	private String nome;
	private String regista;
	private int annoUscita;
	
	public Film(String codFilm, String nome, String regista, int annoUscita) {
		this.codFilm = codFilm;
		this.nome = nome;
		this.regista = regista;
		this.annoUscita = annoUscita;
	}
	
	public String getCodFilm() {
		return codFilm;
	}


	public String getNome() {
		return nome;
	}

	public String getRegista() {
		return regista;
	}

	public int getAnnoUscita() {
		return annoUscita;
	}
	
	@Override
	public boolean equals(Object obj) {
		Film tmp = (Film)obj;
		return this.getCodFilm().equalsIgnoreCase(tmp.getCodFilm());
	}
	
	@Override
	public String toString() {
		return "Film [nome=" + nome + ", regista=" + regista + ", annoUscita="
				+ annoUscita + "]";
	}

}
