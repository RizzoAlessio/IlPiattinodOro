package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.time.LocalDate;


public class IlPiattinodOro {

    private static IlPiattinodOro sistema;
    public IlPiattinodOroCarta gestore;
    public IlPiattinodOroPartita arbitro;
    private Map<String, Colonnina> Colonna;
    private Gioco currGioco;
    public Map<String, Gioco> GiochiDisponibili;
    private Premio currPremio;
    public Map<String, Premio> mappaPremi;
    private Map<String, Carta> CarteFedeltà;
    private Cibo currCibo;
    public Map<String, Cibo> mappaCibi;
    private Prenotazione currPrenotazione;
    private Map<String, Prenotazione> mappaPrenotazioni;
    private Partita currPartita;
    public Map<String, Partita> partiteAttuali;


    private IlPiattinodOro() {
        this.GiochiDisponibili= new HashMap<>();
        this.mappaPremi= new HashMap<>();
        this.CarteFedeltà = new HashMap<>();
        this.Colonna = new HashMap<>();
        this.mappaCibi = new HashMap<>();
        this.mappaPrenotazioni = new HashMap<>();
        this.partiteAttuali = new HashMap<>();
        loadGiochi();
        loadPremi();
        loadColonna();
        this.gestore = new IlPiattinodOroCarta(GiochiDisponibili, Colonna);
        this.arbitro = new IlPiattinodOroPartita(GiochiDisponibili, gestore);
    }
    
    public static IlPiattinodOro getInstance() {
        if (sistema == null) {
            sistema = new IlPiattinodOro();
        } else {
            System.out.println("Istanza del sistema già creata");
        } 
        return sistema;
    }

    public void loadColonna() {
        Colonnina cln = new Colonnina("01", 100);
        this.Colonna.put("01", cln);
        System.out.println("Colonna Pronta");
    }

    //Gestione Gioco
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
    
    public void FineInserimentoGioco() {
        if (currGioco != null) {
            this.GiochiDisponibili.put(currGioco.getCodice(), currGioco);
            System.out.println("Inserimento Concluso");
            }
        }
 
