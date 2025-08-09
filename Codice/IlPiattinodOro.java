package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;


public class IlPiattinodOro {

    private static IlPiattinodOro sistema;
    public IlPiattinodOroCarta gestore;
    public IlPiattinodOroPartita arbitro;
    private Map<String, Colonnina> Colonna;
    private Gioco currGioco;
    public Map<String, Gioco> GiochiDisponibili;
    private Premio currPremio;
    public Map<String, Premio> mappaPremi;
    private Cibo currCibo;
    public Map<String, Cibo> mappaCibi;
    private Prenotazione currPrenotazione;
    private Map<String, Prenotazione> mappaPrenotazioni;
    public Map<String, Partita> partiteAttuali;
    public Map<Integer, Dipendente> mappaDipendenti;
    public Map<String, Turno> Turni;
    private Dipendente currDipendente;
    


    private IlPiattinodOro() {
        this.GiochiDisponibili = new HashMap<>();
        this.mappaPremi= new HashMap<>();
        this.Colonna = new HashMap<>();
        this.mappaCibi = new HashMap<>();
        this.mappaPrenotazioni = new HashMap<>();
        this.partiteAttuali = new HashMap<>();
        this.Turni = new HashMap<>();
        this.mappaDipendenti = new HashMap<>();
        loadInizio();
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

    public void loadInizio() {

        Colonnina cln = new Colonnina("01", 100);
        this.Colonna.put("01", cln);

        Gioco g1 = new Gioco("01", "Biliardo", "Tavolo", 4, 10);
        Gioco g2 = new Gioco("02", "Flipper", "Cabinati", 1, 5);
        Gioco g3 = new Gioco("03", "SpaceBattle", "Cabinati", 2, 10); g3.setStato(false);
        this.GiochiDisponibili.put("01", g1);
        this.GiochiDisponibili.put("02", g2);
        this.GiochiDisponibili.put("03", g3);
        Premio p1 = new Premio("001", "Trombone", 90, "è un trombone");
        p1.newCopia();
        this.mappaPremi.put("001", p1);

        Dipendente d1 = new Dipendente("ABC", "Mario", "Rossi", "AAA");
        Admin adm = new Admin("DFG", "Luigi", "Rossi", "AAA");
        adm.setAmmin();
        this.mappaDipendenti.put(01, d1);
        this.mappaDipendenti.put(00, adm);
        Turno t1 = new Turno(d1.getID(), 8, 13, "Martedì");
        this.Turni.put(d1.getID(), t1);
        //System.out.println(getElencoPremi());
    }

    //Gioco
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
            this.currGioco = null;
        }  
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
    public void getGiocoPunti(String IDgioco) {
        System.out.println(GiochiDisponibili.get(IDgioco).getPunteggioOrdinato());
    }
    public void statoGioco(String IDgioco){
        boolean onoff = new GiocoOn(sistema).recover(IDgioco); 
        if(onoff) new GiocoOn(sistema).taken();
        else new GiocoOff(sistema).taken();
    }
    public void puntiGiocoTot(String IDcarta){
        List<String> Giochi = new ArrayList<>();
        for(Entry<String, Gioco> entry : GiochiDisponibili.entrySet()){
            for(Entry<String, Integer> entry2 : entry.getValue().Punteggio.entrySet()){
                if(Objects.equals(IDcarta, entry2.getKey())){
                    int pos = getPiazzamento(entry.getValue().getPunteggioOrdinato(), IDcarta);
                    Giochi.add(entry.getValue().getNome() + " punteggio: " + entry2.getValue() + " " + pos +"°");
                }
            }
        } 
        System.out.println(Giochi);
    }
    public int getPiazzamento(Map<String, Integer> Punti, String IDcarta) {
    int pos = 1;
    for(String ID : Punti.keySet()) {
        if(Objects.equals(ID, IDcarta)) {
            return pos;
        }
        pos++;
    }
    return -1;
    }

