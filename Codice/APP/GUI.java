package main.APP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.Map.Entry;

import javax.swing.*;

import main.Carta;
import main.Dipendente;
import main.IlPiattinodOro;
import main.Partita;

public class GUI{

    static IlPiattinodOro sistema;
    static GiocoGame gioco;
    public static JTextArea textField, textCartaArea, textListaG, textListaP, textListaC, textPlace, textGiocaG;
    public static JPanel GiocoButtons, PremioButtons, CiboButtons, Customer;

    private static JMenuBar Menu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenu syst = new JMenu("Sistema");
            JMenuItem mGioco = new JMenuItem("Area Gioco");
            mGioco.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GiocoButtons.setVisible(true);
                    PremioButtons.setVisible(false);
                    CiboButtons.setVisible(false);
                    textCartaArea.setVisible(false);
                    Customer.setVisible(false);
                }
            });
            JMenuItem mCibo = new JMenuItem("Area Cibo");
            mCibo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PremioButtons.setVisible(false);
                    GiocoButtons.setVisible(false);
                    CiboButtons.setVisible(true);
                    textCartaArea.setVisible(false);
                    Customer.setVisible(false);
                }
            });
            JMenuItem mPremio = new JMenuItem("Area Premio");
            mPremio.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PremioButtons.setVisible(true);
                    GiocoButtons.setVisible(false);
                    CiboButtons.setVisible(false);
                    textCartaArea.setVisible(false);
                    Customer.setVisible(false);
                }
            });
        JMenuItem mCheck = new JMenuItem("Area Dipendenti");
        mCheck.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PremioButtons.setVisible(false);
                    GiocoButtons.setVisible(false);
                    CiboButtons.setVisible(false);
                    textCartaArea.setVisible(false);
                    Customer.setVisible(true);
                }
            });
        JMenuItem mCarta = new JMenuItem("Area Carta");
            mCarta.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PremioButtons.setVisible(false);
                    GiocoButtons.setVisible(false);
                    CiboButtons.setVisible(false);
                    textCartaArea.setVisible(true);
                    Customer.setVisible(false);
                }
            });
        syst.add(mGioco);  syst.add(mCibo);syst.add(mPremio);
        menu.add(syst); menu.add(mCheck); menu.add(mCarta);
        menuBar.add(menu);
        return menuBar;
    }

    private static JLabel Logo(){
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\utente\\Desktop\\Cartella Codici\\Codici\\Piatto\\IlPiattinodOro\\src\\main\\logo.png");
            java.awt.Image i = imageIcon.getImage();
            java.awt.Image i2 = i.getScaledInstance(500, 280,  java.awt.Image.SCALE_SMOOTH);
            ImageIcon imageIcon2 = new ImageIcon(i2);
        JLabel imageLabel = new JLabel(imageIcon2);
        imageLabel.setBackground(Color.GREEN);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        return imageLabel;
    }

    private static void cancellaTesto(){
        textPlace.setVisible(!textPlace.isVisible());
        textPlace.selectAll();
        textPlace.replaceSelection("");
    }

    private static JTextArea AreaNuovoGioco(){
        textListaG = new JTextArea();
        textListaG.setLineWrap(true);
            JLabel ng1 = new JLabel("Nome Gioco"); JTextField ng11 = new JTextField(10);
            JLabel ng2 = new JLabel("Tipologia"); JTextField ng22 = new JTextField(10);
            JLabel ng3 = new JLabel("Numero Giocatori"); JTextField ng33 = new JTextField(10);
            JLabel ng4 = new JLabel("Prezzo"); JTextField ng44 = new JTextField(10);
            JButton addGame = new JButton("Aggiungi");
            textListaG.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
            textListaG.add(ng1); textListaG.add(ng11); textListaG.add(ng2); textListaG.add(ng22);
            textListaG.add(ng3); textListaG.add(ng33); textListaG.add(ng4); textListaG.add(ng44);
            textListaG.add(addGame);
                addGame.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cancellaTesto();
                        String IDgioco = '0' + Integer.toString(sistema.GiochiDisponibili.size() + 1);
                        sistema.InserisciGioco(IDgioco, ng11.getText(), ng22.getText(), Integer.parseInt(ng33.getText()));
                        sistema.DefinisciCosto(Integer.parseInt(ng44.getText()));
                        sistema.FineInserimentoGioco();
                        textPlace.append("Inserito il gioco " + ng11.getText());
                        ng11.setText(""); ng22.setText(""); ng33.setText(""); ng44.setText("");
                    }
                });
            textListaG.setVisible(false);
        return textListaG;
    }

    private static JTextArea AreaGioco(){
        textGiocaG = new JTextArea();
        textGiocaG.setLineWrap(true);
            JLabel cg = new JLabel("Codice Gioco"); JTextField cg1 = new JTextField(10);
            JLabel cp = new JLabel("Codice Giocatore"); JTextField cp1 = new JTextField(10);
            JLabel gg = new JLabel("Num Giocatori"); JTextField gg1 = new JTextField(2);
            JButton findP = new JButton("Richiesta Partita");
            JButton findCurr = new JButton("Partita Corrente");
            JButton findPunti = new JButton("Punteggio Partita");
            textGiocaG.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
            textGiocaG.add(cg); textGiocaG.add(cg1); textGiocaG.add(cp); textGiocaG.add(cp1);
            textGiocaG.add(gg); textGiocaG.add(gg1);
            textGiocaG.add(findP); textGiocaG.add(findPunti); textGiocaG.add(findCurr);
            findCurr.setVisible(false);
                findPunti.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sistema.puntiGiocoTot(cp1.getText());
                    }
                });
                findCurr.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cancellaTesto();
                        Partita IDpartita = sistema.recuperaPartita();
                        System.out.println(IDpartita);
                        textPlace.append("Partita Attuale: \n");
                        textPlace.append("" + sistema.monitoraPartita(IDpartita.getCodice()) + "* " + gioco.p + "pt ");
                    }
                });
                findP.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cancellaTesto();
                        String gett = sistema.richiestaPartita(cp1.getText(), cg1.getText());
                        if(gett != null){
                            sistema.avviaPartita(cp1.getText(), cg1.getText(), Integer.parseInt(gg1.getText()));
                            String gio = sistema.GiochiDisponibili.get(cg1.getText()).getNome();
                            textPlace.append(gett + " ...avvio..." + gio);
                            gioco = new GiocoGame();
                            gioco.GameG(gio, sistema);
                            cp1.setText(""); cg1.setText(""); gg1.setText("");
                            findCurr.setVisible(!findCurr.isVisible());
                        }
                    }
                });
            textGiocaG.setVisible(false);
        return textGiocaG;
    }

    private static JTextArea AreaNuovoPremio(){
        textListaP = new JTextArea();
        textListaP.setLineWrap(true);
            JLabel np1 = new JLabel("Nome Premio"); JTextField np11 = new JTextField(10);
            JLabel np2 = new JLabel("Valore"); JTextField np22 = new JTextField(10);
            JLabel np3 = new JLabel("Descrizione"); JTextField np33 = new JTextField(10);
            JLabel np4 = new JLabel("Numero Copie"); JTextField np44 = new JTextField(10);
            JButton addPremio = new JButton("Aggiungi"); JButton buyPremio = new JButton("Riscatta");
            JButton buyPremio1 = new JButton("Cerca per costo"); JButton buyPremio2 = new JButton("Compra");
            textListaP.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
            textListaP.add(np1); textListaP.add(np11); textListaP.add(np2); textListaP.add(np22);
            textListaP.add(np3); textListaP.add(np33); textListaP.add(np4); textListaP.add(np44);
            textListaP.add(addPremio); textListaP.add(buyPremio); 
            textListaP.add(buyPremio1).setVisible(false); textListaP.add(buyPremio2).setVisible(false);
            //----------------//
            JLabel nom = new JLabel("Inserici Tessera"); JTextField nomm = new JTextField(10); JButton invio = new JButton("Invio");
            textListaP.add(nom).setVisible(false);  textListaP.add(nomm).setVisible(false); textListaP.add(invio).setVisible(false); 
                addPremio.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String IDpremio = '0' + Integer.toString(sistema.mappaPremi.size() + 1);
                        sistema.InserisciPremio(IDpremio, np11.getText(), Integer.parseInt(np22.getText()), np33.getText(), Integer.parseInt(np44.getText()));
                        sistema.FineInserimentoPremio();
                    }
                });
                buyPremio.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        nom.setVisible(!nom.isVisible()); nomm.setVisible(!nomm.isVisible()); invio.setVisible(!invio.isVisible());  
                            invio.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    cancellaTesto();
                                    Carta c = sistema.gestore.getCarta(nomm.getText());
                                    int tot = sistema.inserisciTessera(c.getCodice());
                                    textPlace.append("Sono disponibili " + tot + " punti");
                                    textListaP.add(buyPremio1).setVisible(!buyPremio1.isVisible());
                                }}); 
                }});
                buyPremio1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cancellaTesto();
                        String totPremi = sistema.cercaPremio(Integer.parseInt(np22.getText()));
                        textPlace.append(totPremi);
                        textListaP.add(buyPremio2).setVisible(!buyPremio2.isVisible());
                    }
                });
                buyPremio2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cancellaTesto();
                        String IDp = sistema.getPremioNome(np11.getText());
                        String IDc = nomm.getText();
                        int reTot = sistema.scegliPremio(IDp, IDc);
                        textPlace.append("Sono disponibili ora " + reTot + " punti\n Goditi il tuo " + np11.getText());
                    }
                });
            textListaP.setVisible(false);
        return textListaP;
    }
    
    private static JTextArea AreaNuovoCibo(){
        textListaC = new JTextArea();
        textListaC.setLineWrap(true);
            JLabel nc1 = new JLabel("Nome Cibo"); JTextField nc11 = new JTextField(10);
            JLabel nc2 = new JLabel("Descrizione"); JTextField nc22 = new JTextField(10);
            JLabel nc3 = new JLabel("Costo"); JTextField nc33 = new JTextField(10);
            JLabel nc4 = new JLabel("Numero Copie"); JTextField nc44 = new JTextField(10);
            JButton addCibo = new JButton("Aggiungi");
            textListaC.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
            textListaC.add(nc1); textListaC.add(nc11); textListaC.add(nc2); textListaC.add(nc22);
            textListaC.add(nc4); textListaC.add(nc44); textListaC.add(nc3); textListaC.add(nc33);
            textListaC.add(addCibo);
                addCibo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String IDcibo = '0' + Integer.toString(sistema.mappaCibi.size() + 1);
                        sistema.InserisciCibo(IDcibo, nc11.getText(), nc22.getText(), Integer.parseInt(nc44.getText()));
                        sistema.DefinisciCostoCibo(Integer.parseInt(nc33.getText()));
                        sistema.FineInserimentoCibo();
                    }
                });
            textListaC.setVisible(false);
        return textListaC;
    }

    private static JTextArea AreaCarta(){
        textCartaArea = new JTextArea();
        textCartaArea.setLineWrap(true);
            JLabel n1 = new JLabel("CF"); JTextField n11 = new JTextField(10);
            JLabel n2 = new JLabel("Nome"); JTextField n22 = new JTextField(10);
            JLabel n3 = new JLabel("Cognome"); JTextField n33 = new JTextField(10);
            JCheckBox n4 = new JCheckBox("VIP"); n4.setSelected(false);
            JLabel n5 = new JLabel("Gettoni aggiuntivi"); JTextField n55 = new JTextField(3);
            JButton add = new JButton("Aggiungi"); JButton list = new JButton("Lista Carte Registrate");
            JButton recov = new JButton("Recupera Carta"); JButton ric = new JButton("Ricarica Carta");
            textCartaArea.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
            textCartaArea.add(n1); textCartaArea.add(n11); textCartaArea.add(n2); textCartaArea.add(n22);
            textCartaArea.add(n3); textCartaArea.add(n33); textCartaArea.add(n5); textCartaArea.add(n55);
            textCartaArea.add(n4);
            textCartaArea.add(add); textCartaArea.add(list);
            textCartaArea.add(recov); textCartaArea.add(ric);
                add.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cancellaTesto();
                        sistema.CreaNuovaCarta();
                        textPlace.append(sistema.gestore.Messaggio);
                        sistema.InserisciDocumento(n11.getText(), n22.getText(), n33.getText());
                        if(n4.isSelected()) {sistema.scegliTipologia(true); }
                        else {sistema.scegliTipologia(false);}
                        textPlace.append(sistema.gestore.Messaggio);
                        sistema.Pagamento();
                        textPlace.append(sistema.gestore.Messaggio);
                        n11.setText(""); n22.setText(""); n33.setText("");n4.setSelected(false);
                    }
                });
                list.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CartePulsante c = new CartePulsante();
                        c.execute(textPlace);
                    }
                });
                recov.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cancellaTesto();
                        String rec = sistema.recuperoCarta(n11.getText());
                        textPlace.append(rec);
                    }
                });
                ric.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       // sistema.inserisciCarta();
                        sistema.ricaricaGettoni(Integer.parseInt(n55.getText()));
                        textPlace.append("Gettoni Caricati");
                    }
                });
            textCartaArea.setVisible(false);
        return textCartaArea;
    }

    private static JPanel AreaDipendenti(){

        JPanel textDip = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
            JLabel CK1 = new JLabel("ID"); JTextField CK11 = new JTextField(10);
            JLabel CK2 = new JLabel("Orario"); JTextField CK22 = new JTextField(10);
            JLabel CK3 = new JLabel("Giorno"); JTextField CK33 = new JTextField(10);
            JButton okButton = new JButton("Invio");
            textDip.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
            textDip.add(CK1); textDip.add(CK11); textDip.add(CK2); textDip.add(CK22); textDip.add(CK3); textDip.add(CK33);
            textDip.add(okButton);
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cancellaTesto();
                        sistema.Check_in(CK11.getText(), Integer.parseInt(CK22.getText()), CK33.getText());
                        textDip.setVisible(false);
                        textPlace.append("Buon Lavoro");
                    }
                });
        textDip.setVisible(false);

        Customer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel d1 = new JLabel("CF"); JTextField d11 = new JTextField(10);
        JLabel d2 = new JLabel("Codice"); JTextField d22 = new JTextField(10);
        JButton b1 = new JButton("Autenticazione");
        JButton b2 = new JButton("CHECK IN"); JButton b3 = new JButton("CHECK OUT");
        JButton b4 = new JButton("Monitora"); JButton b5 = new JButton("Return");
        Customer.add(d1); Customer.add(d11); Customer.add(d2); Customer.add(d22);
        Customer.add(b2).setVisible(false); Customer.add(b3).setVisible(false);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               cancellaTesto();
               boolean[] risu = sistema.VerificaCredenziali(d11.getText(), d22.getText());
               if(risu[0] && risu[1] == false){ 
                for(Entry<Integer, Dipendente> entry : sistema.mappaDipendenti.entrySet()){
                    if((entry.getValue().getCF().equals(d11.getText())) && (entry.getValue().getPass().equals(d22.getText()))) {
                    textPlace.append("Utente " + entry.getValue().getID() + " verificato");
                } } 
                    d1.setVisible(false); d2.setVisible(false); d11.setVisible(false); d22.setVisible(false);
                    Customer.add(b1).setVisible(false); Customer.add(b2).setVisible(true);
                    Customer.add(b3).setVisible(true); Customer.add(b5).setVisible(true);
               } else if(risu[1]){ 
                    textPlace.append("Benvenuto Admin");
                    d1.setVisible(false); d2.setVisible(false); d11.setVisible(false); d22.setVisible(false);
                    Customer.add(CK3); Customer.add(CK33);
                    Customer.add(b1).setVisible(false); Customer.add(b4).setVisible(true); Customer.add(b5).setVisible(true);
               }
            }
        });
        Customer.add(b1);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancellaTesto();
                textDip.setVisible(!textDip.isVisible());
            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(sistema.getDipendente() != null){
                    textPlace.append("");
                    sistema.Check_Out();
                    d1.setVisible(true); d2.setVisible(true); d11.setVisible(true); d22.setVisible(true);
                    Customer.add(b1).setVisible(true);
                    Customer.add(b2).setVisible(false);
                    Customer.add(b3).setVisible(false);
                    Customer.add(b4).setVisible(false);
                    Customer.add(b5).setVisible(false);
            }
            }
        });
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancellaTesto();
                String admList = sistema.MonitoraDipendenti(CK33.getText());
                textPlace.append(admList);
                
            }
        });
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancellaTesto();
                d1.setVisible(true); d2.setVisible(true); d11.setVisible(true); d22.setVisible(true);
                CK3.setVisible(false); CK33.setVisible(false);
                    Customer.add(b1).setVisible(true);
                    Customer.add(b2).setVisible(false);
                    Customer.add(b3).setVisible(false);
                    Customer.add(b4).setVisible(false);
                    Customer.add(b5).setVisible(false);
                
            }
        });
        Customer.add(b4).setVisible(false);
        Customer.add(textDip);
        Customer.setVisible(false);
        return Customer;
    }

    public static void main(String[] args) {
            
        sistema = IlPiattinodOro.getInstance();
        JFrame frame = new JFrame("Il Piattino d'Oro");
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.GREEN);
        frame.setJMenuBar(Menu());
        frame.add(Logo(), BorderLayout.NORTH);
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

