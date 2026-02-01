package erika.utilities.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A class representing a dialog box.
 */
public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Instantiates an instance of DialogBox.
     */
    public DialogBox(String message, Image avatar) {
        text = new Label(message);
        displayPicture = new ImageView(avatar);
        this.getChildren().addAll(text, displayPicture);
    }
}
