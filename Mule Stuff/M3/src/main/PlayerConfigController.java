package main;

import Characters.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.awt.*;
import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayerConfigController implements Initializable {

    public javafx.scene.control.TextField name;
    public javafx.scene.control.TextField name2;
    Stage prevStage;
    @FXML
    private ComboBox<String> color;
    private Color currCol;
    @FXML
    private ComboBox<String> race;
    @FXML
    private ComboBox<String> color2;
    @FXML
    private ComboBox<String> race2;
    public static Player player1;
    public static Player player2;
    public PriorityQueue players;

    public void setPrevStage(Stage stage){
        this.prevStage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String col = color.getValue();
        String rac = race.getValue();
        color.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
        {
            public void changed(ObservableValue<? extends String> observable,String
                    oldValue,String newValue)
            {
                player1.setColor(getColor(newValue));
            }
        });
        String col2 = color2.getValue();
        String rac2 = race2.getValue();
        color2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
        {
            public void changed(ObservableValue<? extends String> observable,String
                    oldValue,String newValue)
            {
                player2.setColor(getColor(newValue));
            }
        });
        player1 = new Player(name.getText(), getColor(col), getRace(rac));
        player2 = new Player(name2.getText(), getColor(col2), getRace(rac2));
        players.add(player1);
        players.add(player2);
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

    private Color getColor(String col) {
        if (col.equals("Blue"))
            return Color.BLUE;
        else if (col.equals("Red"))
            return Color.RED;
        else if (col.equals("Green"))
            return Color.GREEN;
        else
            return Color.PURPLE;
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
