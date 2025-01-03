package main;

public class Base extends Carta {
    
    private int costoBase = 15;

    public Base(String IDcarta) {
        this.IDcarta = IDcarta;
    }

    public int getCosto() {
		return costoBase;
	}
	public void setCosto(int costo) {
		this.costoBase = costo;
	}
    
}
