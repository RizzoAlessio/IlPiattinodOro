package main;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Carta {

	String IDcarta;
	private int numGettoni;
    Map<String, Cliente> ClienteAssociato;

	public Carta() {
        this.IDcarta = "IPDO" + LocalDateTime.now().toString();
        this.ClienteAssociato = new HashMap<>();
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


    public int scegliTipologia(boolean VIP){
        this.addGettoni(100);
        if (VIP){
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

	@Override
	public String toString() {
		return "Carta {" + IDcarta + ": Gettoni :" + numGettoni + "}" + "\n";
	}

}
