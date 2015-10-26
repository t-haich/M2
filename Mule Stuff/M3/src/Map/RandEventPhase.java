package Map;
import Characters.Player;

import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;

public class RandEventPhase {
    private Player[] players;
    private int round;
    private static Random rand = new Random();

    public RandEventPhase(Player[] players, int round) {
        this.players = players;
        this.round = round;
        runRandomEvent();
    }

    public void runRandomEvent() {
        PriorityQueue<Player> p = new PriorityQueue<>();
        for (int i = 0; i < players.length; i++) {
            p.add(players[0]);
        }
        p.poll();
        while (p.size() > 0) {
            Player nextPlayer = p.poll();
            int chance = rand.nextInt(101);
            if (chance <= 80) {
                RandomEvent[] arr = RandomEvent.values();    //fix... lol
                int eventChoice = rand.nextInt(arr.length + 1);
                RandomEvent temp = arr[eventChoice];
                runEvent(temp, nextPlayer);
            } else {
                System.out.println("No Random Event Occured");
            }
        }
    }

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