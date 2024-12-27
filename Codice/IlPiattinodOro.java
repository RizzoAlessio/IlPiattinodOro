package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IlPiattinodOro {

    private static IlPiattinodOro sistema;
    private Gioco currGioco;
    private Map<String, Gioco> GiochiDisponibili;
    
    private IlPiattinodOro() {
        this.GiochiDisponibili= new HashMap<>();
        loadGiochi();
    }
    
    public static IlPiattinodOro getInstance() {
        if (sistema == null) {
            sistema = new IlPiattinodOro();
        } else {
            System.out.println("Istanza del sistema già creata");
        } 
        return sistema;
    }
    
    public void InserisciGioco(String IDgioco, String nome, String tipologia, int giocatori) {
        this.currGioco = new Gioco(IDgioco, nome, tipologia, giocatori, 0);
        System.out.println("Inserito il gioco");
    }
    
    public void DefinisciCosto(int prezzo) {
        if (currGioco != null) {
            if(prezzo >= 1 && prezzo <= 30) this.currGioco.setCosto(prezzo);
            System.out.println("Prezzo inserito");
        } else {
            System.out.println("Errore");
        }
    }
    
    public void FineInserimento() {
        if (currGioco != null) {
            this.GiochiDisponibili.put(currGioco.getCodice(), currGioco);
            System.out.println("Inserimento Concluso");
            }
    
        }

//temp    
    public void loadGiochi() {
            Gioco g1 = new Gioco("01", "Biliardo", "Tavolo", 4, 10);
            Gioco g2 = new Gioco("02", "Flipper", "Cabinati", 1, 5);
            Gioco g3 = new Gioco("03", "SpaceBattle", "Cabinati", 2, 10);
            this.GiochiDisponibili.put("01", g1);
            this.GiochiDisponibili.put("02", g2);
            this.GiochiDisponibili.put("03", g3);
            System.out.println("Caricamento Completato");
        }
    
        public List<Gioco> getElencoGiochi() {
            List<Gioco> listGiochi = new ArrayList<>();
            listGiochi.addAll(GiochiDisponibili.values());
            System.out.println(GiochiDisponibili);
            return listGiochi;
        }
    
        public Gioco getGiocoCorrente() {
            System.out.println(currGioco);
            return currGioco;
        }
    
        public void getGioco(String IDgioco) {
            System.out.println(GiochiDisponibili.get(IDgioco));
        }
    
    }


