package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class IlPiattinodOro {

    private static IlPiattinodOro sistema;
    private Map<String, Colonnina> Colonna;
    private Gioco currGioco;
    private Map<String, Gioco> GiochiDisponibili;
    private Premio currPremio;
    private Map<String, Premio> mappaPremi;
    private Carta currCarta;
    private Map<String, Carta> CarteFedeltà;
    private Cibo currCibo;
    private Map<String, Cibo> mappaCibi;


    private IlPiattinodOro() {
        this.GiochiDisponibili= new HashMap<>();
        this.mappaPremi= new HashMap<>();
        this.CarteFedeltà = new HashMap<>();
        this.Colonna = new HashMap<>();
        this.mappaCibi = new HashMap<>();
        loadGiochi();
        loadColonna();
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
            }
        }
 
    public void loadGiochi() {
            Gioco g1 = new Gioco("01", "Biliardo", "Tavolo", 4, 10);
            Gioco g2 = new Gioco("02", "Flipper", "Cabinati", 1, 5);
            Gioco g3 = new Gioco("03", "SpaceBattle", "Cabinati", 2, 10);
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

    //Premio
    //*(Da rivedere)*\\
    public void InserisciPremio(String ID, String Nome, int Valore, String Descrizione) {
        this.currPremio = new Premio(ID, Nome, Valore, Descrizione);
        Scanner yn = new Scanner(System.in); String d;
        boolean r = false;
        for (var entry : mappaPremi.entrySet()) {
            String premio = entry.getValue().getID();
            if(premio == ID) r = true;
        } if (r == false) {
        do{
            this.currPremio.newCopia();
            System.out.println("Inserito il premio");
            System.out.println("Aggiungere copia (Y / N): ");
            d = yn.next();
        } while (d == "Y");
            yn.close();
        } else {    System.err.println("Gioco già registrato");
            System.out.println("Aggiungere copia a quello scritto? (Y / N): ");
            d = yn.next();
            if(d == "Y"){
                do{
                    this.currPremio.newCopia();
                    System.out.println("Inserito il premio");
                    System.out.println("Aggiungere copia (Y / N): ");
                    d = yn.next();
                } while (d == "Y");
                yn.close();
            }   
        }
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

    //Carta
    public void CreaNuovaCarta() {
        int deposito = this.Colonna.get("01").getnumCarte();
        System.out.println(deposito);
        if(deposito != 0) this.currCarta = new Carta();
        else System.err.println("CARTE ESAURITE, IMPOSSIBILE PROSEGUIRE");
    }
    
    public void InserisciDocumento(String CF, String nome, String cognome) {
        boolean registrato = false;
        for (var entry : CarteFedeltà.entrySet()) {
            String prec = entry.getValue().ClienteAssociato.get(this.currCarta.IDcarta).getCF();
            if(prec == CF) registrato = true;
        } if(registrato == false) {
            this.currCarta.creaCliente(CF, nome, cognome);
            System.out.println("Cliente registrato"); 
        } else { System.err.println("Cliente già registrato"); this.currCarta = null; }
    }

    public int scegliTipologia(boolean VIP){
        int prezzo = this.currCarta.scegliTipologia(VIP);
        System.out.println("Il prezzo è di: " + prezzo + "€");
        return prezzo;
    }

    public void Pagamento() { 
        if (currCarta != null) {
            this.CarteFedeltà.put(currCarta.getCodice(), currCarta);
            int bianco = this.Colonna.get("01").remove();
            System.out.println("Carta stampata, rimanenti " + bianco);
            System.err.println("Pagare: " + costoCarta() + "€");
            if(bianco == 0) System.err.println("CARTE ESAURITE, RICARICARICARE");
        } 
    }

    public double costoCarta(){
        double costo = this.currCarta.getTipologia();
        if(this.currCarta.getGettoni() > 100)
        costo += (this.currCarta.getGettoni() - 100) * 0.5;
        return costo;
    }

    public String recuperoCarta(String CF) {
        String code = null;
        for (var entry : CarteFedeltà.entrySet()) {
            String reg = entry.getValue().ClienteAssociato.get(this.currCarta.IDcarta).getCF();
            if(reg == CF) { code = entry.getValue().IDcarta; this.inserisciCarta(code);}
        }
        if( code == null){ System.err.println("Nessuna carta trovata"); return null; }
        else { System.out.println("Carta trovata " + code ); return null; }
    }

    public void selezionaModalita(boolean mod) {
        if(mod == true){ this.currCarta.changeCodice(); }
        //*(Da rivedere)*\\
        this.CarteFedeltà.remove(currCarta.IDcarta);
        this.Pagamento();
    }

    public void inserisciCarta(String IDcarta){
        for (var entry : CarteFedeltà.entrySet()) {
            String ID = entry.getValue().getCodice();
            if(ID == IDcarta) { this.currCarta = entry.getValue(); }
        }
    }
    public void ricaricaGettoni(int gettoni){
        if(currCarta != null){
            this.currCarta.addGettoni(gettoni);
        }
        System.err.println("Gettoni ricaricati, pagare: " + (gettoni * 0.5) + "€");
    }

    public Carta getCartaCorrente() {
        System.out.println(currCarta);
        return currCarta;
    }

    public List<Carta> getElencoCarte() {
        List<Carta> listCarte = new ArrayList<>();
        listCarte.addAll(CarteFedeltà.values());
        System.out.println(CarteFedeltà);
        return listCarte;
    }

    //Cibo
    //*(Da rivedere)*\\
    public void InserisciCibo(String IDCibo, String nome, String descrizione, int q) {
        this.currCibo = new Cibo(IDCibo, nome, descrizione);
        for(int i = 0; i < q; i++){
            this.currCibo.addQuantità();
        }
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

}