package main;

public class Partita {

	private String IDpartita;
    private Carta carta;
    private Gioco gioco;
	private int punteggio;
    private int giocatori;
	private String datainizio;

	public Partita(Carta carta, Gioco gioco, int giocatori, String data) {
		this.IDpartita = datainizio + carta.IDcarta.charAt(0)+gioco.getCodice().charAt(0);
        this.carta = carta;
        this.gioco = gioco;
        this.punteggio = 0;
        this.giocatori = 0;
        this.datainizio = data;
	}

	public String getCodice() {
		return IDpartita;
	}
	public void setCodice(String IDpartita) {
		this.IDpartita = IDpartita;
	}

	public Carta getCarta() {
		return carta;
	}
	public void setCarta(Carta carta) {
		this.carta = carta;
	}

    public Gioco getGioco() {
		return gioco;
	}
	public void setCioco(Gioco gioco) {
		this.gioco = gioco;
	}

    public String getData() {
		return datainizio;
	}
	public void setData(String data) {
		this.datainizio = data;
	}

    public int getPunteggio() {
		return punteggio;
	}
	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}

    public int getGiocatori() {
		return giocatori;
	}
	public void setGiocatori(int giocatori) {
		this.giocatori = giocatori;
	}

	@Override
	public String toString() {
		return "Partita{" + IDpartita + "di " + carta.getCliente() + " in " + gioco.getNome() + ", geiocatori " + giocatori + " punteggio=" + punteggio + " in " +datainizio + '}' + '\n';
	}

}
