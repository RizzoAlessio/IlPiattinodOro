package test;
import org.junit.Test;
import main.IlPiattinodOro;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

public class TestGioco {

    static IlPiattinodOro sistema;

    @BeforeClass
    public static void initTest() {
        sistema = IlPiattinodOro.getInstance();
    }

    @Test
    public void testInserisciNuovoGioco() {
        sistema.InserisciGioco("04", "Pac Man", "Cabinato", 1);
        assertNotNull(sistema.getGiocoCorrente());
    }

    @Test
    public void testInserisciPrezzo() {
        sistema.InserisciGioco("04", "Pac Man", "Cabinato", 1);
        assertNotNull(sistema.getGiocoCorrente());
        sistema.DefinisciCosto(12);
        assertNotNull(sistema.getGiocoCorrente());
    }

    @Test
    public void testConferma() {
        sistema.InserisciGioco("04", "Pac Man", "Cabinato", 1);
        assertNotNull(sistema.getGiocoCorrente());
        sistema.DefinisciCosto(12);
        assertNotNull(sistema.getGiocoCorrente());
        sistema.FineInserimentoGioco();
        assertEquals(4, sistema.getElencoGiochi().size());
    }

    @Test
    public void testInserisciPremio() {
        sistema.InserisciPremio("01", "Peluche", 100, "Peluche a forma di unicottero", 3);
        assertNotNull(sistema.getPremioCorrente());
    }

    @Test
    public void testConfermaPremio() {
        sistema.InserisciPremio("01", "Peluche", 100, "Peluche a forma di unicottero", 3);      
        sistema.getPremioCorrente().getElencoCopiePremio();
        sistema.FineInserimentoPremio();
        sistema.getElencoPremi();
    }

    @Test
    public void testConfermaCibo() {
        sistema.InserisciCibo("01", "Panino","Panino tonno e marmellata", 2);      
        sistema.DefinisciCostoCibo(3);
        sistema.FineInserimentoCibo();
        sistema.getElencoCibi();
    }


    @Test
    public void testNuovaCarta() {
        sistema.CreaNuovaCarta();
        assertNotNull(sistema.getCartaCorrente());
        
    }
    @Test
    public void testNuovoDocumento() {
        sistema.CreaNuovaCarta();
        sistema.InserisciDocumento("ABC2025", "Marco", "Pesalonbi");
        assertNotNull(sistema.getCartaCorrente().getCliente());
    }
    @Test
    public void testTipologia() {
        sistema.CreaNuovaCarta();
        sistema.InserisciDocumento("ABC2025", "Marco", "Pesalonbi");
        assertEquals(25, sistema.scegliTipologia(true));
    }
    @Test
    public void testPagamentoCarta() {
        sistema.CreaNuovaCarta();
        sistema.InserisciDocumento("ABC2025", "Marco", "Pesalonbi");
        sistema.scegliTipologia(false);
        sistema.Pagamento();
        assertEquals(1, sistema.getElencoCarte().size());
    }
    @Test
    public void testRecuperoCarta() {
        this.testPagamentoCarta();
        sistema.getElencoCarte();
        sistema.recuperoCarta("ABC2025");
    }
    @Test
    public void testNuovaVecchiaCarta() {
        this.testPagamentoCarta();
        sistema.getElencoCarte();
        sistema.recuperoCarta("ABC2025");
        sistema.cambioCodice(false);
        sistema.getElencoCarte();

    }
    @Test
    public void testNuovaNuovaVecchiaCarta() {
        this.testPagamentoCarta();
        sistema.getElencoCarte();
        sistema.recuperoCarta("ABC2025");
        sistema.cambioCodice(true);
        sistema.getElencoCarte();
    }
    @Test
    public void testCaricaGettoni() {
        this.testPagamentoCarta();
        sistema.inserisciCarta(sistema.getElencoCarte().get(0).getCodice());
        sistema.ricaricaGettoni(100);
        sistema.getElencoCarte();
    }
    @Test
    public void testnuovoconGettoni() {
        sistema.CreaNuovaCarta();
        sistema.InserisciDocumento("ABC2025", "Marco", "Pesalonbi");
        sistema.scegliTipologia(true);
        sistema.ricaricaGettoni(100);
        sistema.Pagamento();
        sistema.getElencoCarte();
    }
    @Test
    public void testPrenotazione() {
        sistema.CreaNuovaCarta();
        sistema.InserisciDocumento("ABC2025", "Marco", "Pesalonbi");
        sistema.scegliTipologia(true);
        sistema.ricaricaGettoni(100);
        String ID = sistema.getCartaCorrente().getCodice();
        sistema.Pagamento();
        sistema.getElencoCarte();
        sistema.disponibilita("3 Aprile", 18);
        sistema.creaPrenotazione(ID, "01", 3, "3 Aprile", 18);
        sistema.finePrenotazione();
        sistema.getElencoPrenotazioni();
        sistema.disponibilita("3 Aprile", 18);
        sistema.disponibilita("3 Aprile", 19);
    }
    @Test
    public void testPartita() {
        sistema.CreaNuovaCarta();
        sistema.InserisciDocumento("ABC2025", "Marco", "Pesalonbi");
        sistema.scegliTipologia(true);
        sistema.ricaricaGettoni(100);
        String ID = sistema.getCartaCorrente().getCodice();
        sistema.Pagamento();
        sistema.richiestaPartita(ID, "01");
        sistema.avviaPartita(ID, "01", 1);
        sistema.recuperaPartita("Biliardo");
        sistema.monitoraPartita("2025-02-18I0");
        sistema.statoGioco("01");
        sistema.statoGioco("03");
        sistema.finePartita(1000);
        sistema.continua(false);
        sistema.inserisciTessera(ID);
        sistema.cercaPremio(1000);
        sistema.scegliPremio("001", ID);
    }

    @Test
    public void testPartitaClassifica() {
        sistema.CreaNuovaCarta();
        sistema.InserisciDocumento("ABC2025", "Marco", "Pesalonbi");
        sistema.scegliTipologia(true);
        sistema.ricaricaGettoni(100);
        String ID = sistema.getCartaCorrente().getCodice();
        sistema.Pagamento();
        sistema.richiestaPartita(ID, "01");
        sistema.avviaPartita(ID, "01", 1);
        sistema.finePartita(100);
        sistema.continua(false);
        sistema.inserisciTessera(ID);
        sistema.puntiGiocoTot(ID);

        sistema.CreaNuovaCarta();
        sistema.InserisciDocumento("ABC2026", "Luigi", "Pesalonbi");
        sistema.scegliTipologia(true);
        sistema.ricaricaGettoni(100);
        String ID2 = sistema.getCartaCorrente().getCodice();
        sistema.Pagamento();
        sistema.richiestaPartita(ID2, "01");
        sistema.avviaPartita(ID2, "01", 1);
        sistema.finePartita(99);
        sistema.continua(false);
        sistema.inserisciTessera(ID2);
        sistema.puntiGiocoTot(ID2);
        sistema.puntiGiocoTot(ID);
    }

}
