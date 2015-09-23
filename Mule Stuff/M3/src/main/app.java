package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
<<<<<<< HEAD
import javafx.fxml.Initializable;
import javafx.scene.Parent;
=======
>>>>>>> origin/master
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class app extends Application {
    static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));

        Pane myPane = myLoader.load();

        MainController controller = myLoader.getController();

<<<<<<< HEAD
=======
        controller.setPrevStage(primaryStage);

        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);
        primaryStage.show();

>>>>>>> origin/master
    }

}
