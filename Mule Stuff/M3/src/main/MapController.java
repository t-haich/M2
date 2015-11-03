package main;

import characters.Mule;
import characters.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.awt.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import map.RandEventPhase;
import map.Tile;

public class MapController implements Initializable {
    @FXML
    private Canvas canvas = new Canvas();
    @FXML
    private Canvas canvasFront;
    @FXML
    public static Label textOne;
    @FXML
    public static Label textTwo;
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

    /**
     * Sets the view of the player to the town screen.
     */
    public void toTownScreen() throws IOException {
        if (phase != 1) {
            Pane myPane;
            myPane = FXMLLoader.load(getClass().getResource("/fxml/Town.fxml"));
            Scene scene = new Scene(myPane);
            App.primaryStage.setScene(scene);
        }
    }

    /**
     * Determines what happens if you click on a tile of land.
     * @param e Where you clicked and what tile you chose.
     */
    public void handleMouseClick(MouseEvent e) {
        double xloc = e.getX();
        double yloc = e.getY();
        Tile tile = App.map.getTile(xloc, yloc);
        if (phase == 1) {
            if (currPlayer.getMoney() >= 300) {
                if (tile != null && !tile.isOwned()) {
                    if ((turns > 0 || round >= 4)) {
                        currPlayer.addMoney(-300);
                    }
                    tile.setOwner(currPlayer);
                    g2d = canvas.getGraphicsContext2D();
                    g2d.setFill(currPlayer.getColor());
                    g2d.fillRect(tile.getX()
                            , tile.getY(), 67, 80);
                    tileClicked = true;
                    pass = 0;
                    currPlayer.addTile();
                    tiles.add(tile);
                    currPlayer = nextPlayer();
                }
            }
        } else {
            if (currPlayer.equals(tile.getOwner())) {
                if (currPlayer.hasMule()) {
                    if (tile.hasMule()) {
                        currPlayer.removeMule();
                        System.out.println("Your MULE ran away "
                                + "because there's"
                                + "already a MULE here!");
                    } else {
                        tile.setMule(currPlayer.getMule());
                        drawMule(currPlayer, tile);
                        currPlayer.removeMule();
                        System.out.println("You put your "
                                + tile.getMule().outfit()
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

    /**
     * Sets player's color of tile.
     * @param p current player
     * @param t current tile
     */
    public void setColor(Player p, Tile t) {
        g2d = canvas.getGraphicsContext2D();
        g2d.setFill(p.getColor());
        g2d.fillRect(t.getX(), t.getY(), 67, 80);
        p.addTile();
        tiles.add(t);
    }

    /**
     * Removes a mule from a tile.
     * @param tile Which tile needs a mule removed
     */
    private void eraseMule(Tile tile) {
        g2dFront = canvasFront.getGraphicsContext2D();
        g2dFront.clearRect(tile.getX(), tile.getY(), 67, 80);
    }

    /**
     * Places mule (and his image) on a tile.
     * @param player The player who is setting his/her mule
     * @param tile The tile the muls is being placed
     */
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

    /**
     * Ends turn land phase turn.
     */
    public void endTurn() {
        if (phase1()) {
            tileClicked = true;
            pass++;
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

    /**
     * Sets up the next player for his/her turn.
     * @return the next player in the game
     */
    public static Player nextPlayer() {
        round++;
        for (int i = 0; i < arr.length; i++) {
            if (currPlayer.equals(arr[i])) {
                return arr[(i+1)%arr.length];
            }
        }
        return null;
    }

    /**
     * Checks if it is phase 1.
     * @return Whether or not it is phase 1
     */
    public boolean phase1() {
        return phase == 1;
    }

    /**
     * Checks if it is phase 2.
     * @return Whether or not it is phase 2
     */
    public boolean phase2() {
        return phase == 2;
    }

    /**
     * Ends phase.
     */
    public void endPhase() {
        if (phase == 1) {
            phase++;
        } else if (phase == 2) {
            phase = 1;
        }
    }

    /**
     * Sets up the time restraints for current player's turn.
     */
    public static void turns() {
        int totalTime =
                PlayerConfigController.getAllTime(turns / 2);
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
                    System.out.println("CurrPlayer: "
                            + currPlayer.getMoney());
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

    /**
     * Displays which player's turn it is.
     * @throws IOException just in case there is no input/it failed
     */
    public static void playerTurnDisplay() throws IOException {
        Pane myPane;
        MapController control = new MapController();
        myPane = FXMLLoader.load(control.getClass()
                .getResource("/fxml/PlayerTurnView.fxml"));
        playStage = new Stage();
        playStage.setScene(new Scene(myPane));
        playStage.show();
    }

    /**
     * Checks if a player has any turns left and closes the display.
     */
    public void playerOkay() {
        turns();
        playStage.close();
        phase = 2;
    }

    /**
     * Displays the land one selected.
     * @throws IOException just in case there is no input/it failed
     */
    public static void landSelectDisplay() throws IOException {
        Pane myPane;
        MapController control = new MapController();
        myPane = FXMLLoader.load(control.getClass()
                .getResource("/fxml/landSelection.fxml"));
        playStage = new Stage();
        playStage.setScene(new Scene(myPane));
        playStage.show();
    }

    /**
     * Closes land display.
     */
    public void landOkay() throws IOException {
        playStage.close();
        phase = 1;
        if (round > 2) {
            RandEventPhase randEvent = new RandEventPhase(arr, turns / 2);
        }
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
     * Determines which display should be shown.
     */
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
