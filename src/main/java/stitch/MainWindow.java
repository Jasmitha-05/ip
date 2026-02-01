package stitch;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Stitch stitch;

    private Image liloImage = new Image(this.getClass().getResourceAsStream("/images/lilo.png"));
    private Image stitchImage = new Image(this.getClass().getResourceAsStream("/images/stitch.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());      
    }

    public void showGreeting() {
        String greeting = stitch.getUi().showGreet();
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(greeting, stitchImage));
    }

    /** Injects the Stitch instance */
    public void setStitch(Stitch s) {
        stitch = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Stitch's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = stitch.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, liloImage),
                DialogBox.getDukeDialog(response, stitchImage));
        userInput.clear();
    }
}
