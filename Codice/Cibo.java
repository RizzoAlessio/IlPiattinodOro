package main;

public class Cibo {

	private String IDcibo;
	private String nome;
	private String descrizione;
	private int costo;
	private int quantità;

	public Cibo(String IDcibo, String nome, String descrizione) {
		this.IDcibo = IDcibo;
        this.nome = nome;
        this.descrizione = descrizione;
        this.costo = 0;
        this.quantità = 0;
	}

	public String getCodice() {
		return IDcibo;
	}
	public void setCodice(String IDcibo) {
		this.IDcibo = IDcibo;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

    public String getDescrizione() {
		return descrizione;
	}
	public void setDescizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getCosto() {
		return costo;
	}
	public void setCosto(int costo) {
		this.costo = costo;
	}

	public int getquantità() {
		return quantità;
	}
	public void addQuantità() {
		quantità++;
	}

	@Override
	public String toString() {
		return "Cibo{" + IDcibo + ": " + nome + ", descrizione:" + descrizione + '\n' + "costo=" + costo + ", quantità=" + quantità + '}' + '\n';
	}

}