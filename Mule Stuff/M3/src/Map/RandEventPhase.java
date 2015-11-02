package Map;
import Characters.Player;
import java.util.PriorityQueue;
import java.util.Random;

public class RandEventPhase {
    private Player[] players;
    private int round;
    private static Random rand = new Random();

    /**
     * Creates a random event in the game
     * @param players The players in the game who will be affected
     * @param round The current round
     */
    public RandEventPhase(Player[] players, int round) {
        this.players = players; // Whats stored directly?
        this.round = round;
        runRandomEvent();
    }

    /**
     * Runs the chance of a random event
     */
    public final void runRandomEvent() {
        PriorityQueue<Player> p = new PriorityQueue<>();
        for (int i = 0; i < players.length; i++) {
            p.add(players[0]);
        }
        p.poll();
        while (p.size() > 0) {
            Player nextPlayer = p.poll();
            int chance = rand.nextInt(101); // What is this?
            if (chance <= 80) {             // What is this?
                RandomEvent[] arr = RandomEvent.values();    //fix... lol
                int eventChoice = rand.nextInt(arr.length + 1);
                RandomEvent temp = arr[eventChoice];
                runEvent(temp, nextPlayer);
            } else {
                System.out.println("No Random Event Occured");
            }
        }
    }

    /**
     * Runs the random event
     * @param e The random event to occur
     * @param p The player affected
     */
    private void runEvent(RandomEvent e, Player p) {
        System.out.println(p + ": " + e.label());
        System.out.println("Resources Before: ");
        System.out.println("Energy: " + p.getEnergy() + ",\t Money: " + p.getMoney() + ",\t Food: " + p.getFood() + ",\t Smithore: " + p.getSmithore());
        int arith = e.arith();
        int m = e.m();
        if (arith == 1) {
            if (m == 1) {
                p.addEnergy((int) e.energy() * round);
                p.addMoney(e.money() * round);
                p.addFood(e.smithore() * round);
                p.addSmithore((int)(e.food() * round));
            } else {
                p.addEnergy((int) (p.getEnergy() * e.energy()));
                p.addMoney(p.getMoney() * e.money());
                p.addFood((int)(p.getFood() * e.food()));
                p.addSmithore(p.getSmithore() * e.smithore());
            }
        } else {
            p.addEnergy((int) e.energy());
            p.addMoney(e.money());
            p.addFood((int)(e.food()));
            p.addSmithore(e.smithore());
        }
        System.out.println("Resources After: ");
        System.out.println("Energy: " + p.getEnergy() + ",\t Money: " + p.getMoney() + ",\t Food: " + p.getFood() + ",\t Smithore: " + p.getSmithore());

    }
}