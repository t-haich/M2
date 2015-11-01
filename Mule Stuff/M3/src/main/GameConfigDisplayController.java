package main;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameConfigDisplayController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Displays player screen
     * @throws IOException just in case there is no input/it failed
     */
    public final void toPlayerScreen() throws IOException {
        Pane myPane;
        myPane = FXMLLoader.load(getClass().getResource("/fxml/PlayerConfig.fxml"));
        Scene scene = new Scene(myPane);
        app.primaryStage.setScene(scene);
    }


}

