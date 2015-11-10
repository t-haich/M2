package map;
package test;
import characters.*;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;


public class MuleTests {


	@Before
	public void setUp() {
	private Player player = new Player();
    private int round = 3;
    player.setName("Tester");
    player.setColor(Color.BLUE);
    player.setRace(Race.HUMANOID);
    player.setMoney(100);
    player.setFood(100);
    player.setSmithore(100);
    player.setEnergy(100);
    player.setTiles(100);
    player.setHasMule(true);
	}

	@Test(timeout = TIMEOUT)
	public void outfitMining() {
		
	}

	@Test(timeout = TIMEOUT)
	public void runRandEventTwo() {
		RandomEventPhase.runEvent(RandomEvent.TWO, player); //+2 Smithore
		assertEquals(player.getFood(), 100);
		assertEquals(player.getEnergy(), 100);
		assertEquals(player.getMoney(), 100);
		assertEquals(player.getTiles(), 100);
		assertEquals(player.getSmithore(), 102);
		assertTrue(player.hasMule());
	}

	@Test(timeout = TIMEOUT)
	public void runRandEventThree() {
		RandomEventPhase.runEvent(RandomEvent.THREE, player); //Money += 8 * round (which is 3)
		assertEquals(player.getFood(), 100);
		assertEquals(player.getEnergy(), 100);
		assertEquals(player.getMoney(), 116);
		assertEquals(player.getTiles(), 100);
		assertEquals(player.getSmithore(), 100);
		assertTrue(player.hasMule());
		round = 4;
		RandomEventPhase.runEvent(RandomEvent.THREE, player);
		assertEquals(player.getMoney(), 148);
	}

	@Test(timeout = TIMEOUT)
	public void runRandEventFive() {
		RandomEventPhase.runEvent(RandomEvent.FIVE, player); //Money += (-4)* round (which is 3)
		assertEquals(player.getFood(), 100);
		assertEquals(player.getEnergy(), 100);
		assertEquals(player.getMoney(), 88);
		assertEquals(player.getTiles(), 100);
		assertEquals(player.getSmithore(), 100);
		assertTrue(player.hasMule());
		round = 4;
		RandomEventPhase.runEvent(RandomEvent.THREE, player);
		assertEquals(player.getMoney(), 72);
	}

	@Test(timeout = TIMEOUT)
	public void runRandEventSix() {
		RandomEventPhase.runEvent(RandomEvent.SIX, player); //Food  = Food / 2;
		assertEquals(player.getFood(), 50);
		assertEquals(player.getEnergy(), 100);
		assertEquals(player.getMoney(), 100);
		assertEquals(player.getTiles(), 100);
		assertEquals(player.getSmithore(), 100);
		assertTrue(player.hasMule());
	}


}