package gamelogic;
import java.util.Random;
import Characters.*;

public class LandSelect {

    Player p1, p2, p3, p4;
    Random rand;
    Player[] order;

    public LandSelect(Scene scene, Player p1, Player p2, Player p3, Player p4) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        // this.order = new Player[4];
        // order[0] = p1;
        // order[1] = p2;
        // order[2] = p3;
        // order[3] = p4;
        // randomize();
    }

    // Shuffle Order of players
    // private void randomize() {
    //     for (int i = 3; i > 0; i--) {
    //         int swap = rand.nextInt(i + 1);
    //         Player temp = order[i];
    //         order[i] = order[swap];
    //         order[swap] = temp;
    //     }
    // }
}



