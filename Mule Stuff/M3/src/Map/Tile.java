package Map;
import Characters.Mule;

public class Tile {
    //Player owner;
    Terrain type;
    Mule mule;
    boolean owned;
    boolean hasMule;
    Location x;
    Location y;
    int final tileHeight = ;
    int final tileWidth = ;
    Player owner;

    public Tile(Terrain type, Location x, Location y) {
        this.type = type;
        this.isOwned = false;
        this.hasMule = false;
        this.x = x;
        this.y = y;
        this.owner = null;
    }

    public boolean isOwned() {
        return owned;
    }

    public boolean hasMule() {
        return hasMule;
    }

    public void setOwner(Player p) {
        owner = p;
    }

    public Player getOwner() {
        return owner;
    }
}