package main;

import characters.Mule;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import map.Tile;
import javafx.scene.canvas.GraphicsContext;

public class TownDisplayController implements Initializable {
    @FXML
    private Canvas canvas = new Canvas();
    @FXML
    private Canvas canvasFront = new Canvas();
    public GraphicsContext g2d;
    public int storeSmithore = 10;
    public int storeEnergy = 0;
    public int storeFood = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Displays map screen
     * @throws IOException just in case there is no input/it failed
     */
    public void toMapScreen() throws IOException {
        Pane myPane;
        myPane = FXMLLoader.load(getClass().getResource("/fxml/Map.fxml"));
        Scene scene = new Scene(myPane);
        App.primaryStage.setScene(PlayerConfigController.mapScene);
        g2d = canvas.getGraphicsContext2D();
        for (int i = 0; i < MapController.tiles.size(); i++) {
            Tile tile = MapController.tiles.get(i);
            System.out.println(tile.getX());
            g2d.fillRect(tile.getX(), tile.getY(), 67, 80);
        }
    }

    /**
     * Displays town screen
     * @throws IOException just in case there is no input/it failed
     */
    public void toTownScreen() throws IOException {
        Pane myPane;
        myPane = FXMLLoader.load(getClass().getResource("/fxml/Town.fxml"));
        Scene scene = new Scene(myPane);
        App.primaryStage.setScene(scene);
    }

    /**
     * Displays shop screen
     * @throws IOException just in case there is no input/it failed
     */
    public void toShopScreen() throws IOException {
        Pane myPane;
        myPane = FXMLLoader.load(getClass().getResource("/fxml/Shop.fxml"));
        Scene scene = new Scene(myPane);
        App.primaryStage.setScene(scene);
    }

    /**
     * What happens when player enters the pub
     */
    public void toPub() {
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
        MapController.which();
    }

    /**
     * What happens when player buys a mule
     */
    public void buyMule() {
        if (!(MapController.currPlayer.hasMule()) && MapController.currPlayer.getMoney() >= 100) {
            MapController.currPlayer.setMule(Mule.EMPTY);
            MapController.currPlayer.addMoney(-100);
        }
    }

    /**
     * Customize current mule to be a miner
     */
    public void outfitMuleMining() {
        if (MapController.currPlayer.hasMule() && MapController.currPlayer.getMoney() >= 75
                && !(MapController.currPlayer.getMule().outfit().equals("Mining"))) {
            MapController.currPlayer.setMule(Mule.MINER);
            MapController.currPlayer.addMoney(-75);
        }
    }

    /**
     * Customize current mule to work with energy
     */
    public void outfitMuleEnergy() {
        if (MapController.currPlayer.hasMule() && MapController.currPlayer.getMoney() >= 50
                && !(MapController.currPlayer.getMule().outfit().equals("Energy"))) {
            MapController.currPlayer.setMule(Mule.ENERGY);
            MapController.currPlayer.addMoney(-50);
        }
    }

    /**
     * Customize current mule to be a farmer
     */
    public void outfitMuleFood() {
        if (MapController.currPlayer.hasMule() && MapController.currPlayer.getMoney() >= 25
                && !(MapController.currPlayer.getMule().outfit().equals("Farmer"))) {
            MapController.currPlayer.setMule(Mule.FARMER);
            MapController.currPlayer.addMoney(-25);
        }
    }

    /**
     * What happens when player buys food
     */
    public void buyFood() {
        if (MapController.currPlayer.getMoney() >= 30 && storeFood > 0) {
            MapController.currPlayer.addMoney(-30);
            MapController.currPlayer.addFood(1);
            storeFood--;
        }
    }

    /**
     * What happens when player buys smithore
     */
    public void buySmithore() {
        if (MapController.currPlayer.getMoney() >= 50 && storeSmithore > 0) {
            MapController.currPlayer.addMoney(-50);
            MapController.currPlayer.addSmithore(1);
            storeSmithore--;
        }
    }

    /**
     * What happens when player buys energy
     */
    public void buyEnergy() {
        if (MapController.currPlayer.getMoney() >= 25 && storeEnergy > 0) {
            MapController.currPlayer.addMoney(-25);
            MapController.currPlayer.addEnergy(1);
            storeEnergy--;
        }
    }

    /**
     * What happens when player sells food
     */
    public void sellFood() {
        if (MapController.currPlayer.getFood() > 0) {
            MapController.currPlayer.addFood(-1);
            MapController.currPlayer.addMoney(30);
            storeFood++;
        }
    }

    /**
     * What happens when player sells smithore
     */
    public void sellSmithore() {
        if (MapController.currPlayer.getSmithore() > 0) {
            MapController.currPlayer.addSmithore(-1);
            MapController.currPlayer.addMoney(50);
            storeSmithore++;
        }
    }

    /**
     * What happens when player sells energy
     */
    public void sellEnergy() {
        if (MapController.currPlayer.getEnergy() > 0) {
            MapController.currPlayer.addEnergy(-1);
            MapController.currPlayer.addMoney(25);
            storeEnergy++;
        }
    }

    public void save() {
        GameData data = new GameData();
        try {
            data.save();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
