package unisannio.vpacchiano.rest.multisala.domain;

public class Sala {
	
	private String nome;
	private int capienza;
	
	public Sala(String nome, int capienza){
		this.nome = nome;
		this.capienza = capienza;
		
	}

	@Override
	public String toString() {
		return "Sala [nome=" + nome + ", capienza=" + capienza + "]";
	}

	public int getCapienza() {
		return capienza;
	}

	public String getNome() {
		return nome;
	}
	
	@Override
	public boolean equals(Object obj) {
		Sala s = (Sala)obj;
		return this.getNome().equalsIgnoreCase(s.getNome());
	}

	
}
