package erika.utilities.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import erika.chatbot.Erika;

/**
 * A class representing the main window of the application.
 */
public class Anchor extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogBox;

    @FXML
    private TextField inputField;

    @FXML
    private Button sendButton;

    private Erika erika;

    private Image userAvatar = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image erikaAvatar = new Image(this.getClass().getResourceAsStream("/images/erika.png"));

    /**
     * Implements auto scroll feature.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogBox.heightProperty());
    }

    /**
     * Inserts Erika into the main window of the application.
     */
    public void insertErika(Erika erika) {
        this.erika = erika;
    }



}
