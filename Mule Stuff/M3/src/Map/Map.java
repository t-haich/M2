package Map;

public class Map {

    Tile[][] map;
    private final double WIDE = 67;
    private final double TALL = 80;

    public Map() {
        this.map = new Tile[5][9];
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 9; col++) {
                if ((col == 2 && row == 0 ) || (col == 8 && row == 2) || (col == 1 && row == 1)) {
                    map[row][col] = new Tile(Terrain.MOUNTAIN1, row * WIDE + 6, col * TALL);
                } else if ((row == 3 && (col ==  1 || col == 6)) || (row == 5 && (col == 2 || col == 8))) {
                    map[row][col] = new Tile(Terrain.MOUNTAIN2, row * WIDE + 6, col * TALL);
                } else if ((row == 0 && col == 6) || (row == 1 && col == 8) || (row == 2 && col == 0)) {
                    map[row][col] = new Tile(Terrain.MOUNTAIN3, row * WIDE + 6, col * TALL);
                } else if (col == 4 && (row == 0 || row == 1 || row == 3 || row == 4)) {
                    map[row][col] = new Tile(Terrain.RIVER, row * WIDE + 6, col * TALL);
                } else if (row != 2 && row != 4) {
                    map[row][col] = new Tile(Terrain.PLAIN, row * WIDE + 6, col * TALL);
                }
            }
        }
    }

    public Tile getTile(double x, double y) {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 9; col++) {
                double xloc = map[row][col].getX();
                double yloc = map[row][col].getY();
                boolean yCheck = (y >= yloc) && (y < yloc + TALL);
                boolean xCheck = (x >= xloc) && (x < xloc + WIDE);
                if (yCheck && xCheck) {
                    return map[row][col];
                }
            }
        }
        return null;
    }

    /*public void setTileOwnership(double x, double y, Player p) {
        getTile(x, y).setOwner(p);
    }*/

}