//Area Nuovo Gioco
        content.add(AreaNuovoGioco());
//Area Gioco
        content.add(AreaGioco());
//Area Nuovo Premio
        content.add(AreaNuovoPremio());
//Area Nuovo Cibo
        content.add(AreaNuovoCibo());
//Area Visualizzare Scritte
        textPlace = new JTextArea();
        textPlace.setLineWrap(true);
        textPlace.setFont(textPlace.getFont().deriveFont(16f));
        textPlace.setVisible(false);
        content.add(textPlace);
//Area Carta
        content.add(AreaCarta());
//Pulsanti Area Gioco
        GiocoButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton elGiochi = new JButton("Elenco giochi");
        JButton addGiohi = new JButton("Aggiungi gioco");
        JButton Play = new JButton("Gioca");
        elGiochi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GiochiPulsante c = new GiochiPulsante();
                c.execute(textPlace);
            }
        });
        GiocoButtons.add(elGiochi);
        addGiohi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textListaG.setVisible(!textListaG.isVisible());
            }
        });
        GiocoButtons.add(addGiohi);  
        Play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textGiocaG.setVisible(!textGiocaG.isVisible());
            }
        });
        GiocoButtons.add(Play); 
        GiocoButtons.setVisible(false);
        content.add(GiocoButtons);
