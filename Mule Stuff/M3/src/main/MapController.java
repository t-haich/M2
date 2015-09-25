package main;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.Button;



public class MapController implements Initializable {

    Stage prevStage;
    Label errorMessage;

    public void setPrevStage(Stage stage){
        this.prevStage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorMessage.setText("");
    }
/*
    public void handleTileClick(ActionEvent e) {
        Button button = (Button) e.getSource()
        double xloc = button.getX();
        double yloc = button.getY();
        if (!map.getTile(x, y).isOwned()) {
            map.setTileOwnership(xloc, yloc, Player p);
            errorMessage.setText("This Tile has been clicked");
        }
        //Error message to player saying tile is owned by x player

*/
    }