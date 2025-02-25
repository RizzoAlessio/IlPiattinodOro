package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Cibo implements java.lang.Cloneable{

	private String IDcibo;
	private String nome;
	private String descrizione;
	private int costo;
	Map<String, Cibo> mappaDispensa;

	public Cibo(String IDcibo, String nome, String descrizione) {
		this.IDcibo = IDcibo;
        this.nome = nome;
        this.descrizione = descrizione;
        this.costo = 0;
		this.mappaDispensa = new HashMap<>();
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

	public Cibo copiaCibo() {
        return new Cibo(this.IDcibo, this.nome, this.descrizione);
    }

	public List<Cibo> getDispensa() {
        List<Cibo> list = new ArrayList<>();
        list.addAll(mappaDispensa.values());
        System.out.println(mappaDispensa);
        return list;
    }

    public void newQuantita(){
        String ID = this.IDcibo + "" +  mappaDispensa.size();
        this.mappaDispensa.put(ID, this);
    }

    public void compraCibo(String IDcibo){
        String ID = IDcibo + "" +  mappaDispensa.size();
        this.mappaDispensa.remove(ID);
    }

	@Override
	public String toString() {
		return "Cibo{" + IDcibo + ": " + nome + ", descrizione:" + descrizione + '\n' + "costo=" + costo + ", quantità=" + mappaDispensa.size() + '}' + '\n';
	}

}