//Pulsanti Area Premio
        PremioButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton elPremi = new JButton("Elenco Premi");
        JButton addPremi = new JButton("Aggiungi Premi");
        JButton risc = new JButton("Riscatta Premi");
        elPremi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PremioPulsante c = new PremioPulsante();
                c.execute(textPlace);
            }
        });
        PremioButtons.add(elPremi);
        addPremi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textListaP.setVisible(!textListaP.isVisible());
            }
        });
        PremioButtons.add(addPremi);
        risc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textListaP.setVisible(!textListaP.isVisible());
            }
        });
        PremioButtons.add(risc);
        PremioButtons.setVisible(false);
        content.add(PremioButtons);  
//Pulsanti Area Cibo
        CiboButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton elCibo = new JButton("Elenco Cibo");
        JButton addCibo = new JButton("Aggiungi Cibo");
        elCibo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CiboPulsante c = new CiboPulsante();
                c.execute(textPlace);
            }
        });
        CiboButtons.add(elCibo);
        addCibo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textListaC.setVisible(!textListaP.isVisible());
            }
        });
        CiboButtons.add(addCibo);
        CiboButtons.setVisible(false);
        content.add(CiboButtons);
//Area Dipendenti
        content.add(AreaDipendenti());
//
        frame.add(content, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setVisible(true);
    }

}
