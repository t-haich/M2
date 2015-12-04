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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import main.App;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Kyle on 12/3/2015.
 *  * Didn't have much time to make this, so have a lot of repeated code that could be cut down
 * By having a better backing structure
 * Also could have made a wampus class with variables: x, y, speed, image, moving image, death
 */
public class ShootWampusController implements Initializable {

    @FXML
    private Canvas canvasFront;
    @FXML
    private Label timeText;
    @FXML
    private Label scoreText;
    @FXML
    public WebView web1, web2, web3;
    private GraphicsContext g2dFront;
    public static int score2;
    private int xpos1, xpos2, xpos3, ypos1, ypos2, ypos3, speed1, speed2, speed3, shot1, shot2, shot3, timer1, timer2, timer3;
    public static int highScore2;
    private int time = 30, wampusWidth = 99, wampusHeight = 72;
    private static Timer timer;
    private Random rand = new Random();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ypos1 = 116 - wampusHeight;
        ypos2 = 250 - wampusHeight;
        ypos3 = 376 - wampusHeight;
        xpos1 = 0;
        xpos2 = 0;
        xpos3 = 0;
        drawWampuses();
        speed1 = 1;
        speed2 = 1;
        speed3 = 1;
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

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    updateWampuses();
                    updateShot();
                });
            }
        }, 0, 50);
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
        if (xpos1 + wampusWidth + speed1 >= 600 || xpos1 + speed1 < 0) {
            speed1 = -1 * speed1;
        }
        if (xpos2 + speed2 + wampusWidth >= 600 || xpos2 + speed2 < 0) {
            speed2 = -1 * speed2;
        }
        if (xpos3 + speed3 + wampusWidth >= 600 || xpos3 + speed3 < 0) {
            speed3 = -1 * speed3;
        }
        xpos1 += speed1;
        xpos2 += speed2;
        xpos3 += speed3;
        drawWampuses();
    }

    public void updateShot() {
        int showTime = 20;
        if (shot1 > 0 && timer1 > showTime) {
            web1.setPrefHeight(0);
            web1.setPrefWidth(0);
            shot1 = 0;
        } else if (shot1 > 0){
            timer1++;
        } else {
            timer1 = 0;
        }
        if (shot2 > 0 && timer2 > showTime) {
            web2.setPrefHeight(0);
            web2.setPrefWidth(0);
            shot2 = 0;
        } else if (shot2 > 0){
            timer2++;
        } else {
            timer2 = 0;
        }
        if (shot3 > 0 && timer3 > showTime) {
            web3.setPrefHeight(0);
            web3.setPrefWidth(0);
            shot3 = 0;
        } else if (shot3 > 0){
            timer3++;
        } else {
            timer3 = 0;
        }
    }

    public void wampusShot(int i) {
        int randomPos = rand.nextInt(600 - wampusWidth);
        int randDirection = (int)Math.pow(-1, rand.nextInt(1));
        if (i == 1) {
            shot1++;
            timer1 = 0;
            g2dFront.clearRect(xpos1, ypos1, wampusWidth, wampusHeight);
            speed1 += 2;
            speed1 = Math.abs(speed1) * randDirection;
            web1.setLayoutX(xpos1);
            web1.setLayoutY(ypos1);
            xpos1 = randomPos;
            web1.setPrefHeight(wampusHeight);
            web1.setPrefWidth(wampusWidth);
            WebEngine engine = web1.getEngine();
            URL url = getClass().getResource("/fxml/wampusShotGif.gif");
            engine.load(url.toExternalForm());
        } else if (i == 2) {
            shot2++;
            timer2 = 0;
            g2dFront.clearRect(xpos2, ypos2, wampusWidth, wampusHeight);
            speed2 += 2;
            speed2 = Math.abs(speed2) * randDirection;
            web2.setLayoutX(xpos2);
            web2.setLayoutY(ypos2);
            xpos2 = randomPos;
            web2.setPrefHeight(wampusHeight);
            web2.setPrefWidth(wampusWidth);
            WebEngine engine = web2.getEngine();
            URL url = getClass().getResource("/fxml/wampusShotGif.gif");
            engine.load(url.toExternalForm());
        } else {
            shot3++;
            timer3 = 0;
            g2dFront.clearRect(xpos3, ypos3, wampusWidth, wampusHeight);
            speed3 += 2;
            speed3 = Math.abs(speed3) * randDirection;
            web3.setLayoutX(xpos3);
            web3.setLayoutY(ypos3);
            xpos3 = randomPos;
            web3.setPrefHeight(wampusHeight);
            web3.setPrefWidth(wampusWidth);
            WebEngine engine = web3.getEngine();
            URL url = getClass().getResource("/fxml/wampusShotGif.gif");
            engine.load(url.toExternalForm());
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
