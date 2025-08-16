package main.APP;

import javax.swing.JTextArea;

import main.IlPiattinodOro;

public class GiochiPulsante extends ElencoPulsante {

    @Override
    public void execute(JTextArea textPlace) {
        super.cancellaTesto(textPlace);
        textPlace.append(IlPiattinodOro.getInstance().getElencoGiochi().toString());
    }

}