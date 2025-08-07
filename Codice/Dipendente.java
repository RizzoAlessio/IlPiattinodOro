package main;

import java.util.Random;

public class Dipendente {

    private String CF;
	private String nome;
    private String cognome;
	private String pass;
	protected String IDdipendente;

	public Dipendente(String CF, String nome, String cognome, String pass) {
		Random r = new Random();
		this.CF = CF;
        this.nome = nome;
        this.cognome = cognome;
		this.pass = pass;
		this.IDdipendente = "IPDO-" + nome.charAt(0) + cognome.charAt(0) + (char)(r.nextInt(26) + 'a');
	}

	public String getCF() {
		return CF;
	}
	public String getPass() {
		return pass;
	}

    public String getCredenziali() {
		return (nome + " " + cognome);
	}

	public String getID(){
		return IDdipendente;
	}
    
}