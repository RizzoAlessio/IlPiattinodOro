package main;

import java.util.Map.Entry;

public class GiocoOn extends StatoGioco{

    String ID;

    public GiocoOn(IlPiattinodOro sistema) {
        super(sistema);
    }

    @Override
    public boolean recover(String IDgioco) {
        ID = IDgioco;
        return sistema.GiochiDisponibili.get(IDgioco).getStato();
    }

    @Override
    public void taken() {
        sistema.partiteAttuali = sistema.arbitro.getPartite();
            for(Entry<String, Partita> entity : sistema.partiteAttuali.entrySet()){
                if(entity.getValue().getGioco().getCodice() == ID){
                    String partita = entity.getValue().getCodice();
                    System.out.println("Il gioco è attivo e con partita in corso: " + partita);
                } else {
                    System.out.println("Il gioco è attivo e disponibile");
                }
            }
    }
}
