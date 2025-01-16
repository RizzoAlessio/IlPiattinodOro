package main;

public class Prenotazione {

	private String IDPrenotazione;
	private String Data;
	private int Ora;
	private int numGiocatori;
    private Carta CartaPrenotazione;
    private Gioco GiocoPrenotato;

	public Prenotazione(String IDPrenotazione, String Data, int Ora, Carta CartaPrenotazione, Gioco GiocoPrenotato) {
		this.IDPrenotazione = IDPrenotazione;
        this.Data = Data;
        this.Ora = Ora;
        this.numGiocatori = 0;
        this.CartaPrenotazione = CartaPrenotazione;
        this.GiocoPrenotato = GiocoPrenotato;
	}

	public String getCodice() {
		return IDPrenotazione;
	}
	public void setCodice(String IDPrenotazione) {
		this.IDPrenotazione = IDPrenotazione;
	}

	public String getData() {
		return Data;
	}
	public void setData(String Data) {
		this.Data = Data;
	}

    public int getOra() {
		return Ora;
	}
	public void setOra(int Ora) {
		this.Ora = Ora;
	}

	public int getNumGiocatori() {
		return numGiocatori;
	}
	public void setNumGiocatori(int numGiocatori) {
		this.numGiocatori = numGiocatori;
	}

    public Carta getCarta() {
		return CartaPrenotazione;
	}
	public void setCarta(Carta CartaPrenotazione) {
		this.CartaPrenotazione = CartaPrenotazione;
	}

    public Gioco getGiocoPrenotato() {
		return GiocoPrenotato;
	}
	public void setGiocoPrenotato(Gioco GiocoPrenotato) {
		this.GiocoPrenotato = GiocoPrenotato;
	}

	@Override
	public String toString() {
		return "Prenotazione di " + CartaPrenotazione.IDcarta +", " + IDPrenotazione + ": " + Data + ", " + Ora + ".\nGioco:" + GiocoPrenotato.getCodice() + ", numero giocatori=" + numGiocatori + '\n';
	}

}
