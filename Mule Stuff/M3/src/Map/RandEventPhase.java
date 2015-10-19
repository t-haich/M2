import java.util.Random;

public class RandEventPhase {
    private Player[] players;
    private int round;
    private static Random rand = new Random();

    public RandEventPhase(Player[] players, int round) {
        this.players = players;
        this.round = round;
    }

    public runRandomEvent() {
        PriorityQueue<Player> p;
        for (int i = 0; i < players.length; i++) {
            p.add(players[0]);
        }
        p.poll();
        Iterator it = p.iterator();
        while (p.hasNext()) {
            int chance = rand.nextInt(101);
            if (chance <= 27) {
                RandomEvent temp = RandomEvent.ONE();
                RandomEvent[] arr = temp.getEnumConstants();
                int eventChoice = rand.nextInt(arr.length + 1);
                temp = arr[eventChoice];
                runEvent(temp, p);
            }
        }
    }

    private void runEvent(RandomEvent e, Player p) {
        int arith = e.arith();
        int m = e.m();
        if (arith == 1) {
            if (m == 1) {
                p.addEnergy(e.energy() * round);
                p.addMoney(e.money() * round);
                p.addFood(e.smithore() * round);
                p.addSmithore(e.food() * round);
            } else {
                p.addEnergy(p.getEnergy() * e.energy());
                p.addMoney(p.getMoney() * e.money());
                p.addFood(p.getFood() * e.food());
                p.addSmithore(p.getSmithore() * e.smithore());
            }
        } else {
            p.addEnergy(e.energy());
            p.addMoney(e.money());
            p.addFood(e.food());
            p.addSmithore(e.smithore());
        }
    }
}