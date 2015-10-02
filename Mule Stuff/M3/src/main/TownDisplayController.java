package main;

import Characters.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import Map.Map;
import Map.Tile;
import javafx.scene.canvas.GraphicsContext;

public class TownDisplayController implements Initializable {

    public Stage shopStage;

    //public void setPrevStage(Stage stage){
    //    this.prevStage = stage;
    //}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //this.map = new Map();
        MapController.townStage = new Stage();
        shopStage = new Stage();

    }

    public void toMapScreen() throws IOException {
        /*Pane myPane;
        myPane = FXMLLoader.load(getClass().getResource("/fxml/Map.fxml"));
        Scene scene = new Scene(myPane);
        app.primaryStage.setScene(scene);
        */
        MapController.townStage.close();
    }

    public void toTownScreen() throws IOException {
        /*Pane myPane;
        myPane = FXMLLoader.load(getClass().getResource("/fxml/Town.fxml"));
        Scene scene = new Scene(myPane);
        //stage = new Stage();
        MapController.townStage.setScene(scene);*/
        shopStage.close();
    }


    public void toShopScreen() throws IOException {
        Pane myPane;
        myPane = FXMLLoader.load(getClass().getResource("/fxml/Shop.fxml"));
        Scene scene = new Scene(myPane);
        //MapController.townStage.setScene(scene);
        //prevStage = new Stage();
        shopStage.setScene(scene);
        shopStage.show();
    }

    public void toPub() {       //The formula is: Money Bonus = Round Bonus + random between 0 and Time Bonus
        MapController.timer.cancel();
        MapController.timer.purge();
        long currTime = System.currentTimeMillis();
        long remaining = currTime - MapController.turnTime; //add to MapController
        int moneyBonus, timeBonus, roundBonus;
        if (MapController.turns < 4) {
            roundBonus = 50;
        } else if (MapController.turns < 8) {
            roundBonus = 100;
        } else if (MapController.turns < 11) {
            roundBonus = 150;
        } else {
            roundBonus = 200;
        }
        if (remaining > 37000) {
            timeBonus = 200;
        } else if (remaining > 25000) {
            timeBonus = 150;
        } else if (remaining > 12000) {
            timeBonus = 100;
        } else {
            timeBonus = 50;
        }
        Random rand = new Random();
        moneyBonus = roundBonus + (int) (rand.nextFloat() * timeBonus);
        if (moneyBonus > 250) {
            MapController.currPlayer.addMoney(250);
        } else {
            MapController.currPlayer.addMoney(moneyBonus);
        }
        MapController.turns(MapController.nextPlayer());
    }
}