    //Premio
    public void InserisciPremio(String ID, String Nome, int Valore, String Descrizione, int NCopie) {
        for(Entry<String, Premio> entry : mappaPremi.entrySet()){
            if(!entry.getValue().getID().equals(ID)) {
               this.currPremio = new Premio(ID, Nome, Valore, Descrizione);
               System.out.println(this.currPremio);
            } else {this.currPremio = entry.getValue();}
            do{
                this.currPremio.newCopia();
                NCopie--;
            } while(NCopie > 0);
        }
    }
    public void FineInserimentoPremio() {
        if (currPremio != null) {
            this.mappaPremi.put(currPremio.getID(), currPremio);
            System.out.println("Inserimento Concluso");
            this.currPremio = null;
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

    //Gestore Carta
    public void CreaNuovaCarta() { this.gestore.CreaNuovaCarta(); }
    public void InserisciDocumento(String CF, String nome, String cognome) { this.gestore.InserisciDocumento( CF, nome, cognome);}
    public int scegliTipologia(boolean VIP){ return this.gestore.scegliTipologia(VIP);}
    public void Pagamento() {  this.gestore.Pagamento(true); }
    public double costoCarta(){ return this.gestore.costoCarta(); }
    public String recuperoCarta(String CF) { return this.gestore.recuperoCarta(CF); }
    public void cambioCodice(boolean mod) { this.gestore.cambioCodice(mod); }
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
            this.currCibo = null;
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
        List<Tuple<String, String>> giochiDisp = new ArrayList<>();
        for (Entry<String, Gioco> entry : GiochiDisponibili.entrySet()){
            giochiDisp.add(new Tuple<>(entry.getValue().getNome(), entry.getValue().getCodice()));
        }
        for (Entry<String, Prenotazione> entry : mappaPrenotazioni.entrySet()){
            String data = entry.getValue().getData();
            int ora = entry.getValue().getOra();
            if(data.equals(DataPrenotazione) && ora == OraPrenotazione) {
                giochiDisp.remove(new Tuple<>(entry.getValue().getGiocoPrenotato().getNome(), entry.getValue().getGiocoPrenotato().getCodice()));
            }
        }
        System.out.println(giochiDisp);
        return giochiDisp;
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
            this.currPremio = null;
        }
    }
    public List<Prenotazione> getElencoPrenotazioni() {
        List<Prenotazione> listPrenotazioni = new ArrayList<>();
        listPrenotazioni.addAll(mappaPrenotazioni.values());
        System.out.println(mappaPrenotazioni);
        return listPrenotazioni;
    }

    //Gestore Partita
    public String richiestaPartita(String IDCarta, String IDGioco){ return this.arbitro.richiestaPartita(IDCarta, IDGioco);}
    public void avviaPartita(String IDCarta, String IDGioco, int giocatori){this.arbitro.avviaPartita(IDCarta, IDGioco, giocatori);}
    public void finePartita(int punteggio){ this.arbitro.finePartita(punteggio);}
    public void continua(boolean c){ this.arbitro.continua(c);}
    //public String recuperaPartita(String gioco){ return this.arbitro.recuperaPartita(gioco);}
    public Partita recuperaPartita(){ return this.arbitro.recuperaPartita();}
    public Partita monitoraPartita(String IDpartita){ return this.arbitro.monitoraPartita(IDpartita);}

    //Dipendenti
    public boolean[] VerificaCredenziali(String CF, String Codice){   
        for(Entry<Integer, Dipendente> entry : mappaDipendenti.entrySet()){
            if((entry.getValue().getCF().equals(CF)) && (entry.getValue().getPass().equals(Codice))) {
               System.out.println("Utente verificato " + entry.getValue().getID());
               if(entry.getValue().getID().equals("ADMIN")) return new boolean[] {true, true};
               return new boolean[] { true, false};
            } 
        } return new boolean[] {false, false};
    }
    public void Check_in(String IDdipendente, int Orario, String Giorno){  
        for(Entry<String, Turno> entry : Turni.entrySet()){
            if(entry.getKey().equals(IDdipendente) && entry.getValue().getGiorno().equals(Giorno)){
                if (Orario >= entry.getValue().getInizo() && Orario <= entry.getValue().getFine()){
                    entry.getValue().setDisponibile();
                    for(Entry<Integer, Dipendente> entry1 : mappaDipendenti.entrySet()){
                        if((entry1.getValue().getID().equals(IDdipendente))) {
                            System.out.println("Il dipendente " + entry1.getValue().getCredenziali() + " è a lavoro");
                            currDipendente = entry1.getValue();
                        } 
                }
            }
        }
        }
    }
    public void Check_Out(){   
        for(Entry<String, Turno> entry : Turni.entrySet()){
            if(entry.getKey().equals(currDipendente.getID())){
                    entry.getValue().setDisponibile();
                    currDipendente = null;
            }
        }
    } 
    public String MonitoraDipendenti(String Giorno){
        String elenco;
        if(currDipendente != null){
            elenco = ("Il dipendente " + currDipendente.getCredenziali() + "è a lavoro");
            System.out.println(elenco);
        } else {
            elenco = ("Nessun dipendente è a lavoro");
            System.out.println(elenco);
        }
        elenco += ("\nI dipendenti in turno oggi sono: ");
        System.out.println("I dipendenti in turno oggi sono: ");
        for(Entry<String, Turno> entry : Turni.entrySet()){
            if(entry.getValue().getGiorno().equals(Giorno) && entry.getValue().getDisponibile()){
                for(Entry<Integer, Dipendente> entry1 : mappaDipendenti.entrySet()){
                    if((entry1.getValue().getID().equals(entry.getValue().getDip()))) {
                        elenco += ("\nIl dipendente " + entry1.getValue().getCredenziali() + " dalle " + entry.getValue().getInizo() + " alle " + entry.getValue().getFine());
                        System.out.println("Il dipendente " + entry1.getValue().getCredenziali() + " dalle " + entry.getValue().getInizo() + " alle " + entry.getValue().getFine());
                    } 
                }
            }
        }
        return elenco;
    }
    public Dipendente getDipendente(){
        return currDipendente;
    }

    /*

    */
}