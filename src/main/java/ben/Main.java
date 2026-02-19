package ben;

import java.io.IOException;

import ben.javafxui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main class for launching JavaFX
 */
public class Main extends Application {
    private Ben ben = new Ben();


    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Ben");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBen(this.ben);
            if (this.ben.getStartupException() != null) {
                // Display startup exception if there is
                fxmlLoader.<MainWindow>getController().showBenExceptionMessage(this.ben.getStartupException());
            }
            fxmlLoader.<MainWindow>getController().showBenWelcomeMessage();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
