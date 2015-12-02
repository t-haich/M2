package map;

import characters.Player;

/**
 * Created by tomas on 12/1/2015.
 */
public abstract class RoundRandomEvent {

    protected String label;

    public RoundRandomEvent(String labelArg) {
        this.label = labelArg;
    }

    public void runEvent(Player[] players) {
        for (Player p : players) {
            System.out.println(label);
            System.out.println("Resources Before: ");
            System.out.println("Energy: " + p.getEnergy() + ",\t Money: "
                    + p.getMoney() + ",\t Food: " + p.getFood()
                    + ",\t Smithore: " + p.getSmithore());
            process(p);
            System.out.println("Resources After: ");
            System.out.println("Energy: " + p.getEnergy() + ",\t Money: "
                    + p.getMoney() + ",\t Food: " + p.getFood()
                    + ",\t Smithore: " + p.getSmithore());
        }
    }
    abstract void process(Player p);
}
