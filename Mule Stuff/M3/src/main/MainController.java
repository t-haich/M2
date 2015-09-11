package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public void greeting(ActionEvent event) throws Exception {

        Parent root = new FXMLLoader().load(getClass().getResource("/fxml/GameConfigDisplay.fxml"));
        Scene scene = new Scene(root, 600, 400);
        Stage secondaryStage = new Stage();
        secondaryStage.setScene(scene);
        secondaryStage.show();
    }

        @Override
        public void initialize (URL location, ResourceBundle resources){

        }
}