    public void loadGiochi() {
            Gioco g1 = new Gioco("01", "Biliardo", "Tavolo", 4, 10);
            Gioco g2 = new Gioco("02", "Flipper", "Cabinati", 1, 5);
            Gioco g3 = new Gioco("03", "SpaceBattle", "Cabinati", 2, 10); g3.setStato(false);
            this.GiochiDisponibili.put("01", g1);
            this.GiochiDisponibili.put("02", g2);
            this.GiochiDisponibili.put("03", g3);
            System.out.println("Caricamento Giochi Completato");
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

    public void statoGioco(String IDgioco){
        boolean onoff = new GiocoOn(sistema).recover(IDgioco); 
        if(onoff) new GiocoOn(sistema).taken();
        else new GiocoOff(sistema).taken();
    }

    //Gestione Premio
 
    public void InserisciPremio(String ID, String Nome, int Valore, String Descrizione, int NCopie) {
        this.currPremio = new Premio(ID, Nome, Valore, Descrizione);
        do{
            this.currPremio.newCopia();
            NCopie--;
        } while(NCopie > 0);
    }

    public void FineInserimentoPremio() {
        if (currPremio != null) {
            this.mappaPremi.put(currPremio.getID(), currPremio);
            System.out.println("Inserimento Concluso");
        }
    }

    public List<Premio> getElencoPremi() {
        List<Premio> listPremi = new ArrayList<>();
        listPremi.addAll(mappaPremi.values());
        System.out.println(mappaPremi);
        return listPremi;
    }

    public Premio getPremioCorrente() {
        System.out.println(currPremio);
        return currPremio;
    }

    public void getPremio(String ID) {
        System.out.println(mappaPremi.get(ID));
    }

    public void loadPremi() {
        InserisciPremio("001", "Trombone", 999, "Trombone", 1);
        FineInserimentoPremio();
        System.out.println("Caricamento Premi Completato");
}

    //Gestione Carta
    public void CreaNuovaCarta() { this.gestore.CreaNuovaCarta(); }
    public void InserisciDocumento(String CF, String nome, String cognome) { this.gestore.InserisciDocumento( CF, nome, cognome);}
    public int scegliTipologia(boolean VIP){ return this.gestore.scegliTipologia(VIP);}
    public void Pagamento() {  this.gestore.Pagamento(); }
    public double costoCarta(){ return this.gestore.costoCarta(); }
    public String recuperoCarta(String CF) { return this.gestore.recuperoCarta(CF); }
    public void selezionaModalita(boolean mod) { this.gestore.selezionaModalita(mod); }
    public void inserisciCarta(String IDcarta){ this.gestore.inserisciCarta(IDcarta); }
    public void ricaricaGettoni(int gettoni){ this.gestore.ricaricaGettoni(gettoni); }
    public void inserimentoCell(String tel){ this.gestore.inserimentoCell(tel); }
    public Carta getCartaCorrente() { return this.gestore.getCartaCorrente(); }
    public List<Carta> getElencoCarte() { return this.gestore.getElencoCarte(); }

    //Cibo
    
    public void InserisciCibo(String IDCibo, String nome, String descrizione, int CCopie) {
        this.currCibo = new Cibo(IDCibo, nome, descrizione);
        do{
            this.currCibo.newQuantita();
            CCopie--;
        } while(CCopie > 0);
    }

    public void DefinisciCostoCibo(int prezzo) {
        if (currCibo != null) {
            if(prezzo >= 1 && prezzo <= 30) this.currCibo.setCosto(prezzo);
            System.out.println("Prezzo inserito");
        } else {
            System.out.println("Errore");
        }
    }

    public void FineInserimentoCibo() {
        if (currCibo != null) {
            this.mappaCibi.put(currCibo.getCodice(), currCibo);
            System.out.println("Inserimento Concluso");
        }
    }

    public List<Cibo> getElencoCibi() {
        List<Cibo> listCibi = new ArrayList<>();
        listCibi.addAll(mappaCibi.values());
        System.out.println(mappaCibi);
        return listCibi;
    }
        
    public Cibo getCiboCorrente() {
        System.out.println(currCibo);
        return currCibo;
    }

    public void getCibo(String IDCibo) {
        System.out.println(mappaCibi.get(IDCibo));
    }

    //Prenotazione
    public List<Tuple<String, String>> disponibilita(String DataPrenotazione, int OraPrenotazione){
        System.out.println("Giochi disponibili:");
        List<Tuple<String, String>> giochi = new ArrayList<>();
        int tempo = 1;
        for (Entry<String, Prenotazione> entry : mappaPrenotazioni.entrySet()){
            String data = entry.getValue().getData();
            int ora = entry.getValue().getOra();
            if(data != DataPrenotazione && ora < (OraPrenotazione-tempo) && ora > (OraPrenotazione+tempo)) {
                giochi.add(new Tuple<>(entry.getValue().getGiocoPrenotato().getNome(), entry.getValue().getGiocoPrenotato().getCodice()));
            }
        }
        return giochi;
    }

    public void creaPrenotazione(String IDCarta, String IDGioco, int numGiocatori, String Data, int Ora){
        Carta carta = this.gestore.getCarta(IDCarta);
        Gioco gioco = GiochiDisponibili.get(IDGioco);
        this.currPrenotazione = new Prenotazione(Data, Ora, numGiocatori, carta, gioco);
    }

    public void finePrenotazione(){
        if (currPrenotazione != null) {
            this.mappaPrenotazioni.put(currPrenotazione.getCodice(), currPrenotazione);
            System.out.println("Inserimento Concluso");
        }
    }

    public List<Prenotazione> getElencoPrenotazioni() {
        List<Prenotazione> listPrenotazioni = new ArrayList<>();
        listPrenotazioni.addAll(mappaPrenotazioni.values());
        System.out.println(mappaPrenotazioni);
        return listPrenotazioni;
    }

    //Partita
    public void richiestaPartita(String IDCarta, String IDGioco){ this.arbitro.richiestaPartita(IDCarta, IDGioco);}
    public void avviaPartita(String IDCarta, String IDGioco, int giocatori){this.arbitro.avviaPartita(IDCarta, IDGioco, giocatori);}
    public void finePartita(int punteggio){ this.arbitro.finePartita(punteggio);}
    public void continua(boolean c){ this.arbitro.continua(c);}
    public void recuperaPartita(String gioco){ this.arbitro.recuperaPartita(gioco);}
    public Partita monitoraPartita(String IDpartita){ return this.arbitro.monitoraPartita(IDpartita);}

    //premio
    public void inserisciTessera(String IDcarta){
        Carta tessera = this.gestore.getCarta(IDcarta);
        int totalP = 0;
        for(Entry<String, Integer> entry : tessera.Punteggio.entrySet()){
            totalP += entry.getValue();
        } 
        System.out.println("Sono disponibili " + totalP + " punti");
    }

    public void cercaPremio(int costo){
        for(Entry<String, Premio> entry : mappaPremi.entrySet()){
            if(entry.getValue().getValore() <= costo) {
                System.out.println("Premio "+ entry.getValue().getID() + entry.getValue().getNome() + "; punti: " + entry.getValue().getValore());
            }
        } 
    }
    public void scegliPremio(String IDp, String IDc){
        for(Entry<String, Premio> entry : mappaPremi.entrySet()){
            if(entry.getValue().getID().equals(IDp)) {
                this.gestore.getCarta(IDc).removePunti(entry.getValue().getValore());
                entry.getValue().removeCopia(IDp);
            }
        }
        this.inserisciTessera(IDc);
    }

}