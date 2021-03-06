package map;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
/**
 * Created by tomas on 11/10/2015.
 */
public class MapGetTileTest {
    private Map map;
    private Tile[][] right;
    private static final double WIDE = 67;
    private static final double TALL = 80;

    @Before
    public void setUp() {
        map = new Map();
        final int rowLength = 9;
        final int columnLength = 5;
        this.right = new Tile[columnLength][rowLength];
        System.out.println(map.getMap()[1][1].getY());
        for (int row = 0; row < columnLength; row++) {
            for (int col = 0; col < rowLength; col++) {
                if ((col == 2 && row == 0) || (col == 8 && row == 2) || (col == 1 && row == 1)) {
                    right[row][col] = new Tile(Terrain.MOUNTAIN1, col * WIDE, row * TALL);
                } else if ((row == 3 && (col == 1 || col == 6)) || (row == 4 && (col == 2 || col == 8))) {
                    right[row][col] = new Tile(Terrain.MOUNTAIN2, col * WIDE, row * TALL);
                } else if ((row == 0 && col == 6) || (row == 1 && col == 8) || (row == 2 && col == 0)) {
                    right[row][col] = new Tile(Terrain.MOUNTAIN3, col * WIDE, row * TALL);
                } else if (col == 4 && (row == 0 || row == 1 || row == 3 || row == 4)) {
                    right[row][col] = new Tile(Terrain.RIVER, col * WIDE, row * TALL);
                } else if (!(row == 2 && col == 4)) {
                    right[row][col] = new Tile(Terrain.PLAIN, col * WIDE, row * TALL);
                }
            }
        }


    }

    @Test
    public void testGetTile() {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 9; col++) {
                //Check null for town
                if (row == 2 && col == 4) {
                    assertTrue(map.getTile(col * 67, row * 80) == right[row][col]);
                } else {
                    assertTrue(map.getTile(col * 67, row * 80).getX() == right[row][col].getX());
                    assertTrue(map.getTile(col * 67, row * 80).getY() == right[row][col].getY());
                }
            }
        }
    }
}
