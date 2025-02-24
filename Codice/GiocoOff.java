package main;

public class GiocoOff extends StatoGioco{

    String ID;

    public GiocoOff(IlPiattinodOro sistema) {
        super(sistema);
    }

    @Override
    public void taken() {
        System.out.println("Il gioco non è attivo");
    }

    @Override
    public boolean recover(String id) {
        return false;
    }
}
