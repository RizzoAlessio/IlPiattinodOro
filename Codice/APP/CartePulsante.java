package main.APP;

import javax.swing.JTextArea;

import main.IlPiattinodOro;

public class CartePulsante extends ElencoPulsante {

    @Override
    public void execute(JTextArea textPlace) {
        cancellaTesto(textPlace);
        textPlace.append(IlPiattinodOro.getInstance().getElencoCarte().toString());
    }

}