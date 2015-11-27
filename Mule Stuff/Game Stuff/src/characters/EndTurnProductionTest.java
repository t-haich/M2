package characters;

import javafx.scene.paint.Color;
import map.Terrain;
import map.Tile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by courtney on 11/11/2015.
 */
public class EndTurnProductionTest {

    Tile t;
    Player p;

    @Before
    public void setUp() throws Exception {
        t = new Tile(Terrain.PLAIN, 0, 0);
        p = new Player("p", Color.RED, Race.BONZOID);
        p.setFood(1);
        p.setEnergy(1);
        p.setSmithore(1);
    }

    @Test
    public void smithoreEndTurnProduction() throws Exception {
        p.setEnergy(1);
        t.setMule(Mule.MINER);
        t.getProduction(p, 0);
        assertEquals(p.getEnergy(), 1);
        assertEquals(p.getSmithore(), 2);
    }

    @Test
    public void energyEndTurnProduction() throws Exception {
        t.setMule(Mule.ENERGY);
        t.getProduction(p, 0);
        assertTrue(p.getEnergy() == 3);
    }

    @Test
    public void foodEndTurnProduction() throws Exception {
        p.setEnergy(1);
        t.setMule(Mule.FARMER);
        t.getProduction(p, 0);
        assertEquals(p.getEnergy(), 1);
        assertEquals(p.getFood(), 4);
    }
}