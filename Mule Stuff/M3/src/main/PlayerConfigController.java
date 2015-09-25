package main;

import Characters.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayerConfigController implements Initializable {

    public javafx.scene.control.TextField name;
    Stage prevStage;
    @FXML
    private ComboBox<String> color;
    @FXML
    private ComboBox<String> race;
    private Player player1;

    public void setPrevStage(Stage stage){
        this.prevStage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String col = color.getValue();
        String rac = race.getValue();
        player1 = new Player(name.getText(), getColor(col), getRace(rac));
    }

    public void toMapScreen() throws IOException {
        Pane myPane;
        myPane = FXMLLoader.load(getClass().getResource("/fxml/Map.fxml"));
        Scene scene = new Scene(myPane);
        app.primaryStage.setScene(scene);
        System.out.println(color.getValue());
        System.out.println(name.getText() + race.getValue());
    }

    public void toConfigScreen() throws IOException {
        Pane myPane;
        myPane = FXMLLoader.load(getClass().getResource("/fxml/GameConfigDisplay.fxml"));
        Scene scene = new Scene(myPane);
        app.primaryStage.setScene(scene);
    }

    private PColor getColor(String col) {
        PColor pColor;
        if (col.equals("Blue"))
            pColor = PColor.BLUE;
        if (col.equals("Red"))
            pColor = PColor.RED;
        if (col.equals("Green"))
            pColor = PColor.GREEN;
        else
            pColor = PColor.PURPLE;
        return pColor;
    }
    private Race getRace(String rac) {
        Race raceE;
        if (rac.equals("Bonzoid"))
            raceE = Race.BONZOID;
        if (rac.equals("Buzzite"))
            raceE = Race.BUZZITE;
        if (rac.equals("Ugaite"))
            raceE = Race.UGAITE;
        if (rac.equals("Humanoid"))
            raceE = Race.HUMANOID;
        else
            raceE = Race.FLAPPER;
        return raceE;
    }
}
