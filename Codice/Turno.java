package main;

public class Turno {

    private String IDdipendente;
	private int Orario_i, Orario_f;
    private String Giorno;
	private boolean Disp = false;

	public Turno(String IDdipendente, int Orario_i, int Orario_f, String Giorno) {

		this.IDdipendente = IDdipendente;
        this.Orario_i = Orario_i;
		this.Orario_f = Orario_f;
		this.Giorno = Giorno;
	}

	public void setDisponibile() {
		Disp = !Disp;
	}

	public String getGiorno(){
		return Giorno;
	}
	public String getDip(){
		return IDdipendente;
	}
	public int getInizo(){
		return Orario_i;
	}
	public int getFine(){
		return Orario_f;
	}
	public boolean getDisponibile(){
		return Disp;
	}
    
}