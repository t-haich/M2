package main;
import Characters.Mule;
import Characters.Player;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.io.*;

/**
 * Created by Josh on 11/1/2015.
 */
public class GameData {

    public static Scene mapScene;


    public void save() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("gamedata.txt", "UTF-8");
        writer.println(MapController.turns);
        writer.println(MapController.arr.length);
        writer.println(MapController.currPlayer.toString());
        for (int i = 0; i < MapController.arr.length; i++) {
            writer.println(MapController.arr[i].toString());
            writer.println(MapController.arr[i].getColor().getRed());
            writer.println(MapController.arr[i].getColor().getGreen());
            writer.println(MapController.arr[i].getColor().getBlue());
            writer.println(MapController.arr[i].getMoney());
            writer.println(MapController.arr[i].getEnergy());
            writer.println(MapController.arr[i].getFood());
            writer.println(MapController.arr[i].getSmithore());
            writer.println(MapController.arr[i].getTiles());
            if(MapController.arr[i].getMule() != null) {
                writer.println(MapController.arr[i].getMule().outfit());
            } else {
                writer.println("0");
            }
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 9; col++) {
                    if (app.map.getMap()[row][col].getOwner().equals(MapController.arr[i])) {
                        writer.println(row);
                        writer.println(col);
                        if (app.map.getMap()[row][col].getMule() != null) {
                            writer.println(app.map.getMap()[row][col].getMule().outfit());
                        } else {
                            writer.println("n");
                        }
                    }
                }
            }
            writer.println(" ");
        }
    }

    public void load() {
        try {
            FileReader reader = new FileReader("gamedata.txt");
            BufferedReader bufferReader = new BufferedReader(reader);
            toMapScreen();
            MapController.turns = Integer.parseInt(bufferReader.readLine());
            MapController.arr = new Player[Integer.parseInt(bufferReader.readLine())];
            String currPlayer = bufferReader.readLine();
            for (int i = 0; i < MapController.arr.length; i++) {
                MapController.arr[i] = new Player(null, null, null);
                MapController.arr[i].setName(bufferReader.readLine());
                MapController.arr[i].setColor(new Color((float) Integer.parseInt(bufferReader.readLine())/255
                        , (float) Integer.parseInt(bufferReader.readLine())/255
                        , (float) Integer.parseInt(bufferReader.readLine())/255, (float) 30/100));
                MapController.arr[i].setMoney(Integer.parseInt(bufferReader.readLine()));
                MapController.arr[i].setEnergy(Integer.parseInt(bufferReader.readLine()));
                MapController.arr[i].setFood(Integer.parseInt(bufferReader.readLine()));
                MapController.arr[i].setSmithore(Integer.parseInt(bufferReader.readLine()));
                MapController.arr[i].setTiles(Integer.parseInt(bufferReader.readLine()));
                String line = bufferReader.readLine();
                if (line != "0") {
                    if (line == "Farmer") {
                        MapController.arr[i].setMule(Mule.FARMER);
                    } else if (line == "Miner") {
                        MapController.arr[i].setMule(Mule.MINER);
                    } else if (line == "Energy") {
                        MapController.arr[i].setMule(Mule.ENERGY);
                    } else {
                        MapController.arr[i].setMule(Mule.EMPTY);
                    }
                } else {
                    MapController.arr[i].setHasMule(false);
                }
                line = bufferReader.readLine();
                while (line != " ") {
                    int row = Integer.parseInt(line);
                    int col = Integer.parseInt(bufferReader.readLine());

                }
            }

        }
        catch(FileNotFoundException ex) {
            System.out.println("File not found!");
        }
        catch(IOException exe) {
            System.out.println("Quit deleting files dammit");
        }
    }

    private void toMapScreen() throws IOException {
        Pane myPane;
        myPane = FXMLLoader.load(getClass().getResource("/fxml/Map.fxml"));
        Scene scene = new Scene(myPane);
        app.primaryStage.setScene(scene);
        mapScene = scene;
    }
}
