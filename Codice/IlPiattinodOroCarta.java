package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class IlPiattinodOroCarta {

    private Map<String, Colonnina> Colonna;
    private Map<String, Gioco> GiochiDisponibili; //per il futuro
    private Carta currCarta;
    private Map<String, Carta> CarteFedeltà;

    public IlPiattinodOroCarta(Map<String, Gioco> GiochiDisponibili, Map<String, Colonnina> Colonna) {
        this.GiochiDisponibili= GiochiDisponibili;
        this.Colonna = Colonna;
        this.CarteFedeltà = new HashMap<>();
    }
  
    //Gestione Carta
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
        else { System.out.println("Carta trovata " + code ); return code; }
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

    public void inserimentoCell(String tel){
        this.currCarta.inserisciCell(tel);
    }

    public Carta getCartaCorrente() {
        System.out.println(currCarta);
        return currCarta;
    }
    public Carta getCarta(String IDCarta) {
        return this.CarteFedeltà.get(IDCarta);
    }
    public List<Carta> getElencoCarte() {
        List<Carta> listCarte = new ArrayList<>();
        listCarte.addAll(CarteFedeltà.values());
        System.out.println(CarteFedeltà);
        return listCarte;
    }

}

