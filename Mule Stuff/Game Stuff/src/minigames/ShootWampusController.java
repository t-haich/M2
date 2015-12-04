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
 * Created by Kyle on 12/3/2015.
 */
public class ShootWampusController implements Initializable {

    @FXML
    private Canvas canvasFront;
    @FXML
    private Label timeText;
    @FXML
    private Label scoreText;
    private GraphicsContext g2dFront;
    public static int score2;
    private int xpos1, xpos2, xpos3, ypos1, ypos2, ypos3, speed1, speed2, speed3;
    public static int highScore2;
    private int time = 30, wampusWidth = 99, wampusHeight = 72;
    private static Timer timer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ypos1 = 147 - wampusHeight;
        ypos2 = 313 - wampusHeight;
        ypos3 = 470 - wampusHeight;
        xpos1 = 0;
        xpos2 = 600 - wampusWidth;
        xpos3 = 0;
        speed1 = 100;
        speed2 = 100;
        speed3 = -100;
        score2 = 0;
        time = 30;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (time == -1) {
                        timer.cancel();
                        timer.purge();
                        try {
                            endGame();
                            if (highScore2 < score2) {
                                highScore2 = score2;
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
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateWampuses();
            }
        }, 0, 100);
    }

    public void handleMouseClick(MouseEvent e) {
        double xloc = e.getX();
        double yloc = e.getY();
        if (xloc >= xpos1 && xloc <= xpos1 + wampusWidth) {
            if (yloc >= ypos1 && yloc <= ypos1 + wampusHeight) {
                scoreText.setText("Score: " + (++score2 * 132));
                wampusShot(1);
            }
        }
        if (xloc >= xpos2 && xloc <= xpos2 + wampusWidth) {
            if (yloc >= ypos2 && yloc <= ypos2 + wampusHeight) {
                scoreText.setText("Score: " + (++score2 * 132));
                wampusShot(2);
            }
        }
        if (xloc >= xpos3 && xloc <= xpos3 + wampusWidth) {
            if (yloc >= ypos3 && yloc <= ypos3 + wampusHeight) {
                scoreText.setText("Score: " + (++score2 * 132));
                wampusShot(3);
            }
        }
    }

    public void updateWampuses() {
        eraseWampuses();
        if (xpos1 + speed1 >= xpos1 + wampusWidth || xpos1 + speed1 < 0) {
            speed1 = -1 * speed1;
        }
        if (xpos2 + speed2 >= xpos2 + wampusWidth || xpos2 + speed2 < 0) {
            speed2 = -1 * speed2;
        }
        if (xpos3 + speed3 >= xpos3 + wampusWidth || xpos3 + speed3 < 0) {
            speed3 = -1 * speed3;
        }
        xpos1 += speed1;
        xpos2 += speed2;
        xpos3 += speed3;
        drawWampuses();
    }

    public void wampusShot(int i) {
        if (i == 1) {
            speed1 *= 2;
            speed1 = Math.abs(speed1);
            xpos1 = 0;
        } else if (i == 2) {
            speed2 *= 2;
            speed2 = Math.abs(speed2);
            xpos2 = 0;
        } else {
            speed3 *= 2;
            speed3 = Math.abs(speed3);
            xpos3 = 0;
        }
    }

    public void endGame() throws IOException {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/fxml/ShootWinView.fxml"));
        Pane myPane = myLoader.load();
        Scene myScene = new Scene(myPane);
        App.primaryStage.setScene(myScene);
    }

    private void drawWampuses() {
        g2dFront = canvasFront.getGraphicsContext2D();
        g2dFront.drawImage(new Image("/fxml/wampusMoving.png"), xpos1, ypos1);
        g2dFront.drawImage(new Image("/fxml/wampusMoving.png"), xpos2, ypos2);
        g2dFront.drawImage(new Image("/fxml/wampusMoving.png"), xpos3, ypos3);
    }

    private void eraseWampuses() {
        g2dFront = canvasFront.getGraphicsContext2D();
        g2dFront.clearRect(xpos1, ypos1, wampusWidth, wampusHeight);
        g2dFront.clearRect(xpos2, ypos2, wampusWidth, wampusHeight);
        g2dFront.clearRect(xpos3, ypos3, wampusWidth, wampusHeight);
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