package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

public class GUI{

    static IlPiattinodOro sistema;
    public static JTextArea textField, textLista, textListaP, textListaC, textList;
    public static JLabel n1, n2, n3, n4;
    public static JTextField n11, n22, n33, n44;
    public static JButton add;
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
                }
            });
            JMenuItem mCibo = new JMenuItem("Area Cibo");
            mCibo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PremioButtons.setVisible(false);
                    GiocoButtons.setVisible(false);
                    CiboButtons.setVisible(true);
                }
            });
            JMenuItem mPremio = new JMenuItem("Area Premio");
            mPremio.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PremioButtons.setVisible(true);
                    GiocoButtons.setVisible(false);
                    CiboButtons.setVisible(false);
                }
            });
        JMenuItem mCheck = new JMenuItem("Area Dipendenti");
            y.add(mGioco);  y.add(mCibo);y.add(mPremio);
            x.add(y); x.add(mCheck);
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

        textLista = new JTextArea();
        textLista.setLineWrap(true);
            n1 = new JLabel("Nome Gioco"); n11 = new JTextField(10);
            n2 = new JLabel("Tipologia"); n22 = new JTextField(10);
            n3 = new JLabel("Numero Giocatori"); n33 = new JTextField(10);
            n4 = new JLabel("Prezzo"); n44 = new JTextField(10);
            add = new JButton("Aggiungi");
            textLista.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
            textLista.add(n1); textLista.add(n11); textLista.add(n2); textLista.add(n22);
            textLista.add(n3); textLista.add(n33); textLista.add(n4); textLista.add(n44);
            textLista.add(add);
                add.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String IDgioco = '0' + Integer.toString(sistema.GiochiDisponibili.size() + 1);
                        sistema.InserisciGioco(IDgioco, n11.getText(), n22.getText(), Integer.parseInt(n33.getText()));
                        sistema.DefinisciCosto(Integer.parseInt(n44.getText()));
                        sistema.FineInserimentoGioco();
                    }
                });
            textLista.setVisible(false);
        content.add(textLista);

        textListaP = new JTextArea();
        textListaP.setLineWrap(true);
            n1 = new JLabel("Nome Premio"); n11 = new JTextField(10);
            n2 = new JLabel("Valore"); n22 = new JTextField(10);
            n3 = new JLabel("Descrizione"); n33 = new JTextField(10);
            n4 = new JLabel("Numero Copie"); n44 = new JTextField(10);
            add = new JButton("Aggiungi");
            textListaP.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
            textListaP.add(n1); textListaP.add(n11); textListaP.add(n2); textListaP.add(n22);
            textListaP.add(n3); textListaP.add(n33); textListaP.add(n4); textListaP.add(n44);
            textListaP.add(add);
                add.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String IDpremio = '0' + Integer.toString(sistema.mappaPremi.size() + 1);
                        sistema.InserisciPremio(IDpremio, n11.getText(), Integer.parseInt(n22.getText()), n33.getText(), Integer.parseInt(n44.getText()));
                        sistema.FineInserimentoPremio();
                    }
                });
            textListaP.setVisible(false);
        content.add(textListaP);

        textListaC = new JTextArea();
        textListaC.setLineWrap(true);
            n1 = new JLabel("Nome Cibo"); n11 = new JTextField(10);
            n2 = new JLabel("Descrizione"); n22 = new JTextField(10);
            n3 = new JLabel("Costo"); n33 = new JTextField(10);
            n4 = new JLabel("Numero Copie"); n44 = new JTextField(10);
            add = new JButton("Aggiungi");
            textListaC.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
            textListaC.add(n1); textListaC.add(n11); textListaC.add(n2); textListaC.add(n22);
            textListaC.add(n4); textListaC.add(n44); textListaC.add(n3); textListaC.add(n33);
            textListaC.add(add);
                add.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String IDcibo = '0' + Integer.toString(sistema.mappaCibi.size() + 1);
                        sistema.InserisciCibo(IDcibo, n11.getText(), n22.getText(), Integer.parseInt(n44.getText()));
                        sistema.DefinisciCostoCibo(Integer.parseInt(n33.getText()));
                        sistema.FineInserimentoCibo();
                    }
                });
            textListaC.setVisible(false);
        content.add(textListaC);

        textList = new JTextArea();
        textList.setLineWrap(true);
        textList.setVisible(false);
        content.add(textList);

        GiocoButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton button1 = new JButton("Elenco giochi");
        JButton button2 = new JButton("Aggiungi gioco");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textList.setVisible(!textList.isVisible());
                textList.selectAll();
                textList.replaceSelection("");
                textList.append(sistema.getElencoGiochi().toString());
            }
        });
        GiocoButtons.add(button1);

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textLista.setVisible(!textLista.isVisible());
            }
        });
        GiocoButtons.add(button2);
    
        GiocoButtons.setVisible(false);
        content.add(GiocoButtons);

        PremioButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton button3 = new JButton("Elenco Premi");
        JButton button4 = new JButton("Aggiungi Premi");

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textList.setVisible(!textList.isVisible());
                textList.selectAll();
                textList.replaceSelection("");
                textList.append(sistema.getElencoPremi().toString());
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

        CiboButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton button5 = new JButton("Elenco Cibo");
        JButton button6 = new JButton("Aggiungi Cibo");

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textList.setVisible(!textList.isVisible());
                textList.selectAll();
                textList.replaceSelection("");
                textList.append(sistema.getElencoCibi().toString());
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

        frame.add(content, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setVisible(true);

    }
}