package main;

public class Admin extends Dipendente {

	public Admin(String CF, String nome, String cognome, String pass) {
		super(CF, nome, cognome, pass);
	}

	public void setAmmin(){
		IDdipendente = "ADMIN";
	}
    
}