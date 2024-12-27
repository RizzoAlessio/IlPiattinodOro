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

    public getID(){
        return ID;
    }

    public setID(String ID){
        this.ID = ID;
    }

    public getNome(){
        return Nome;
    }

    public setNome(String Nome){
        this.Nome = Nome;
    }

    public getValore(){
        return Valore;
    }

    public setValore(int Valore){
        this.Valore = Valore;
    }

    public getDescrizione(){
        return Descrizione;
    }

    public setDescrizione(String Descrizione){
        this.Descrizione = Descrizione;
    }

    public List<copiaPremio> getElencoCopiePremio() {
        List<copiaPremio> listCopiePremio = new ArrayList<>();
        listCopiePremio.addAll(mappaCopiePremio.values());
        System.out.println(mappaCopiePremio);
        return listCopiePremio;
    }

    public void newCopia(){
        //genero l'ID della copia(vediamo come generarlo che non ho idea)/inseriamo noi l'ID
        //richiamo il costruttore della copia, copia = costruttorecopia(ID);
        //this.mappaCopiePremio.put(ID, copia);
    }

    @Override
	public String toString() {
		return "Premio{" + ID + ": " + Nome + ", valore = " + Valore + "\n Descrizione: " + Descrizione + "}\n";
	}
}


