package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

public class GUI{

    static IlPiattinodOro sistema;
    public static JTextArea textField, textCartaArea, textListaG, textListaP, textListaC, textPlace;
    public static JPanel GiocoButtons, PremioButtons, CiboButtons;

    private static JMenuBar Menu(){
        JMenuBar menu = new JMenuBar();
        JMenu x = new JMenu("Menu");
        JMenu y = new JMenu("Sistema");
            JMenuItem mGioco = new JMenuItem("Area Gioco");
            mGioco.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GiocoButtons.setVisible(true);
                    PremioButtons.setVisible(false);
                    CiboButtons.setVisible(false);
                    textCartaArea.setVisible(false);
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
                }
            });
        JMenuItem mCheck = new JMenuItem("Area Dipendenti");
        JMenuItem mCarta = new JMenuItem("Area Carta");
            mCarta.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PremioButtons.setVisible(false);
                    GiocoButtons.setVisible(false);
                    CiboButtons.setVisible(false);
                    textCartaArea.setVisible(true);
                }
            });
        y.add(mGioco);  y.add(mCibo);y.add(mPremio);
        x.add(y); x.add(mCheck); x.add(mCarta);
        menu.add(x);
        return menu;
    }

    private static JLabel Logo(){
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\utente\\Desktop\\Tesi\\tese\\Piatto\\IlPiattinodOro\\src\\main\\logo.png");
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

    public static void main(String[] args) {
            
        sistema = IlPiattinodOro.getInstance();
        JFrame frame = new JFrame("Il Piattino d'Oro");
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.GREEN);

        frame.setJMenuBar(Menu());
        frame.add(Logo(), BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        /*textField = new JTextArea();
        textField.setLineWrap(true);
        content.add(textField);
        utton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(textField.getText());
            }
        });*/

//Area Nuovo Gioco
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
        content.add(textListaG);

//Area Nuovo Premio
        textListaP = new JTextArea();
        textListaP.setLineWrap(true);
            JLabel np1 = new JLabel("Nome Premio"); JTextField np11 = new JTextField(10);
            JLabel np2 = new JLabel("Valore"); JTextField np22 = new JTextField(10);
            JLabel np3 = new JLabel("Descrizione"); JTextField np33 = new JTextField(10);
            JLabel np4 = new JLabel("Numero Copie"); JTextField np44 = new JTextField(10);
            JButton addPremio = new JButton("Aggiungi");
            textListaP.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
            textListaP.add(np1); textListaP.add(np11); textListaP.add(np2); textListaP.add(np22);
            textListaP.add(np3); textListaP.add(np33); textListaP.add(np4); textListaP.add(np44);
            textListaP.add(addPremio);
                addPremio.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String IDpremio = '0' + Integer.toString(sistema.mappaPremi.size() + 1);
                        sistema.InserisciPremio(IDpremio, np11.getText(), Integer.parseInt(np22.getText()), np33.getText(), Integer.parseInt(np44.getText()));
                        sistema.FineInserimentoPremio();
                    }
                });
            textListaP.setVisible(false);
        content.add(textListaP);

//Area Nuovo Cibo
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
        content.add(textListaC);

//Area Visualizzare Scritte
        textPlace = new JTextArea();
        textPlace.setLineWrap(true);
        textPlace.setFont(textPlace.getFont().deriveFont(16f));
        textPlace.setVisible(false);
        content.add(textPlace);

//Area Carta
        textCartaArea = new JTextArea();
        textCartaArea.setLineWrap(true);
            JLabel n1 = new JLabel("CF"); JTextField n11 = new JTextField(10);
            JLabel n2 = new JLabel("Nome"); JTextField n22 = new JTextField(10);
            JLabel n3 = new JLabel("Cognome"); JTextField n33 = new JTextField(10);
            JCheckBox n4 = new JCheckBox("VIP"); n4.setSelected(false);
            JButton add = new JButton("Aggiungi"); JButton n5 = new JButton("Lista Carte Registrate");
            textCartaArea.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
            textCartaArea.add(n1); textCartaArea.add(n11); textCartaArea.add(n2); textCartaArea.add(n22);
            textCartaArea.add(n3); textCartaArea.add(n33); textCartaArea.add(n4);
            textCartaArea.add(add); textCartaArea.add(n5);
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
                n5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cancellaTesto();
                        textPlace.append(sistema.getElencoCarte().toString());
                    }
                });
            textCartaArea.setVisible(false);
        content.add(textCartaArea);

//Pulsanti Area Gioco
        GiocoButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton button1 = new JButton("Elenco giochi");
        JButton button2 = new JButton("Aggiungi gioco");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancellaTesto();
                textPlace.append(sistema.getElencoGiochi().toString());
            }
        });
        GiocoButtons.add(button1);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textListaG.setVisible(!textListaG.isVisible());
            }
        });
        GiocoButtons.add(button2);  
        GiocoButtons.setVisible(false);
        content.add(GiocoButtons);

//Pulsanti Area Premio
        PremioButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton button3 = new JButton("Elenco Premi");
        JButton button4 = new JButton("Aggiungi Premi");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancellaTesto();
                textPlace.append(sistema.getElencoPremi().toString());
            }
        });
        PremioButtons.add(button3);
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textListaP.setVisible(!textListaP.isVisible());
            }
        });
        PremioButtons.add(button4);
        PremioButtons.setVisible(false);
        content.add(PremioButtons);

        
//Pulsanti Area Cibo
        CiboButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton button5 = new JButton("Elenco Cibo");
        JButton button6 = new JButton("Aggiungi Cibo");
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancellaTesto();
                textPlace.append(sistema.getElencoCibi().toString());
            }
        });
        CiboButtons.add(button5);
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textListaC.setVisible(!textListaP.isVisible());
            }
        });
        CiboButtons.add(button6);
        CiboButtons.setVisible(false);
        content.add(CiboButtons);

//
        frame.add(content, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setVisible(true);

    }
}