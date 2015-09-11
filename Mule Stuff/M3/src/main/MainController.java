package main;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public void greeting(ActionEvent event) {
        System.out.println("Hello");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
