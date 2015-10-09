package main;

import Characters.*;
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
import java.util.List;
import java.util.ArrayList;
import Map.Map;
import Map.Tile;
import javafx.scene.canvas.GraphicsContext;

public class MapController implements Initializable {
    @FXML
    private Canvas canvas;
    public static List<Tile> tiles = new ArrayList<Tile>();
    //public static Map map = new Map();
    public static Player currPlayer;
    public static Timer timer;
    private static boolean tileClicked;
    public static int turns;
    public static long turnTime;
    public static Stage townStage = new Stage();
    public static int phase = 1;
    public static int pass = 0;
    public static int round = 0;
    public static Player[] arr;
    public GraphicsContext g2d;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        arr = PlayerConfigController.players;
        currPlayer = arr[0];
        /*while (phase == 1) {//phase1()) {

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
        //timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                turnTime = System.currentTimeMillis();
                tileClicked = false;
                System.out.println("2");
                turn(PlayerConfigController.player2);
                System.out.println(currPlayer.getMoney());
            }
        }, 5000, 10000);*/
    }


    public void toTownScreen() throws IOException {
        if (phase != 1) {
            Pane myPane;
            myPane = FXMLLoader.load(getClass().getResource("/fxml/Town.fxml"));
            Scene scene = new Scene(myPane);
            app.primaryStage.setScene(scene);
            //townStage.show();
        }
    }

    public void handleMouseClick(MouseEvent e) {
        double xloc = e.getX();
        double yloc = e.getY();
        Tile tile = app.map.getTile(xloc, yloc);
        if (phase == 1) {
            if (tile != null && !tile.isOwned()) {
                if (round >= 4 && currPlayer.getMoney() >= 300) {
                        currPlayer.addMoney(-300);
                }
                tile.setOwner(currPlayer);
                g2d = canvas.getGraphicsContext2D();
                g2d.setFill(currPlayer.getColor());
                g2d.fillRect(tile.getX(), tile.getY(), 67, 80);
                tileClicked = true;
                pass = 0;
                currPlayer.addTile();
                tiles.add(tile);
                currPlayer = nextPlayer();
            }
        } else {
            if (currPlayer.equals(tile.getOwner())) {
                if (currPlayer.hasMule()) {
                    if (tile.hasMule()) {
                        currPlayer.removeMule();
                        System.out.println("Your MULE ran away because there's already a MULE here!");
                    } else {
                        tile.setMule(currPlayer.getMule());
                        currPlayer.removeMule();
                        System.out.println("You put your " + tile.getMule().outfit() + " MULE on the tile.");
                    }
                } else {
                    if (tile.hasMule()) {
                        currPlayer.setMule(tile.getMule());
                        tile.removeMule();
                        System.out.println("You took the tile's " + currPlayer.getMule().outfit() + " MULE.");
                    } else {
                        System.out.println("There are no MULEs!");
                    }
                }
            } else {
                if (currPlayer.hasMule()) {
                    currPlayer.removeMule();
                    System.out.println("Your mule ran away because this isn't your tile!");
                } else {
                    System.out.println("You don't own this tile!");
                }
            }
        }
    }

    /*public void repaint(Tile t) {
        g2d = canvas.getGraphicsContext2D();
        g2d.setFill(t.getOwner().getColor());
        g2d.fillRect(t.getX(), t.getY(), 67, 80);
    }*/

    public void endTurn() {
        if (phase1()) {
            tileClicked = true;
            pass++;
            //round++;
            if (pass == 4) {
                endPhase();
                turns(currPlayer);
            }
            currPlayer = nextPlayer();
        }
    }

    public static Player nextPlayer() {
        round++;
        for (int i = 0; i < arr.length; i++) {
            if (currPlayer.equals(arr[i])) {
                return arr[(i+1)%arr.length];
            }
        }
        return null;
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

    public static void turns(Player p) {
        int totalTime = PlayerConfigController.getAllTime(round);
        int timeOne = PlayerConfigController.getTurnTime(PlayerConfigController.getOrder(1), round);
        //currPlayer = p;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                turnTime = System.currentTimeMillis();
                turns++;
                if (turns >= 10) {
                    phase = 1;
                    timer.cancel();
                    timer.purge();
                } else {
                    tileClicked = false;
                    currPlayer = nextPlayer();
                    System.out.println(currPlayer.toString());
                    System.out.println("CurrPlayer: " + currPlayer.getMoney());
                }
            }
        }, 0, totalTime);

    }

    public Canvas getCanvas() {
        return this.canvas;
    }
}
