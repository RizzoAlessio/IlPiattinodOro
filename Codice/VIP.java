package main;

public class VIP extends Carta {
    
    private int costoVIP = 25;

    public VIP(String IDcarta) {
        this.IDcarta = IDcarta;
    }

    public int getCosto() {
		return costoVIP;
	}
	public void setCosto(int costo) {
		this.costoVIP = costo;
	}
    
}
