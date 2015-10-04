package Characters;

import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;

public class Player implements Comparable{
    private String name;
    private Color pColor;
    private Race race;
    private int smithore = 0;
    private int food = 8;
    private int energy = 4;
    private int money;
    private boolean turn;
    private boolean hasMule;
    private Mule mule;
    private int tiles;

    public Player (String name, Color col, Race race) {
        this.name = name;
        pColor = col;
        money = race.money();
        this.race = race;
        turn = false;
        hasMule = false;
        mule = null;
        tiles = 0;
    }

    public boolean hasMule() {
        return hasMule;
    }

    public void giveMule(Mule mule) {
        if (!hasMule()) {
            hasMule = true;
            this.mule = mule;
        }
    }

    public Mule getMule() {
        return mule;
    }

    public void setMule(Mule mule) {
        this.mule = mule;
    }

    public boolean getTurn() {
        return turn;
    }

    public void setTurn(boolean set) {
        turn = set;
    }

    public Color getColor() { return pColor; }

    public void setColor(Color col) {
        Color opColor = null;
        if (col.equals(Color.BLUE)) {
            pColor = new Color(0x800000FF);
        } else if (col.equals(Color.RED) {
            pColor = new Color(0x80FF0000);
        } else if (col == Color.GREEN) {
            pColor = new Color(0x80008000;
        } else if (col == COlor.PURPLE) {
            pColor = new Color(0x80800080);
        }
    }

    public void addMoney(int add) {
        money += add;
    }

    public void addFood(int add) {
        money += food;
    }

    public void addEnergy(int add) {
        money += add;
    }

    public void addSmithore(int add) {
        money += add;
    }

    public int getMoney() {
        return money;
    }

    public int getEnergy() {
        return energy;
    }

    public int getFood() {
        return food;
    }

    public int getSmithore() {
        return smithore;
    }

    public int getScore() {
        return (money + (smithore * 50) + (food * 30) + (energy * 25) + (500 * tiles));
    }

    public int getTurnTime() {
        return (food * 15);
    }

    public void addTile() {
        tiles++;
    }

    public int compareTo(Object t) {
        if (this.getScore() < ((Player) t).getScore()) {
            return -1;
        }
        if (this.getScore() > ((Player) t).getScore()) {
            return 1;
        }
        return 0;
    }

    public String toString() {
        return name;
    }
    public void setName(String n) {
        name = n;
    }
}
