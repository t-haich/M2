package main;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayerConfigController implements Initializable {

    Stage prevStage;

    public void setPrevStage(Stage stage){
        this.prevStage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void toMapScreen() throws IOException {
        Pane myPane;
        myPane = FXMLLoader.load(getClass().getResource("/fxml/Map.fxml"));
        Scene scene = new Scene(myPane);
        app.primaryStage.setScene(scene);
    }


}
