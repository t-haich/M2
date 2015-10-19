package main;

import Characters.Player;
import Map.Map;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class app extends Application {
    static Stage primaryStage;
    public static Map map = new Map();

    private Canvas canvas;
    public static List<Map.Tile> tiles = new ArrayList<Map.Tile>();
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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));

        Pane myPane = myLoader.load();

        MainController controller = myLoader.getController();


        controller.setPrevStage(primaryStage);

        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }

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

    public static void turns(Player p) {
        int totalTime = PlayerConfigController.getAllTime(round);
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
                    currPlayer.endTurnProduction();
                    currPlayer = nextPlayer();
                    System.out.println(currPlayer.toString());
                    System.out.println("CurrPlayer: " + currPlayer.getMoney());
                }
            }
        }, 0, totalTime);
    }
}

}