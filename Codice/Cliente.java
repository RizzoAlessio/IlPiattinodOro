package main;

public class Cliente {

    private String CF;
	private String nome;
    private String cognome;

	public Cliente(String CF, String nome, String cognome ) {
		this.CF = CF;
        this.nome = nome;
        this.cognome = cognome;
	}

	public String getCF() {
		return CF;
	}
	public void setCF(String CF) {
		this.CF = CF;
	}

    public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

    public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
    
}
