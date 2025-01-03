package main;

public class Colonnina {

    private String IDcol;
    private int numCarte;

    public Colonnina( String IDcol, int numCarte) {
        this.IDcol = IDcol;
        this.numCarte = numCarte;
	}

	public String getCodice() {
		return IDcol;
	}
	public void setCodice(String IDcol) {
		this.IDcol = IDcol;
	}

    public int getnumCarte() {
		return numCarte;
	}

	public int addCarte(int numCarte) {
		this.numCarte += numCarte;
        return numCarte;
	}

    public int remove() {
		this.numCarte -= 1;
        return numCarte;
	}
    
}
