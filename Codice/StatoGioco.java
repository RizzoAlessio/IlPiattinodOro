package main;

public abstract class StatoGioco {
    public IlPiattinodOro sistema;

    StatoGioco(IlPiattinodOro oro) {
        this.sistema = oro;
    }

    public abstract boolean recover(String id);
    public abstract void taken();
}