package main;

public class Gioco {

	private String IDgioco;
	private String nome;
	private String tipologia;
	private int giocatori;
	private int costo;

	public Gioco(String IDgioco, String nome, String tipologia, int giocatori, int costo) {
		this.IDgioco = IDgioco;
        this.nome = nome;
        this.tipologia = tipologia;
        this.giocatori = giocatori;
        this.costo = costo;
	}

	public String getCodice() {
		return IDgioco;
	}
	public void setCodice(String IDgioco) {
		this.IDgioco = IDgioco;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

    public String getTipologia() {
		return tipologia;
	}
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public int getGiocatori() {
		return giocatori;
	}
	public void setGiocatori(int giocatori) {
		this.giocatori = giocatori;
	}

	public int getCosto() {
		return costo;
	}
	public void setCosto(int prezzo) {
		this.costo = prezzo;
	}

	@Override
	public String toString() {
		return "Gioco{" + IDgioco + ": " + nome + ", tipo=" + tipologia + ", num. giocatori=" + giocatori + ", costo=" + costo + '}' + '\n';
	}

}
