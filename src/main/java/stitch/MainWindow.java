package stitch;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Creates the controller for the main GUI.
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

    /** Shows a greeting message from Stitch */
    public void showGreeting() {
        String greeting = stitch.getUi().showGreet();
        dialogContainer.getChildren().add(
                DialogBox.getStitchDialog(greeting, stitchImage));
    }

    /** Injects the Stitch instance */
    public void setStitch(Stitch s) {
        stitch = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Stitch's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * exit application
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = stitch.getResponse(input);
        addDialogBox(input, response);
        userInput.clear();

        handleExit(input);

    }

    /**
     * Creates dialog container containing user and Stitch input/response
     * 
     * @param input    user's input text
     * @param response Stitch's response to the text
     */
    private void addDialogBox(String input, String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getLiloDialog(input, liloImage),
                DialogBox.getStitchDialog(response, stitchImage));
    }

    /**
     * Handles exiting of application when user input is "bye"
     * 
     * @param input command input by user
     */
    private void handleExit(String input) {
        if (input.trim().equalsIgnoreCase("bye")) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
            PauseTransition wait = new PauseTransition(Duration.seconds(1.5));
            wait.setOnFinished(event -> Platform.exit());
            wait.play();
        }
    }
}
