package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.Stage;

public class MainController implements Initializable {

    public void greeting(ActionEvent event) {
        System.out.println("Hello");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
