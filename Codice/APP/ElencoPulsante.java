package main.APP;

import javax.swing.JTextArea;

public abstract class ElencoPulsante {

    public static JTextArea textPlace;

    protected static void cancellaTesto(JTextArea textPlace){
        textPlace.setVisible(!textPlace.isVisible());
        textPlace.selectAll();
        textPlace.replaceSelection("");
    }

    public abstract void execute(JTextArea textPlace);
    
}
