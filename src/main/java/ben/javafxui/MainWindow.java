package ben.javafxui;

import ben.Ben;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main window GUI
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

    private Ben ben;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image benImage = new Image(this.getClass().getResourceAsStream("/images/DaBen.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Ben instance */
    public void setBen(Ben b) {
        this.ben = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input
     * and the other containing Ben's reply
     * and then appends them to the dialog container.
     * Clears the user input after processing
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ben.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBenDialog(response, benImage)
        );
        userInput.clear();

        if (ben.getIsExit()) {
            Platform.exit();
        }
    }

    /**
     * Displays the welcome message for the Ben chatbot.
     */
    public void showBenWelcomeMessage() {
        String message = ben.getWelcomeMessage();
        dialogContainer.getChildren().add(
                DialogBox.getBenDialog(message, benImage)
        );
    }
}
