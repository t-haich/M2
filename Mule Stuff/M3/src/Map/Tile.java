package Map;
import Characters.*;
import main.app;

public class Tile {

    private Terrain type;
    private Mule mule;
    private boolean hasMule;
    private double x;
    private double y;
    private Player owner;

    public Tile(Terrain type, double x, double y) {
        this.type = type;
        this.hasMule = false;
        this.x = x;
        this.y = y;
        this.owner = null;
    }

    public double getX() {
         return x;
    }

    public double getY() {
         return y;
    }

    public boolean isOwned() {
        return owner != null;
    }

    public boolean hasMule() {
       return hasMule;
    }

    public void setMule(Mule m) {
        mule = m;
        hasMule = true;
    }

    public void removeMule() {
        mule = null;
        hasMule = false;
    }

    public void setOwner(Player p) {
        owner = p;
    }

    public Player getOwner() {
        return owner;
    }

    public Mule getMule() {
        return mule;
    }

    public String toString() {
        String out = "";
        out += "( " + x + ", " + y + ", " + type.label() + ")";
        return out;
    }

    public int numAdjacentTiles() {
        int total = 0;
        Tile tempTile = app.map.getTile((this.x - 1), this.y);
        if (tempTile != null && tempTile.getOwner() != null && tempTile.getOwner().equals(this.getOwner())
                && tempTile.hasMule() && tempTile.getMule().outfit().equals(this.getMule().outfit())) {
            total++;
        }
        tempTile = app.map.getTile((this.x + 68), this.y);
        if (tempTile != null && tempTile.getOwner() != null && tempTile.getOwner().equals(this.getOwner())
                && tempTile.hasMule() && tempTile.getMule().outfit().equals(this.getMule().outfit())) {
            total++;
        }
        tempTile = app.map.getTile(this.x, (this.y - 1));
        if (tempTile != null && tempTile.getOwner() != null && tempTile.getOwner().equals(this.getOwner())
                && tempTile.hasMule() && tempTile.getMule().outfit().equals(this.getMule().outfit())) {
            total++;
        }
        tempTile = app.map.getTile(this.x, (this.y + 68));
        if (tempTile != null && tempTile.getOwner() != null && tempTile.getOwner().equals(this.getOwner())
                && tempTile.hasMule() && tempTile.getMule().outfit().equals(this.getMule().outfit())) {
            total++;
        }
        return total;
    }

    public void getProduction(Player p) {
        double mult = 1;
        if (this.getMule().outfit().equals("Energy")) {
            for (int i = 0; i < this.numAdjacentTiles(); i++) {
                mult = mult * 1.2;
            }
            p.addEnergy((int) Math.ceil(this.type.energy() * mult));
        }
        if (this.getMule().outfit().equals("Miner")) {
            for (int i = 0; i < this.numAdjacentTiles(); i++) {
                mult = mult * 1.2;
            }
            p.addSmithore((int) Math.ceil(this.type.smithore() * mult));
        }
        if (this.getMule().outfit().equals("Farmer")) {
            for (int i = 0; i < this.numAdjacentTiles(); i++) {
                mult = mult * 1.2;
            }
            p.addFood((int) Math.ceil(this.type.food() * mult));
        }
    }

}