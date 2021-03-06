package map;

public class Map {

    private Tile[][] map;
    private static final double WIDE = 67;
    private static final double TALL = 80;

    /**
     * Map constructor
     */
    public Map() {
        final int rowLength = 9;
        final int columnLength = 5;
        this.map = new Tile[columnLength][rowLength];
        for (int row = 0; row < columnLength; row++) {
            for (int col = 0; col < rowLength; col++) {
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
        map[0][2] = new Tile(Terrain.VOLCANO, 2 * WIDE, 0 * TALL);
        map[3][6] = new Tile(Terrain.VOLCANO, 6 * WIDE, 3 * TALL);
    }

    /**
     * Gets the selected tile
     * @param x The x coordinate
     * @param y The y coordinate
     * @return The tile that matches the coordinates
     */
    public final Tile getTile(double x, double y) {
        final int rowLength = 9;
        final int columnLength = 5;
        for (int row = 0; row < columnLength; row++) {
            for (int col = 0; col < rowLength; col++) {
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
    public final Tile[][] getMap() {
        return map;
    }
}