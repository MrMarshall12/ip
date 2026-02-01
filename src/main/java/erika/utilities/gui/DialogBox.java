package erika.utilities.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A class representing a dialog box.
 */
public class DialogBox extends HBox {
    @FXML
    private Label text;

    @FXML
    private ImageView displayPicture;

    /**
     * Instantiates an instance of DialogBox.
     */
    public DialogBox(String message, Image avatar) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        text.setText(message);
        displayPicture.setImage(avatar);
    }

    /**
     * Flips the dialog box horizontally to make the image appear on the left and the text on the right.
     */
    public void flip() {
        ObservableList<Node> temporary = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(temporary);
        getChildren().setAll(temporary);
        setAlignment(Pos.TOP_LEFT);
        text.getStyleClass().add("reply-label");
    }

    /**
     * Instantiates a dialog box with image on the right and text on the left.
     */
    public static DialogBox createUserDialogBox(String message, Image avatar) {
        return new DialogBox(message, avatar);
    }

    /**
     * Instantiates an dialog box with image on the left and text on the right.
     */
    public static DialogBox createBotDialogBox(String message, Image avatar) {
        DialogBox dialogBox = new DialogBox(message, avatar);
        dialogBox.flip();
        return dialogBox;
    }
}
