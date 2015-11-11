package main;
import characters.Mule;
import characters.Player;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import map.Tile;

import java.io.*;

/**
 * Created by Josh on 11/1/2015.
 */
public class GameData {

    public static Scene mapScene;


    public final void save() throws FileNotFoundException, UnsupportedEncodingException {
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
            Player player = MapController.arr[i];
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 9; col++) {
                    if (row != 2 || col != 4) {
                        if (player.equals(App.map.getMap()[row][col].getOwner())) {
                            writer.println(row);
                            writer.println(col);
                            if (App.map.getMap()[row][col].getMule() != null) {
                                writer.println(App.map.getMap()[row][col].getMule().outfit());
                            } else {
                                writer.println("0");
                            }
                        }
                    }
                }
            }
            writer.println(" ");
        }
        writer.close();
    }

    public final void load() {
        try {
            FileReader reader = new FileReader("gamedata.txt");
            BufferedReader bufferReader = new BufferedReader(reader);

            MapController.turns = Integer.parseInt(bufferReader.readLine());
            MapController.arr = new Player[Integer.parseInt(bufferReader.readLine())];
            String currPlayer = bufferReader.readLine();
            for (int i = 0; i < MapController.arr.length; i++) {
                MapController.arr[i] = new Player();
                MapController.arr[i].setName(bufferReader.readLine());
                if (currPlayer.equals(MapController.arr[i].toString())) {
                    MapController.currPlayer = MapController.arr[i];
                }
                MapController.arr[i].setColor(new Color( Float.parseFloat(bufferReader.readLine())
                        ,  Float.parseFloat(bufferReader.readLine())
                        ,  Float.parseFloat(bufferReader.readLine()), (float) 30/100));
                MapController.arr[i].setMoney(Integer.parseInt(bufferReader.readLine()));
                MapController.arr[i].setEnergy(Integer.parseInt(bufferReader.readLine()));
                MapController.arr[i].setFood(Integer.parseInt(bufferReader.readLine()));
                MapController.arr[i].setSmithore(Integer.parseInt(bufferReader.readLine()));
                MapController.arr[i].setTiles(Integer.parseInt(bufferReader.readLine()));
                String line = bufferReader.readLine();
                if (!line.equals("0")) {
                    if (line.equals("Farmer")) {
                        MapController.arr[i].setMule(Mule.FARMER);
                    } else if (line.equals("Miner")) {
                        MapController.arr[i].setMule(Mule.MINER);
                    } else if (line.equals("Energy")) {
                        MapController.arr[i].setMule(Mule.ENERGY);
                    } else {
                        MapController.arr[i].setMule(Mule.EMPTY);
                    }
                } else {
                    MapController.arr[i].setHasMule(false);
                }
                line = bufferReader.readLine();
                while (!line.equals(" ")) {
                    int row = Integer.parseInt(line);
                    int col = Integer.parseInt(bufferReader.readLine());
                    String mule = bufferReader.readLine();
                    Tile tile = App.map.getMap()[row][col];
                    tile.setOwner(MapController.arr[i]);
                    if (!mule.equals("0")) {
                        if (mule.equals("Farmer")) {
                            tile.setMule(Mule.FARMER);
                        } else if (mule.equals("Miner")) {
                            tile.setMule(Mule.MINER);
                        } else if (mule.equals("Energy")) {
                            tile.setMule(Mule.ENERGY);
                        } else {
                            tile.setMule(Mule.EMPTY);
                        }
                    } else {
                        tile.removeMule();
                    }
                    line = bufferReader.readLine();
                }
            }
            GameConfigDisplayController cont1 = new GameConfigDisplayController();
            PlayerConfigController cont2 = new PlayerConfigController();
            MapController map = new MapController();
            PlayerConfigController.players = MapController.arr;
            reader.close();
            cont2.toMapScreen();
        }
        catch(FileNotFoundException ex) {
            System.out.println("File not found!");
        }
        catch(IOException exe) {
            System.out.println("Quit deleting files dammit");
            exe.printStackTrace();
        }
    }

    private void toMapScreen() throws IOException {
        Pane myPane;
        myPane = FXMLLoader.load(getClass().getResource("/fxml/Map.fxml"));
        Scene scene = new Scene(myPane);
        App.primaryStage.setScene(scene);
        //mapScene = scene;
    }
}
