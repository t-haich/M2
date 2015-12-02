package map;

import characters.Player;

/**
 * Created by tomas on 12/1/2015.
 */
public class RainRoundEvent extends RoundRandomEvent {

    public RainRoundEvent() {
        super("THERE WAS A LOT OF RAIN INCREASING FOOD PRODUCTION,"
                + " EVERYONE GETS 2 FOOD UNITS");
    }

    @Override
    void process(Player p) {
        p.addFood(2);
    }
}
