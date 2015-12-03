package minigames;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import main.App;
import main.MainController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

/**
 * Created by tomas on 12/3/2015.
 */
public class CatchWampusController implements Initializable {

    private int score;
    public static Timer timer = new Timer();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        score = 0;

    }

    public void exit() {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
        try {
        Pane myPane = myLoader.load();

        Scene myScene = new Scene(myPane);
        App.primaryStage.setScene(myScene);
        } catch (IOException e) {

        }
    }

}
