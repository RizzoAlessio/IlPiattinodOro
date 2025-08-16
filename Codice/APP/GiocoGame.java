package main.APP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

import main.IlPiattinodOro;

public class GiocoGame{

    public static JTextArea textListaG, textPlace;
    public int p = 0;

    private static void cancellaTesto(){
        textPlace.setVisible(!textPlace.isVisible());
        textPlace.selectAll();
        textPlace.replaceSelection("");
    }

    public void GameG(String gio, IlPiattinodOro sistema) {

        JFrame frame = new JFrame("Gioco");
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.GREEN);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        textListaG = new JTextArea();
        textPlace = new JTextArea();
        textListaG.setLineWrap(true);
            textListaG.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
               textPlace.append("- " + gio + "-");
               
            JButton gp = new JButton("Gioca (+10pt)");
            JButton go = new JButton("Game Over");
            JButton cnt = new JButton("Continua"); JButton fin = new JButton("Non Continua");
            textListaG.add(gp); textListaG.add(go);

            gp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    p +=10;
                }
            });

            go.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sistema.finePartita(p);
                    textPlace.append("GAME OVER \n totalizzati :" + p + " punti\n vuoi continuare?");
                    go.setVisible(false); gp.setVisible(false); 
                    textListaG.add(cnt); textListaG.add(fin);
                }
            });

            cnt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cancellaTesto();
                    p = 0;
                    go.setVisible(true); gp.setVisible(true); 
                    sistema.continua(true);
                    textListaG.remove(cnt); textListaG.remove(fin);
                }
            });

            fin.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cancellaTesto();
                    sistema.continua(false);
                    textListaG.remove(cnt); textListaG.remove(fin);
                    frame.dispose(); 
                }
            });

        content.add(textPlace);    
        content.add(textListaG);

//
        frame.add(content, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setVisible(true);

    }
}