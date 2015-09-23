

public class Map {

    Scene scene;
    public Map(Scene scene) {
        this.scene = scene;
        placeTile(RIVER, 0, 0);
        placeTile(RIVER, x, y);
        placeTile(RIVER, x, y);
        placeTile(RIVER, x, y);
        for(int i = 0; i < )
    }

    public placeTile(Terrain type, double xloc, double yloc) {
        Tile tile = new Tile(type, xloc, yloc);

    }
}