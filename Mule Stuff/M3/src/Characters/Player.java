package Characters;

import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;

public class Player {
    private String name;
    private Color pColor;
    private Race race;
    private int smithore = 0;
    private int food = 8;
    private int energy = 4;
    private int money = 0;//race.money();
    private boolean turn;
    private boolean hasMule;
    private Mule mule;

    public Player (String name, Color col, Race race) {
        this.name = name;
        pColor = col;

        this.race = race;
        turn = false;
        hasMule = false;
        mule = null;
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

    public boolean getTurn() {
        return turn;
    }

    public void setTurn(boolean set) {
        turn = set;
    }

    public Color getColor() { return pColor; }

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
}