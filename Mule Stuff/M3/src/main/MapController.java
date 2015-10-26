package main;

import Characters.*;
import com.sun.org.apache.xml.internal.security.Init;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
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
import javafx.scene.image.Image;

public class MapController implements Initializable {
    @FXML
    private Canvas canvas;
    @FXML
    private Canvas canvasFront;
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
    public static Stage playStage = new Stage();
    public static Player[] arr;
    public GraphicsContext g2d;
    public GraphicsContext g2dFront;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        arr = PlayerConfigController.players;
        currPlayer = arr[0];
    }


    public void toTownScreen() throws IOException {
        if (phase != 1) {
            Pane myPane;
            myPane = FXMLLoader.load(getClass().getResource("/fxml/Town.fxml"));
            Scene scene = new Scene(myPane);
            app.primaryStage.setScene(scene);
        }
    }

    public void handleMouseClick(MouseEvent e) {
        double xloc = e.getX();
        double yloc = e.getY();
        Tile tile = app.map.getTile(xloc, yloc);
        if (phase == 1) {
            if (tile != null && !tile.isOwned()) {
                if ((turns > 0 || round >= 4) && currPlayer.getMoney() >= 300) {
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
                        System.out.println("Your MULE ran away "
                                + "because there's already a MULE here!");
                    } else {
                        tile.setMule(currPlayer.getMule());
                        drawMule(currPlayer, tile);
                        currPlayer.removeMule();
                        System.out.println("You put your " + tile.getMule().outfit()
                                + " MULE on the tile.");

                    }
                } else {
                    if (tile.hasMule()) {
                        currPlayer.setMule(tile.getMule());
                        tile.removeMule();
                        System.out.println("You took the tile's "
                                + currPlayer.getMule().outfit() + " MULE.");
                        eraseMule(tile);
                    } else {
                        System.out.println("There are no MULEs!");
                    }
                }
            } else {
                if (currPlayer.hasMule()) {
                    currPlayer.removeMule();
                    System.out.println("Your mule ran away because"
                        + "this isn't your tile!");
                } else {
                    System.out.println("You don't own this tile!");
                }
            }
        }
    }

    private void eraseMule(Tile tile) {
        g2dFront = canvasFront.getGraphicsContext2D();
        g2dFront.clearRect(tile.getX(), tile.getY(), 67, 80);
    }

    private void drawMule(Player player, Tile tile) {
        g2dFront = canvasFront.getGraphicsContext2D();
        Mule tempMule = player.getMule();
        if (tempMule.outfit().equals("Farmer")) {
            g2dFront.drawImage(new Image("/fxml/food.png"),tile.getX() + 10, tile.getY() + 20);
        } else if (tempMule.outfit().equals("Miner")) {
            g2dFront.drawImage(new Image("/fxml/miner.png"),tile.getX() + 10, tile.getY() + 10);
        } else {
            g2dFront.drawImage(new Image("/fxml/energy.png"),tile.getX() + 10, tile.getY() + 10);
        }
    }

    public void endTurn() {
        if (phase1()) {
            tileClicked = true;
            pass++;
            //round++;
            if (pass == arr.length) {
                pass = 0;
                endPhase();
                try {
                    playerTurnDisplay();
                } catch (IOException e) {

                }
            }
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

    public static void turns() {
        int totalTime = PlayerConfigController.getAllTime(round);
        timer = new Timer();
        currPlayer = nextPlayer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                turnTime = System.currentTimeMillis();
                turns++;
                if (turns >= 20) {
                    phase = 1;
                    timer.cancel();
                    timer.purge();
                } else {
                    tileClicked = false;
                    currPlayer = arr[turns % 2];
                    System.out.println(currPlayer.toString());
                    System.out.println("CurrPlayer: " + currPlayer.getMoney());
                }
            }
        }, 0, 2*totalTime);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

            }
        }, totalTime, 2*totalTime);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    which();
                    timer.cancel();
                    timer.purge();
                });
            }
        }, totalTime, 5000);
    }

    public static void playerTurnDisplay() throws IOException {
        Pane myPane;
        MapController control = new MapController();
        myPane = FXMLLoader.load(control.getClass().getResource("/fxml/PlayerTurnView.fxml"));
        playStage = new Stage();
        playStage.setScene(new Scene(myPane));
        playStage.show();
    }
    public void playerOkay() {
        turns();
        playStage.close();
        phase = 2;
    }

    public static void landSelectDisplay() throws IOException {
        Pane myPane;
        MapController control = new MapController();
        myPane = FXMLLoader.load(control.getClass().getResource("/fxml/landSelection.fxml"));
        playStage = new Stage();
        playStage.setScene(new Scene(myPane));
        playStage.show();
    }
    public void landOkay() {
        playStage.close();
        phase = 1;
    }

    public static void which() {
        try {
            if (turns % 2 == 1)
                playerTurnDisplay();
            else
                landSelectDisplay();
        } catch (IOException e) {

        }
    }
}
