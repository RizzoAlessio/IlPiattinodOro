package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Gioco {

	private String IDgioco;
	private String nome;
	private String tipologia;
	private int giocatori;
	private int costo;
	private boolean attivo;
	public Map<String, Integer> Punteggio;

	public Gioco(String IDgioco, String nome, String tipologia, int giocatori, int costo) {
		this.IDgioco = IDgioco;
        this.nome = nome;
        this.tipologia = tipologia;
        this.giocatori = giocatori;
        this.costo = costo;
		this.attivo = true;
		this.Punteggio = new HashMap<>();
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

	public boolean getStato() {
		return attivo;
	}
	public void setStato(boolean state) {
		this.attivo = state;
	}

	public void setPunteggio(String IDcarta, int punti) {
		this.Punteggio.merge(IDcarta, punti, Integer::sum);
		if(this.Punteggio.get(IDcarta) != null && this.Punteggio.get(IDcarta) <= punti){
			this.Punteggio.put(IDcarta, punti); 
		}
	}
	public Map<String, Integer> getPunteggioOrdinato() {
        List<Map.Entry<String, Integer>> PuntiTot = new ArrayList<>(this.Punteggio.entrySet());
        PuntiTot.sort(Map.Entry.<String, Integer>comparingByValue().reversed());
        Map<String, Integer> PuntiOrd = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : PuntiTot) {
           	PuntiOrd.put(entry.getKey(), entry.getValue());
        }
		return PuntiOrd;
	}

	@Override
	public String toString() {
		return "Gioco{" + IDgioco + ": " + nome + ", tipo=" + tipologia + ", num. giocatori=" + giocatori + ", costo=" + costo + '}' + '\n';
	}

}
