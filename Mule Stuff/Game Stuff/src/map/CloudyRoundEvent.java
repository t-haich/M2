package map;

import characters.Player;

/**
 * Created by tomas on 12/1/2015.
 */
public class CloudyRoundEvent extends RoundRandomEvent {

    public CloudyRoundEvent() {
        super("THE DAY WAS COMPLETELY OVERCAST SO NO ENERGY "
                + "WAS PRODUCED, 3 ENERGY UNITS LOST");
    }

    @Override
    void process(Player p) {
        p.addFood(2);
    }
}
