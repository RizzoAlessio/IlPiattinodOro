import java.util.HashMap;
import java.util.Map;

public class Premio {
    Map<String, copiaPremio> mappaCopiePremio;

    private String ID;
    private String Nome;
    private int Valore;
    private String Descrizione;

    public Premio(String ID, int Valore, String Nome, String Descrizione){
        this.mappaCopiePremio = new HashMap<>();
        this.ID = ID;
        this.Nome = Nome;
        this.Valore = Valore;
        this.Descrizione = Descrizione; 
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

    public List<copiaPremio> getElencoCopiePremio() {
        List<copiaPremio> listCopiePremio = new ArrayList<>();
        listCopiePremio.addAll(mappaCopiePremio.values());
        System.out.println(mappaCopiePremio);
        return listCopiePremio;
    }

    public void newCopia(){
        String ID = this.ID + "" +  mappaCopiePremio.size();
        copiaPremio copia = new copiaPremio(ID);
        this.mappaCopiePremio.put(ID, copia);
    }

    @Override
	public String toString() {
		return "Premio{" + ID + ": " + Nome + ", valore = " + Valore + "\n Descrizione: " + Descrizione + "}\n";
	}
}


