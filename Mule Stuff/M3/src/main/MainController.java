package main;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.InputStream;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.Stage;

public class MainController implements Initializable {

    Stage prevStage;

    /**
     * Sets previous screen
     * @param stage screen prior to this one
     */
    public void setPrevStage(Stage stage){
        this.prevStage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Goes to Configuration screen
     * @throws IOException just in case there is no input/it failed
     */
    public void toConfigScreen() throws IOException {
        Pane myPane;
        myPane = FXMLLoader.load(getClass().getResource("/fxml/GameConfigDisplay.fxml"));
        Scene scene = new Scene(myPane);
        prevStage.setScene(scene);
    }
}
