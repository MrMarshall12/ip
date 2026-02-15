package erika.utilities.gui;

import javafx.application.Application;

// The solution below adapted from https://se-education.org/guides/tutorials/javaFx.html

/**
 * A launcher class to work around classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}

