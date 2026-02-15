package erika.utilities.gui;

import java.io.IOException;

import erika.chatbot.Erika;
import erika.exceptions.ErikaIoException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

// The solution below adapted from https://se-education.org/guides/tutorials/javaFx.html

/**
 * A class representing the GUI of the application.
 */
public class Gui extends Application {
    private Erika erika;

    @Override
    public void start(Stage stage) {
        try {
            erika = new Erika();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Anchor.fxml"));
            AnchorPane anchorPane = loader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.setTitle("Erika");
            stage.setMinHeight(417);
            stage.setMinWidth(810);
            loader.<Anchor>getController().insertErika(erika);
            stage.show();
        } catch (IOException | ErikaIoException e) {
            e.printStackTrace();
        }

    }
}

