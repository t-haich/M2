package map;

import org.junit.Before;

import characters.*;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class RandomEventTest {

    private Player player;
    private int round;
    private RandEventPhase randomEvent;

	@Before
	public void setUp() {
    player = new Player();
    round = 3;
    player.setName("Tester");
    player.setColor(Color.BLUE);
    player.setRace(Race.HUMANOID);
    player.setMoney(100);
    player.setFood(100);
    player.setSmithore(100);
    player.setEnergy(100);
    player.setTiles(100);
    player.setHasMule(true);
    Player[] players = {player};
    randomEvent = new RandEventPhase(players, round);
	}

	@Test
	public void outfitMining() {
		
	}

	@Test
	public void runRandEventTwo() {
		randomEvent.runEvent2(RandomEvent.TWO, player); //+2 Smithore
		assertEquals(player.getFood(), 100);
		assertEquals(player.getEnergy(), 100);
		assertEquals(player.getMoney(), 100);
		assertEquals(player.getTiles(), 100);
		assertEquals(player.getSmithore(), 102);
		assertTrue(player.hasMule());
	}

	@Test
	public void runRandEventThree() {
		randomEvent.runEvent2(RandomEvent.THREE, player); //Money += 8 * round (which is 3)
		assertEquals(player.getFood(), 100);
		assertEquals(player.getEnergy(), 100);
		assertEquals(player.getMoney(), 124);
		assertEquals(player.getTiles(), 100);
		assertEquals(player.getSmithore(), 100);
		assertTrue(player.hasMule());
		round = 4;
		randomEvent.runEvent2(RandomEvent.THREE, player);
		assertEquals(player.getMoney(), 148);
	}

	@Test
	public void runRandEventFive() {
		randomEvent.runEvent2(RandomEvent.FIVE, player); //Money += (-4)* round (which is 3)
		assertEquals(player.getFood(), 100);
		assertEquals(player.getEnergy(), 100);
		assertEquals(player.getMoney(), 88);
		assertEquals(player.getTiles(), 100);
		assertEquals(player.getSmithore(), 100);
		assertTrue(player.hasMule());
		round = 4;
		randomEvent.runEvent2(RandomEvent.THREE, player);
		assertEquals(player.getMoney(), 112);
	}

	@Test
	public void runRandEventSix() {
		randomEvent.runEvent2(RandomEvent.SIX, player); //Food  = Food / 2;
		assertEquals(player.getFood(), 50);
		assertEquals(player.getEnergy(), 100);
		assertEquals(player.getMoney(), 100);
		assertEquals(player.getTiles(), 100);
		assertEquals(player.getSmithore(), 100);
		assertTrue(player.hasMule());
	}


}