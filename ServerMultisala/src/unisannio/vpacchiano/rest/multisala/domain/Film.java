package unisannio.vpacchiano.rest.multisala.domain;


public class Film {
	
	private String codFilm;
	private String nome;
	private String regista;
	private int annoUscita;
	private String genere;
	private int a;
	
	public Film(String codFilm, String nome, String regista, int annoUscita, String genere) {
		this.codFilm = codFilm;
		this.nome = nome;
		this.regista = regista;
		this.annoUscita = annoUscita;
		this.genere = genere;
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
	
	public String getGenere() {
		return genere;
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
