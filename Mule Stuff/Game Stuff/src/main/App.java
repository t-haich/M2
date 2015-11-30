package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import map.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;


public class App extends Application {
    public static Stage primaryStage;
    public static Map map = new Map();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public final void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));

        Pane myPane = myLoader.load();

        MainController controller = myLoader.getController();

        controller.setPrevStage(primaryStage);

        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);
        primaryStage.show();
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "/src/main/09-the-moon.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}
