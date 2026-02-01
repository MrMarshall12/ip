package erika.utilities.gui;

import erika.chatbot.Erika;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * A class representing the main window of the application.
 */
public class Anchor extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField inputField;

    @FXML
    private Button sendButton;

    private Erika erika;

    private String byeResponse = """
                Erika: Bye. Hope to see you again soon!
                """;;

    private Image userAvatar = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image erikaAvatar = new Image(this.getClass().getResourceAsStream("/images/erika.png"));

    /**
     * Implements auto scroll feature.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Inserts Erika into the main window of the application.
     */
    public void insertErika(Erika erika) {
        this.erika = erika;

        String greeting = this.erika.getGreeting();

        dialogContainer.getChildren().addAll(DialogBox.createBotDialogBox(greeting, erikaAvatar));
    }

    /**
     * Creates dialog box containing user's message and Erika's response and appends them to the dialog container.
     * Clears user input at the end of the process
     */
    @FXML
    private void handleUserInput() {
        String userMessage = inputField.getText();
        String response = erika.converse(userMessage);
        dialogContainer.getChildren().addAll(
                DialogBox.createUserDialogBox(userMessage, userAvatar),
                DialogBox.createBotDialogBox(response, erikaAvatar));

        inputField.clear();

        if (response.equals(byeResponse)) {
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> System.exit(0));
            pause.play();
        }
    }


}
