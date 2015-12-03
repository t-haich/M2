package minigames;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import main.App;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tomas on 12/3/2015.
 */
public class CatchWampusController implements Initializable {

    @FXML
    private Canvas canvasFront;
    @FXML
    private Label timeText;
    @FXML
    private Label scoreText;
    private GraphicsContext g2dFront;
    public static int score;
    private int xpos, ypos;
    public static int highScore;
    private int time = 30, wampusWidth = 50, wampusHeight = 39;
    private static Timer timer;
    private Random rand = new Random();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        score = 0;
        time = 30;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (time == -1) {
                        timer.cancel();
                        timer.purge();
                        eraseWampus();
                        try {
                            endGame();
                            if (highScore < score) {
                                highScore = score;
                            }
                        } catch (IOException e) {
                            System.out.println("UhOh");
                        }
                    } else {
                        timeText.setText("Time: " + time);
                        time--;
                    }
                });
            }
        }, 0, 1000);
        drawWampus();
    }

    public void handleMouseClick(MouseEvent e) {
        double xloc = e.getX();
        double yloc = e.getY();
        if (xloc >= xpos && xloc <= xpos + wampusWidth) {
            if (yloc >= ypos && yloc <= ypos + wampusHeight) {
                scoreText.setText("Score: " + (++score * 132));
                eraseWampus();
                drawWampus();
            }
        }
    }

    public void endGame() throws IOException {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/fxml/CatchWinView.fxml"));
        Pane myPane = myLoader.load();
        Scene myScene = new Scene(myPane);
        App.primaryStage.setScene(myScene);
    }

    private void drawWampus() {
        g2dFront = canvasFront.getGraphicsContext2D();
        xpos = rand.nextInt(601 - wampusWidth);
        ypos = rand.nextInt(297 - wampusHeight);
        g2dFront.drawImage(new Image("/fxml/wampus.png"), xpos, ypos);
    }

    private void eraseWampus() {
        g2dFront = canvasFront.getGraphicsContext2D();
        g2dFront.clearRect(xpos, ypos, wampusWidth, 39);
    }

    public void exit() {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
        try {
        Pane myPane = myLoader.load();

        Scene myScene = new Scene(myPane);
        App.primaryStage.setScene(myScene);
        } catch (IOException e) {

        }
    }

}
