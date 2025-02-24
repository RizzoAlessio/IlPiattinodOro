package main;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Premio implements java.lang.Cloneable{
    
    private String ID;
    private String Nome;
    private int Valore;
    private String Descrizione;
    Map<String, Premio> mappaCopiePremio;


    public Premio(String ID, String Nome, int Valore, String Descrizione){
        this.ID = ID;
        this.Nome = Nome;
        this.Valore = Valore;
        this.Descrizione = Descrizione; 
        this.mappaCopiePremio = new HashMap<>();
    } 

    public String getID(){
        return ID;
    }

    public void setID(String ID){
        this.ID = ID;
    }

    public String getNome(){
        return Nome;
    }

    public void setNome(String Nome){
        this.Nome = Nome;
    }

    public int getValore(){
        return Valore;
    }

    public void setValore(int Valore){
        this.Valore = Valore;
    }

    public String getDescrizione(){
        return Descrizione;
    }

    public void setDescrizione(String Descrizione){
        this.Descrizione = Descrizione;
    }

    public List<Premio> getElencoCopiePremio() {
        List<Premio> listCopiePremio = new ArrayList<>();
        listCopiePremio.addAll(mappaCopiePremio.values());
        System.out.println(mappaCopiePremio);
        return listCopiePremio;
    }

    public void newCopia(){
        String ID = this.ID + "" +  mappaCopiePremio.size();
        this.mappaCopiePremio.put(ID, this);
    }

    public void removeCopia(String IDcopia){
        String ID = IDcopia + "" +  mappaCopiePremio.size();
        this.mappaCopiePremio.remove(ID);
    }

    @Override
	public String toString() {
		return "" + ID + ": " + Nome + ", valore = " + Valore + ", quantità: " + mappaCopiePremio.size()
        + "\n Descrizione: " + Descrizione + "}\n";
	}
}

