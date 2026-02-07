package stitch;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Stitch using FXML.
 */
public class Main extends Application {

    private Stitch stitch = new Stitch();

    @Override
    public void start(Stage stage) {
        try {
            Scene scene = loadMainWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the main window from FXML and sets up the controller.
     * 
     * @return the Scene representing the main window
     * @throws IOException if loading FXML fails
     */
    private Scene loadMainWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        AnchorPane ap = fxmlLoader.load();
        Scene scene = new Scene(ap);

        MainWindow controller = fxmlLoader.getController();
        controller.setStitch(stitch);
        controller.showGreeting();

        return scene;
    }
}
