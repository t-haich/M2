package characters;

import javafx.scene.paint.Color;
import main.App;

public class Player implements Comparable{
    private String name;
    private Color pColor;
    private Race race;
    private int smithore = 0;
    //food and energy are magic numbers - change or ignore or prep for argument?
    private static int food = 8;
    private int energy = 4;
    private int money;
    private boolean hasMule;
    private Mule mule;
    private int tiles;

    /**
     * This constructor instantiates the player
     * @param nameArg The name of the player
     * @param colArg The color the player uses to claim his/her land
     * @param raceArg The race of the player
     */
    public Player(String nameArg, Color colArg, Race raceArg) {
        this.name = nameArg;
        pColor = colArg;
        money = (race == null)? 1000 : race.money();
        this.race = raceArg;
        hasMule = false;
        mule = null;
        tiles = 0;
    }

    /**
     * No parameter constructor of Player.
     */
    public Player() {

    }

    /**
     * Method checks if the player has a Mule
     * @return Whether or not the player owns a mule via true/false
     */
    public final boolean hasMule() {
        return hasMule;
    }

    /**
     * The getter method for whatever mule the player has at the time
     * @return Returns the mule that the player has
     */
    public final Mule getMule() {
        return mule;
    }

    /**
     * The setter method that officially gives the player a mule
     * @param mule The desired mule to be owned
     */
    public final void setMule(Mule muleArg) {
        this.mule = muleArg;
        hasMule = true;
    }

    /**
     * The getter method to access the player's color
     * @return Returns the player's chosen color
     */
    public final Color getColor() { return pColor; }

    /**
     * Sets the player's desired color from the four choices he/she was given
     * @param col The player's desired color
     */
    public final void setColor(Color col) {
        final int divAlpha = 100;
        final int numAlpha = 30;
        final float baseNum = 255;
        float alpha = (float) numAlpha / divAlpha;
        float base = baseNum;
        if (col.equals(Color.BLUE)) {
            pColor = new Color(0/base, 11/base, 229/base, alpha); //RGB values; create constants like in line 72?
        } else if (col.equals(Color.RED)) {
            pColor = new Color(229/base, 0, 3/base, alpha);
        } else if (col.equals(Color.GREEN)) {
            pColor = new Color( 0, 229/base, 57/base, alpha);
        } else if (col.equals(Color.PURPLE)) {
            pColor = new Color(195/base, 0, 229/base, alpha);
        }
    }

    /**
     * Adds the money the player earns throughout the game
     * @param add the amount of money the player earned
     */
    public final void addMoney(int add) {
        money += add;
    }

    /**
     * Adds the food the player earns throughout the game
     * @param add the amount of food the player earned
     */
    public final void addFood(int add) {
        food += add;
    }

    /**
     * Adds the energy the player earns throughout the game
     * @param add the amount of energy the player earned
     */
    public final void addEnergy(int add) {
        energy += add;
    }

    /**
     * Adds the smithore the player earns throughout the game
     * @param add the amount of smithore the player earned
     */
    public final void addSmithore(int add) {
        smithore += add;
    }

    /**
     * The getter method to access how much money the player has a given time
     * @return The amount of money the player has
     */
    public final int getMoney() {
        return money;
    }

    /**
     * The getter method to access how much energy the player has a given time
     * @return The amount of energy the player has
     */
    public final int getEnergy() {
        return energy;
    }

    /**
     * The getter method to access how much food the player has a given time
     * @return The amount of food the player has
     */
    public final int getFood() {
        return food;
    }

    /**
     * The getter method to access how much smithore the player has a given time
     * @return The amount of smithore the player has
     */
    public final int getSmithore() {
        return smithore;
    }

    /**
     * The getter method to access the score the player has a given time
     * @return The score the player has
     */
    public final int getScore() {
        final int foodMult = 30;
        final int smithoreMult = 50;
        final int energyMult = 25;
        final int tilesMult = 500;
        return (money + (smithore * smithoreMult) + (food * foodMult)
                + (energy * energyMult) + (tilesMult * tiles));
    }

    public final int getTiles() {
        return tiles;
    }

    public final Race getRace() {
        return race;
    } // Never used

    /**
     * The getter method to access how much time the player has a given turn
     * @return The amount of time the player has
     */
    public final int getTurnTime() {
        final int timeMult = 15;
        return (food * timeMult);
    }

    /**
     * Add the tile the player just claimed
     */
    public final void addTile() {
        tiles++;
    }

    /**
     * Removes the mule from a player's ownership
     */
    public final void removeMule() {
        hasMule = false;
        mule = null;
    }

    @Override
    public final int compareTo(Object t) {
        if (this.getScore() < ((Player) t).getScore()) {
            return -1;
        }
        if (this.getScore() > ((Player) t).getScore()) {
            return 1;
        }
        return 0;
    }

    @Override
    public final String toString() {
        return name;
    }

    /**
     * Sets the player's name
     * @param n The player's desired name
     */
    public final void setName(String n) {
        name = n;
    }

    public final void setMoney(int i) {
        money = i;
    }

    public final void setFood(int i) {
        food = i;
    }

    public final void setSmithore(int i) {
        smithore = i;
    }

    public final void setEnergy(int i) {
        energy = i;
    }

    public final void setTiles(int i) {
        tiles = i;
    }

    public final void setHasMule(boolean b) {
        hasMule = b;
    }

    /**
     * At the end of the turn, calculate production at the turn
     */
    //This is SOOOOOOOOO unoptimal, fix it later
    public final void endTurnProduction() {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 9; col++) {
                if (!(row == 2 && col == 4)) {
                    if (App.map.getMap()[row][col].getOwner() != null
                            && this.equals(App.map.getMap()[row][col].getOwner())
                            && App.map.getMap()[row][col].getMule() != null
                            && App.map.getMap()[row][col].getMule().outfit().equals("Energy")) {
                        App.map.getMap()[row][col].getProduction(this);
                        System.out.println("Generated some energy");
                        System.out.println(this.energy);
                    }
                }
            }
        }
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 9; col++) {
                if (!(row == 2 && col == 4)) {
                    if (App.map.getMap()[row][col].getOwner() != null
                            && this.equals(App.map.getMap()[row][col].getOwner())
                            && App.map.getMap()[row][col].getMule() != null
                            && !(App.map.getMap()[row][col].getMule().outfit().equals("Energy"))) {
                        if (this.energy > 0) {
                            this.addEnergy(-1);
                            App.map.getMap()[row][col].getProduction(this);
                            if (App.map.getMap()[row][col].getMule().outfit().equals("Miner")) {
                                System.out.println("Mined some smithore");
                                System.out.println(this.smithore);
                            } else if (App.map.getMap()[row][col].getMule().outfit().equals("Farmer")) {
                                System.out.println("Made some food");
                                System.out.println(this.food);
                            }
                        } else {
                            System.out.println("Out of energy!");
                        }
                    }
                }
            }
        }
    }
}
