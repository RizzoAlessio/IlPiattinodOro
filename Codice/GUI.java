package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

public class GUI{

    static IlPiattinodOro sistema;
    public static JTextArea textField, textLista, textList;
    public static JLabel n1, n2, n3, n4;
    public static JTextField n11, n22, n33, n44;
    public static JButton add;

    private static JMenuBar Menu(){
        JMenuBar menu = new JMenuBar();
        JMenu x = new JMenu("Menu");
        JMenu y = new JMenu("Sistema");
            JMenuItem mGioco = new JMenuItem("Area Gioco");
            mGioco.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("area");
                }
            });
            JMenuItem mCibo = new JMenuItem("Area Cibo");
            JMenuItem mPremio = new JMenuItem("Area Premio");
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

        textField = new JTextArea();
        textField.setLineWrap(true);
        content.add(textField);
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

        textList = new JTextArea();
        textList.setLineWrap(true);
        textList.setVisible(false);
        content.add(textList);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton button1 = new JButton("Elenco giochi");
        JButton button2 = new JButton("Aggiungi gioco");
        JButton button3 = new JButton("Elenco Premi");
        JButton button4 = new JButton("Aggiungi Cibo");
        JButton button5 = new JButton("Scrivi");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textList.setVisible(!textList.isVisible());
                textList.selectAll();
                textList.replaceSelection("");
                textList.append(sistema.getElencoGiochi().toString());
            }
        });
        buttons.add(button1);

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textLista.setVisible(!textLista.isVisible());
            }
        });
        buttons.add(button2);

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textList.selectAll();
                textList.replaceSelection("");
                textList.append(sistema.getElencoPremi().toString());
            }
        });
        buttons.add(button3);

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textList.selectAll();
                textList.replaceSelection("");
                textList.append(sistema.getElencoCibi().toString());
            }
        });
        buttons.add(button4);

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(textField.getText());
            }
        });
        buttons.add(button5);

        content.add(buttons);
        frame.add(content, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setVisible(true);

    }
}