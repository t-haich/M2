package main;

import Characters.Player;
import com.sun.org.apache.xml.internal.security.Init;
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
import java.util.Timer;
import java.util.TimerTask;
import Map.Map;
import Map.Tile;
import javafx.scene.canvas.GraphicsContext;

public class MapController implements Initializable {
    @FXML
    private Canvas canvas;
    //public static Map map = new Map();
    public Player currPlayer;
    private Timer timer;
    private boolean tileClicked;
    private int turns;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //while (turns < 6) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    tileClicked = false;
                    System.out.println("1");
                    turn(PlayerConfigController.player1);
                }
            }, 0, 10000);
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    tileClicked = false;
                    System.out.println("2");
                    turn(PlayerConfigController.player2);
                }
            }, 5000, 10000);
            //turns++;
        //}
    }


    public void toTownScreen() throws IOException {
        Pane myPane;
        myPane = FXMLLoader.load(getClass().getResource("/fxml/Town.fxml"));
        Scene scene = new Scene(myPane);
        app.primaryStage.setScene(scene);
    }

    public void handleMouseClick(MouseEvent e) {
        if (!tileClicked) {
            double xloc = e.getX();
            double yloc = e.getY();
            Tile tile = app.map.getTile(xloc, yloc);
            if (tile != null && !tile.isOwned()) {
                tile.setOwner(currPlayer);
                GraphicsContext g2d = canvas.getGraphicsContext2D();
                g2d.setFill(currPlayer.getColor());
                g2d.fillRect(tile.getX(), tile.getY(), 67, 80);
            }
            tileClicked = true;
        }
    }


    public void turn(Player player) {
        currPlayer = player;
    }

}
