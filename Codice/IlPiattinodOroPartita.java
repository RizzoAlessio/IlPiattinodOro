package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.time.LocalDate;


public class IlPiattinodOroPartita {

    private IlPiattinodOroCarta gestoreCarta;
    private Map<String, Gioco> GiochiDisponibili;
    private Partita currPartita;
    private Map<String, Partita> partiteAttuali;

    public IlPiattinodOroPartita(Map<String, Gioco> GiochiDisponibili, IlPiattinodOroCarta gestore) {
        this.GiochiDisponibili= GiochiDisponibili;
        this.gestoreCarta = gestore;
        this.partiteAttuali = new HashMap<>();
    }

    public String richiestaPartita(String IDCarta, String IDGioco){
        Carta carta = this.gestoreCarta.getCarta(IDCarta);
        Gioco gioco = GiochiDisponibili.get(IDGioco);
        if(carta.getGettoni() >= gioco.getCosto()) return "Gettoni sufficenti";
        return null;
    }

    public void avviaPartita(String IDCarta, String IDGioco, int giocatori){
        Carta carta = this.gestoreCarta.getCarta(IDCarta);
        Gioco gioco = GiochiDisponibili.get(IDGioco);
        this.gestoreCarta.getCarta(IDCarta).addGettoni(-gioco.getCosto());
        String data = LocalDate.now().toString();
        this.currPartita = new Partita(carta, gioco, giocatori, data);
        String partita = currPartita.getCodice();
        System.out.println("Avvio partita: " + partita);
        addPartita(partita, currPartita);  
    }

    private void addPartita(String p, Partita pt){
        this.partiteAttuali.put(p, pt); 
    }
    private void addPuntiPartita(int punt, Partita pt){
        this.currPartita.setPunteggio(punt);
    }

    public void finePartita(int punteggio){
        addPuntiPartita(punteggio, currPartita);
        String IDcarta = this.currPartita.getCarta().getCodice();
        String IDgioco = this.currPartita.getGioco().getCodice();
        this.gestoreCarta.getCarta(IDcarta).addPunti(IDgioco, punteggio);
        this.GiochiDisponibili.get(IDgioco).setPunteggio(IDcarta, punteggio);
    }
    
    public void continua(boolean c){
        if(c){
            String IDcarta = this.currPartita.getCarta().getCodice();
            String IDgioco = this.currPartita.getGioco().getCodice();
            this.partiteAttuali.remove(currPartita.getCodice());
            this.currPartita = null;
            this.richiestaPartita(IDcarta, IDgioco);
        } else {
            this.partiteAttuali.remove(currPartita.getCodice());
            this.currPartita = null;
        }
    }

    public void recuperaPartita(String gioco){
        for (Entry<String, Gioco> entry : GiochiDisponibili.entrySet()) {
            if(gioco == entry.getValue().getNome()){
                String IDgioco = entry.getValue().getCodice();
                for(Entry<String, Partita> entity : partiteAttuali.entrySet()){
                    if(entity.getValue().getGioco().getCodice() == IDgioco){
                        String partita = entity.getValue().getCodice();
                        System.out.println("Trovata partita per il gioco " + gioco + ": " + partita );
                    }
                }
            }
        } 
    }

    public Partita monitoraPartita(String IDpartita){
        Partita vista = partiteAttuali.get(IDpartita);
        System.out.println(vista);
        return vista;
    }

    public Map<String, Partita> getPartite(){
        return partiteAttuali;
    }

}