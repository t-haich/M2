package minigames;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import main.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Kyle on 12/3/2015.
 */
public class ShootWampusWinController implements Initializable {

    @FXML
    private Label winnerText = new Label();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int score2 = ShootWampusController.score2;
        int highScore2 = ShootWampusController.highScore2;

        if (highScore2 < score2) {
            winnerText.setText("Score: " + score2
                    + "\nNew High Score!\nYou Shot " + score2 + " Wampus!");
        } else {
            winnerText.setText("Score: " + score2
                    + "\nCongratulations!\nYou Shot " + score2 + " Wampus!");
        }
    }



    public void exit() {
        (new ShootWampusController()).exit();
    }
}
