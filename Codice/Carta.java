package main;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Carta {

	String IDcarta;
    boolean state;
    private StatoCarta TipoCarta; 
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
        this.state = isVIP;
        if(isVIP){
            TipoCarta = new VIP();
        } else {
            TipoCarta = new Base();
        }
        return TipoCarta.getCosto();
    }

    public int getTipologia(){
        return this.TipoCarta.getCosto();
    }

    public void creaCliente(String CF, String nome, String cognome){
        String IDccl = this.IDcarta;
        Cliente cl = new Cliente(CF, nome, cognome);
        this.ClienteAssociato.put(IDccl, cl);
    }
    public String getCliente(){
        return this.ClienteAssociato.get(this.IDcarta).getCF();
    }
    public void inserisciCell(String cell){
        this.ClienteAssociato.get(this.IDcarta).setCell(cell);
    }

    public void addPunti(String gioco, int punti){  
        this.Punteggio.merge(gioco, punti, Integer::sum);   
    }
    public void removePunti(int punti){
        for(Entry<String, Integer> entry : this.Punteggio.entrySet()){
            if(entry.getValue() >= punti){ addPunti(entry.getKey(), -punti); punti = 0; break;}
            else{ punti -= entry.getValue(); addPunti(entry.getKey(), -entry.getValue());}
        }  
    }
	public Map<String, Integer> getPunteggioOrdinato() {
        List<Map.Entry<String, Integer>> PuntiTot = new ArrayList<>(this.Punteggio.entrySet());
        PuntiTot.sort(Map.Entry.comparingByValue());
        Map<String, Integer> PuntiOrd = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : PuntiTot) {
           	PuntiOrd.put(entry.getKey(), entry.getValue());
        }
		return PuntiOrd;
	}

	@Override
	public String toString() {
		return "Carta " + IDcarta + " di "+ getCliente() + ": Gettoni :" + numGettoni + "}\n";
	}

}