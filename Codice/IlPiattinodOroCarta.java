package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class IlPiattinodOroCarta {

    private Map<String, Colonnina> Colonna;
    private Map<String, Gioco> GiochiDisponibili; //per il futuro
    private Carta currCarta;
    private Map<String, Carta> CarteFedeltà;
    public String Messaggio;

    public IlPiattinodOroCarta(Map<String, Gioco> GiochiDisponibili, Map<String, Colonnina> Colonna) {
        this.GiochiDisponibili= GiochiDisponibili;
        this.Colonna = Colonna;
        this.CarteFedeltà = new HashMap<>();
    }
  
    //Gestione Carta
    public void CreaNuovaCarta() {
        getCartaCorrente();
        int deposito = this.Colonna.get("01").getnumCarte();
        if(deposito != 0) { 
            Messaggio = "\nCarte Attuali: " + deposito;
            this.currCarta = new Carta();
        } else{ 
            Messaggio = "\nCARTE ESAURITE, IMPOSSIBILE PROSEGUIRE";
        }
        System.err.println(Messaggio);
    }
    
    public void InserisciDocumento(String CF, String nome, String cognome) {
        boolean registrato = false;
        for (Entry<String, Carta> entry : CarteFedeltà.entrySet()) {
            String prec = entry.getValue().getCliente();
            if(prec == CF) registrato = true;
        } if(!registrato) {
            this.currCarta.creaCliente(CF, nome, cognome);
            Messaggio = "\nCliente registrato"; 
        } else {  Messaggio = "\nCliente già registrato"; this.currCarta = null; }
        System.err.println(Messaggio);
    }

    public int scegliTipologia(boolean VIP){
        int prezzo = this.currCarta.scegliTipologia(VIP);
        Messaggio =  "\nIl prezzo è di: " + prezzo + "€";
        System.err.println(Messaggio);
        return prezzo;
    }

    public void Pagamento( boolean newCarta) { 
        this.CarteFedeltà.put(currCarta.getCodice(), currCarta);
        int bianco = this.Colonna.get("01").remove();
        if (bianco == 0) { Messaggio = "\nCARTE ESAURITE, RICARICARE"; }
        else { Messaggio = "\nCarta stampata, rimanenti " + bianco; }
        if (newCarta) Messaggio += "\nPagare: " + costoCarta() + "€";
        this.currCarta = null;
        System.err.println(Messaggio);
    }

    public double costoCarta(){
        double costo = this.currCarta.getTipologia();
        if(this.currCarta.getGettoni() > 100)
        costo += (this.currCarta.getGettoni() - 100) * 0.5;
        return costo;
    }

    public String recuperoCarta(String CF) {
        String code = null;
        for (Entry<String, Carta> entry : CarteFedeltà.entrySet()) {
            String reg = entry.getValue().ClienteAssociato.get(this.currCarta.IDcarta).getCF();
            if(reg == CF) { code = entry.getValue().IDcarta; this.inserisciCarta(code);}
        }
        if( code == null){ System.err.println("Nessuna carta trovata"); return null; }
        else { System.out.println("Carta trovata " + code ); return code; }
    }

    public void cambioCodice(boolean mod) {
        this.CarteFedeltà.remove(currCarta.IDcarta);
        if(mod == true){ this.currCarta.changeCodice(); }
        this.Pagamento(false);
    }

    public void inserisciCarta(String IDcarta){
        for (Entry<String, Carta> entry : CarteFedeltà.entrySet()) {
            String ID = entry.getValue().getCodice();
            if(ID == IDcarta) { this.currCarta = entry.getValue(); }
        }
    }
    public void ricaricaGettoni(int gettoni){
        if(currCarta != null){
            this.currCarta.addGettoni(gettoni);
        }
        Messaggio = "Gettoni ricaricati, pagare: " + (gettoni * 0.5) + "€";
        System.err.println(Messaggio);
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
        System.out.println(listCarte);
        return listCarte;
    }

}