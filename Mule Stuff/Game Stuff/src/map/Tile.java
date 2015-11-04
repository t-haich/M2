package map;

import characters.Mule;
import characters.Player;
import main.App;

public class Tile {

    private Terrain type;
    private Mule mule;
    private boolean hasMule;
    private double x;
    private double y;
    private Player owner;
    private static final int TILE_OFFSET = 68;
    private static final double TILE_MULTI = 1.2;

    /**
     * The constructor for the tile.
     *
     * @param typeArg The terrain type
     * @param xArg    The x-coordinate of the tile
     * @param yArg    The y-coordinate of the tile
     */
    public Tile(Terrain typeArg, double xArg, double yArg) {
        this.type = typeArg;
        this.hasMule = false;
        this.x = xArg;
        this.y = yArg;
        this.owner = null;
    }

    /**
     * Gets the x-coordinate of the tile.
     *
     * @return the x-coordinate
     */
    public final double getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the tile.
     *
     * @return the y-coordinate
     */
    public final double getY() {
        return y;
    }

    /**
     * Checks if the tile is owned
     *
     * @return Whether or not the tile is owned
     */
    public final boolean isOwned() {
        return owner != null;
    }

    /**
     * Checks whether or not a mule is at the tile
     *
     * @return Whether or not a mule is at the tile via true or false
     */
    public final boolean hasMule() {
        return hasMule;
    }

    /**
     * The setter method that officially gives the tile a mule
     *
     * @param m The desired mule to be placed
     */
    public final void setMule(Mule m) {
        mule = m;
        hasMule = true;
    }

    /**
     * Removes the mule from a tile
     */
    public final void removeMule() {
        mule = null;
        hasMule = false;
    }

    /**
     * Sets the owner
     *
     * @param p The desired owner
     */
    public final void setOwner(Player p) {
        owner = p;
    }

    /**
     * The getter method to access who owns the tile
     *
     * @return The owner
     */
    public final Player getOwner() {
        return owner;
    }

    /**
     * The getter method that accesses the mule at the tile
     *
     * @return Returns the mule at the desired tile
     */
    public final Mule getMule() {
        return mule;
    }

    @Override
    public final String toString() {
        String out = "";
        out += "( " + x + ", " + y + ", " + type.label() + ")";
        return out;
    }

    /**
     * Gets the amount of neighboring tiles that are owned
     *
     * @return Returns the number of adjacent, owned tiles
     */
    public final int numAdjacentTiles() {
        int total = 0;
        Tile tempTile = App.map.getTile((this.x - 1), this.y);
        if (tempTile != null && tempTile.getOwner() != null && tempTile.getOwner().equals(this.getOwner())
                && tempTile.hasMule() && tempTile.getMule().outfit().equals(this.getMule().outfit())) {
            total++;
        }
        tempTile = App.map.getTile((this.x + TILE_OFFSET), this.y);
        if (tempTile != null && tempTile.getOwner() != null && tempTile.getOwner().equals(this.getOwner())
                && tempTile.hasMule() && tempTile.getMule().outfit().equals(this.getMule().outfit())) {
            total++;
        }
        tempTile = App.map.getTile(this.x, (this.y - 1));
        if (tempTile != null && tempTile.getOwner() != null && tempTile.getOwner().equals(this.getOwner())
                && tempTile.hasMule() && tempTile.getMule().outfit().equals(this.getMule().outfit())) {
            total++;
        }
        tempTile = App.map.getTile(this.x, (this.y + TILE_OFFSET));
        if (tempTile != null && tempTile.getOwner() != null && tempTile.getOwner().equals(this.getOwner())
                && tempTile.hasMule() && tempTile.getMule().outfit().equals(this.getMule().outfit())) {
            total++;
        }
        return total;
    }

    /**
     * Calculates the production of mule at the tile
     *
     * @param p The player whose production will be calculated
     */
    public final void getProduction(Player p) {
        double mult = 1;
        if (this.getMule().outfit().equals("Energy")) {
            for (int i = 0; i < this.numAdjacentTiles(); i++) {
                mult = mult * TILE_MULTI;
            }
            p.addEnergy((int) Math.ceil(this.type.energy() * mult));
        }
        if (this.getMule().outfit().equals("Miner")) {
            for (int i = 0; i < this.numAdjacentTiles(); i++) {
                mult = mult * TILE_MULTI;
            }
            p.addSmithore((int) Math.ceil(this.type.smithore() * mult));
        }
        if (this.getMule().outfit().equals("Farmer")) {
            for (int i = 0; i < this.numAdjacentTiles(); i++) {
                mult = mult * TILE_MULTI;
            }
            p.addFood((int) Math.ceil(this.type.food() * mult));
        }
    }

}