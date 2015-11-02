package map;

public class Map {

    Tile[][] map;
    private final double WIDE = 67;
    private final double TALL = 80;

    /**
     * Map constructor
     */
    public Map() {
        this.map = new Tile[5][9];
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 9; col++) {
                if ((col == 2 && row == 0 ) || (col == 8 && row == 2) || (col == 1 && row == 1)) {
                    map[row][col] = new Tile(Terrain.MOUNTAIN1, col * WIDE, row * TALL);
                } else if ((row == 3 && (col ==  1 || col == 6)) || (row == 4 && (col == 2 || col == 8))) {
                    map[row][col] = new Tile(Terrain.MOUNTAIN2, col * WIDE, row * TALL);
                } else if ((row == 0 && col == 6) || (row == 1 && col == 8) || (row == 2 && col == 0)) {
                    map[row][col] = new Tile(Terrain.MOUNTAIN3, col * WIDE, row * TALL);
                } else if (col == 4 && (row == 0 || row == 1 || row == 3 || row == 4)) {
                    map[row][col] = new Tile(Terrain.RIVER, col * WIDE, row * TALL);
                } else if (!(row == 2 && col == 4)) {
                    map[row][col] = new Tile(Terrain.PLAIN, col * WIDE, row * TALL);
                }

            }

        }
    }

    /**
     * Gets the selected tile
     * @param x The x coordinate
     * @param y The y coordinate
     * @return The tile that matches the coordinates
     */
    public Tile getTile(double x, double y) {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 9; col++) {
                if (map[row][col] != null) {
                    double xloc = map[row][col].getX();
                    double yloc = map[row][col].getY();
                    boolean yCheck = (y >= yloc) && (y < yloc + TALL);
                    boolean xCheck = (x >= xloc) && (x < xloc + WIDE);
                    if (yCheck && xCheck) {
                        return map[row][col];
                    }
                }
            }
        }
        return null;
    }

    /**
     * Gets map
     * @return map
     */
    public Tile[][] getMap() {
        return map;
    }

    /*public void setTileOwnership(double x, double y, Player p) {
        getTile(x, y).setOwner(p);
    }*/

}