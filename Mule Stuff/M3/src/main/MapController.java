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
    public static Player currPlayer;
    private Timer timer;
    private boolean tileClicked;
    public static int turns;
    public static long turnTime;
    public static Stage townStage = new Stage();
    public static int phase = 1;
    public static int pass = 0;
    public static int round = 0;
    public static Player[] arr = PlayerConfigController.player.toArray(Player[] a);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        while (phase1()) {
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                turnTime = System.currentTimeMillis();
                turns++;
                if (turns >= 7) {
                    timer.cancel();
                    timer.purge();
                } else {
                    tileClicked = false;
                    System.out.println("1");
                    turn(PlayerConfigController.player1);
                    System.out.println(currPlayer.getMoney());
                }
            }
        }, 0, 10000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                turnTime = System.currentTimeMillis();
                tileClicked = false;
                System.out.println("2");
                turn(PlayerConfigController.player2);
                System.out.println(currPlayer.getMoney());
            }
        }, 5000, 10000);
    }


    public void toTownScreen() throws IOException {
        Pane myPane;
        myPane = FXMLLoader.load(getClass().getResource("/fxml/Town.fxml"));
        Scene scene = new Scene(myPane);
        townStage.setScene(scene);
        townStage.show();
    }

    public void handleMouseClick(MouseEvent e) {
        if (phase == 1) {
            if (!tileClicked) {
                double xloc = e.getX();
                double yloc = e.getY();
                Tile tile = app.map.getTile(xloc, yloc);
                if (tile != null && !tile.isOwned()) {
                    tile.setOwner(currPlayer);
                    GraphicsContext g2d = canvas.getGraphicsContext2D();
                    g2d.setFill(currPlayer.getColor());
                    g2d.fillRect(tile.getX(), tile.getY(), 67, 80);
                    tileClicked = true;
                    pass = 0;
                    currPlayer = nextPlayer();

                }
                if (round > 2) {
                    currPlayer.addMoney(-300);
                }
            }
        }
    }

    public void endTurn() {
        if (phase1()) {
            tileClicked = true;
            pass++;
            if (pass == 4) {
                endPhase();
            }
            currPlayer = nextPlayer();
        }
    }

    public Player nextPlayer() {
        Player next = new Player();
        for (int i = 0; i < arr.length; i++) {
            if (currPlayer.equals(arr[i])) {
                next = arr[(i+1)%arr.length];
            }
        }
        return next;
    }
    public boolean phase1() {
        return phase == 1;
    }

    public boolean phase2() {
        return phase == 2;
    }

    public void endPhase() {
        if (phase == 1) {
            phase++;
        } else if (phase == 2) {
            phase = 1;
        }
    }

    public void turn(Player player) {
        currPlayer = player;
    }

}
