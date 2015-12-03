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
 * Created by tomas on 12/3/2015.
 */
public class CatchWampusWinController implements Initializable {

    @FXML
    private Label winnerText = new Label();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int score = CatchWampusController.score;
        int highScore = CatchWampusController.highScore;

        if (highScore < score) {
            winnerText.setText("Score: " + score
                    + "\nNew High Score!\nYou caught " + score + " Wampus!");
        } else {
            winnerText.setText("Score: " + score
                    + "\nCongratulations!\nYou caught " + score + " Wampus!");
        }
    }


    public void playAgain() throws IOException {
        Pane myPane = FXMLLoader.load(getClass().getResource("/fxml/CatchWampusView.fxml"));
        Scene scene = new Scene(myPane);
        App.primaryStage.setScene(scene);
    }



    public void exit() {
        (new CatchWampusController()).exit();
    }
}
