package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import map.Map;

public class App extends Application {
    public static Stage primaryStage;
    public static Map map = new Map();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public final void start(Stage primaryStage) throws Exception {
        //map = new Map();
        this.primaryStage = primaryStage;
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));

        Pane myPane = myLoader.load();

        MainController controller = myLoader.getController();

        controller.setPrevStage(primaryStage);

        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }
}
