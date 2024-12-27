import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Premio {
    
    private String ID;
    private String Nome;
    private int Valore;
    private String Descrizione;
    Map<String, CopiaPremio> mappaCopiePremio;


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

    public List<CopiaPremio> getElencoCopiePremio() {
        List<CopiaPremio> listCopiePremio = new ArrayList<>();
        listCopiePremio.addAll(mappaCopiePremio.values());
        System.out.println(mappaCopiePremio);
        return listCopiePremio;
    }

    public void newCopia(){
        String ID = this.ID + "" +  mappaCopiePremio.size();
        CopiaPremio copia = new CopiaPremio(ID);
        this.mappaCopiePremio.put(ID, copia);
    }

    @Override
	public String toString() {
		return "Premio{" + ID + ": " + Nome + ", valore = " + Valore + "\n Descrizione: " + Descrizione + "}\n";
	}
}

