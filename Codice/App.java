package main;
public class App {
    public static void main(String[] args) {
        IlPiattinodOro systemOro = IlPiattinodOro.getInstance();
        systemOro.loadGiochi();
        System.out.println("erdtfg");
        systemOro.getGioco("01");
    }
}
