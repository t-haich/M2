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

    /**
     * The constructor for the tile
     * @param type The terrain type
     * @param x The x-coordinate of the tile
     * @param y The y-coordinate of the tile
     */
    public Tile(Terrain type, double x, double y) {
        this.type = type;
        this.hasMule = false;
        this.x = x;
        this.y = y;
        this.owner = null;
    }

    /**
     * Gets the x-coordinate of the tile
     * @return the x-coordinate
     */
    public double getX() {
         return x;
    }

    /**
     * Gets the y-coordinate of the tile
     * @return the y-coordinate
     */
    public double getY() {
         return y;
    }

    /**
     * Checks if the tile is owned
     * @return Whether or not the tile is owned
     */
    public boolean isOwned() {
        return owner != null;
    }

    /**
     * Checks whether or not a mule is at the tile
     * @return Whether or not a mule is at the tile via true or false
     */
    public boolean hasMule() {
       return hasMule;
    }

    /**
     * The setter method that officially gives the tile a mule
     * @param m The desired mule to be placed
     */
    public void setMule(Mule m) {
        mule = m;
        hasMule = true;
    }

    /**
     * Removes the mule from a tile
     */
    public void removeMule() {
        mule = null;
        hasMule = false;
    }

    /**
     * Sets the owner
     * @param p The desired owner
     */
    public void setOwner(Player p) {
        owner = p;
    }

    /**
     * The getter method to access who owns the tile
     * @return The owner
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * The getter method that accesses the mule at the tile
     * @return Returns the mule at the desired tile
     */
    public Mule getMule() {
        return mule;
    }

    @Override
    public String toString() {
        String out = "";
        out += "( " + x + ", " + y + ", " + type.label() + ")";
        return out;
    }

    /**
     * Gets the amount of neighboring tiles that are owned
     * @return Returns the number of adjacent, owned tiles
     */
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

    /**
     * Calculates the production of mule at the tile
     * @param p The player whose production will be calculated
     */
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