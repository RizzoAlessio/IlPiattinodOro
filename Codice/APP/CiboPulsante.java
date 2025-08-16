package main.APP;

import javax.swing.JTextArea;

import main.IlPiattinodOro;

public class CiboPulsante extends ElencoPulsante {

    @Override
    public void execute(JTextArea textPlace) {
        cancellaTesto(textPlace);
        textPlace.append(IlPiattinodOro.getInstance().getElencoCibi().toString());
    }

}