package main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameConfigDisplayController implements Initializable {

    @FXML
    private ComboBox<String> difficulty;
    public static String gameDifficulty = "Beginner";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        difficulty.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String
                    oldValue, String newValue) {
                gameDifficulty = newValue;
                System.out.println("."+gameDifficulty+".");
            }
        });
    }

    /**
     * Displays player screen.
     * @throws IOException just in case there is no input/it failed
     */
    public final void toPlayerScreen() throws IOException {
        Pane myPane;
        myPane = FXMLLoader.load(getClass()
                .getResource("/fxml/PlayerConfig.fxml"));
        Scene scene = new Scene(myPane);
        App.primaryStage.setScene(scene);
    }
}

