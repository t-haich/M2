/*public class Map {

    Tile[][] map;

    public Map() {
        double wide = 66.67;
        double tall = 80;
        this.map = new Tile[5][9];
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 9; col++) {
                if ((col == 2 && row == 0 ) || (col == 8 && row == 2) || (col == 1 && row == 1)) {
                    map[row][col] = new Tile(MOUNTAIN1, row * wide, col * tall);
                } else if ((row == 3 && (col ==  1 || col == 6)) || (row == 5 && (col == 2 || col == 8)) {
                    map[row][col] = new Tile(MOUNTAIN2, row * wide, col * tall);
                } else if ((row == 0 && col == 6) || (row == 1 && col == 8) || (row == 2 && col == 0) {
                    map[row][col] = new Tile(MOUNTAIN3, row * wide, col * tall);
                } else if (col == 4 && (row == 0 || row == 1 || row == 3 || row == 4) {
                    map[row][col] = new Tile(RIVER, row * wide, col * tall);
                } else if (row != 2 && row != 4) {
                    map[row][col] = new Tile(PLAIN, row * wide, col * tall);
                }
            }
        }
    }

    public Tile getTile(double x, double y) {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 9; col++) {
                if (map[row][col].getX() == x && map[row][col].getY() == y) {
                    return map[row][col];
                }
            }
        }
    }
}*/