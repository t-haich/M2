package test;

import characters.Mule;
import characters.Player;
import characters.Race;
import javafx.scene.paint.Color;
import map.Terrain;
import map.Tile;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;



/**
 * Created by Josh on 11/10/2015.
 */
public class ProductionTest {

    Tile tile;
    Player p;

    @Before
    public void setup() {
        tile = new Tile(Terrain.PLAIN, 0, 0);
        p = new Player("p", Color.RED, Race.BONZOID);
    }

    @Test
    public void testEnergyProduction() {
        p.setFood(1);
        p.setEnergy(1);
        p.setSmithore(1);
        tile.setMule(Mule.ENERGY);
        tile.getProduction(p, 0);
        assertTrue(p.getEnergy() == 3);
        assertTrue(p.getFood() == 1);
        assertTrue(p.getSmithore() == 1);
    }

    @Test
    public void testFoodProduction() {
        p.setFood(1);
        p.setEnergy(1);
        p.setSmithore(1);
        tile.setMule(Mule.FARMER);
        tile.getProduction(p, 0);
        assertTrue(p.getEnergy() == 1);
        assertTrue(p.getFood() == 4);
        assertTrue(p.getSmithore() == 1);
    }

    @Test
    public void testSmithoreProduction() {
        p.setFood(1);
        p.setEnergy(1);
        p.setSmithore(1);
        tile.setMule(Mule.MINER);
        tile.getProduction(p, 0);
        assertTrue(p.getEnergy() == 1);
        assertTrue(p.getFood() == 1);
        assertTrue(p.getSmithore() == 2);
    }

    @Test
    public void testNoProduction() {
        p.setFood(1);
        p.setEnergy(1);
        p.setSmithore(1);
        tile.setMule(Mule.EMPTY);
        tile.getProduction(p, 0);
        assertTrue(p.getEnergy() == 1);
        assertTrue(p.getFood() == 1);
        assertTrue(p.getSmithore() == 1);
    }

    @Test
    public void testProductionChangeWithAdjacentTiles() {
        p.setFood(1);
        p.setEnergy(1);
        p.setSmithore(1);
        tile.setMule(Mule.MINER);
        tile.getProduction(p, 4);
        assertTrue(p.getEnergy() == 1);
        assertTrue(p.getFood() == 1);
        assertTrue(p.getSmithore() == 4);
    }
}
