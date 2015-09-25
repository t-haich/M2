package main;

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
import java.util.ResourceBundle;
import Map.Map;
import Map.Tile;
import javafx.scene.canvas.GraphicsContext;

public class TownDisplayController implements Initializable {

    Stage prevStage;
    @FXML
    private Canvas canvas;
    private Map map;
    // private Player player;

    public void setPrevStage(Stage stage){
        this.prevStage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.map = new Map();

    }


    public void toTownScreen() throws IOException {
        Pane myPane;
        myPane = FXMLLoader.load(getClass().getResource("/fxml/Town.fxml"));
        Scene scene = new Scene(myPane);
        app.primaryStage.setScene(scene);
    }

    public void toMapScreen() throws IOException {
        Pane myPane;
        myPane = FXMLLoader.load(getClass().getResource("/fxml/Map.fxml"));
        Scene scene = new Scene(myPane);
        app.primaryStage.setScene(scene);
    }

    public void toShopScreen() throws IOException {
        Pane myPane;
        myPane = FXMLLoader.load(getClass().getResource("/fxml/Shop.fxml"));
        Scene scene = new Scene(myPane);
        app.primaryStage.setScene(scene);
    }

    public void handleMouseClick(MouseEvent e) {
        double xloc = e.getX();
        double yloc = e.getY();
        Tile tile = map.getTile(xloc, yloc);
        if (!tile.isOwned()) {
            // tile.setOwner(Player p);
            GraphicsContext g2d = canvas.getGraphicsContext2D();
            g2d.setFill(PlayerConfigController.player1.getColor());
            g2d.fillRect(tile.getX(), tile.getY(), 67, 80);
        }
    }


}

