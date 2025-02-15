package main;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Carta {

	String IDcarta;
    boolean VIP;
	private int numGettoni;
    Map<String, Cliente> ClienteAssociato;
    Map<String, Integer> Punteggio;

	public Carta() {
        this.IDcarta = "IPDO" + LocalDateTime.now().toString();
        this.ClienteAssociato = new HashMap<>();
        this.Punteggio = new HashMap<>();
	}

	public String getCodice() {
		return IDcarta;
	}
	public void setCodice(String IDcarta) {
		this.IDcarta = IDcarta;
	}
    public void changeCodice(){
        this.IDcarta = "IPDO" + LocalDateTime.now().toString();
    }

    public int getGettoni() {
		return numGettoni;
	}
	public int addGettoni(int numGettoni) {
		this.numGettoni += numGettoni;
        return numGettoni;
	}

    public int scegliTipologia(boolean isVIP){
        this.addGettoni(100);
        this.VIP = isVIP;
        if(isVIP){
            VIP cartavip = new VIP(this.IDcarta);
            return cartavip.getCosto();
        } else {
            Base cartabase = new Base(this.IDcarta);
            return cartabase.getCosto();
        }
    }

    public int getTipologia(){
        if(this.VIP){
            VIP cartavip = new VIP(this.IDcarta);
            return cartavip.getCosto();
        } else {
            Base cartabase = new Base(this.IDcarta);
            return cartabase.getCosto();
        }
    }

    public void creaCliente(String CF, String nome, String cognome){
        String IDccl = this.IDcarta;
        Cliente cl = new Cliente(CF, nome, cognome);
        this.ClienteAssociato.put(IDccl, cl);
    }
    public String getCliente(){
        return this.ClienteAssociato.get(this.IDcarta).getNome();
    }
    public void inserisciCell(String cell){
        this.ClienteAssociato.get(this.IDcarta).setCell(cell);
    }

    public void addPunti(String gioco, int punti){
        this.Punteggio.put(gioco, punti);
    }
    public int getPunti(String gioco){
        return this.Punteggio.get(gioco);
    }

	@Override
	public String toString() {
		return "Carta {" + IDcarta + ": Gettoni :" + numGettoni + "}" + "\n";
	}

}