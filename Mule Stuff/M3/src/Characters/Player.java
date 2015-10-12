package Characters;

import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import main.app;

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

    public Mule getMule() {
        return mule;
    }

    public void setMule(Mule mule) {
        this.mule = mule;
        hasMule = true;
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
        float alpha = (float) 30 / 100;
        float base = 255;
        if (col.equals(Color.BLUE)) {
            pColor = new Color(0/base, 11/base, 229/base, alpha);
        } else if (col.equals(Color.RED)) {
            pColor = new Color(229/base, 0, 3/base, alpha);
        } else if (col.equals(Color.GREEN)) {
            pColor = new Color( 0, 229/base, 57/base, alpha);
        } else if (col.equals(Color.PURPLE)) {
            pColor = new Color(195/base, 0, 229/base, alpha);
        }
    }

    public void addMoney(int add) {
        money += add;
    }

    public void addFood(int add) {
        food += add;
    }

    public void addEnergy(int add) {
        energy += add;
    }

    public void addSmithore(int add) {
        smithore += add;
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

    public void removeMule() {
        hasMule = false;
        mule = null;
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

    //This is SOOOOOOOOO unoptimal, fix it later
    public void endTurnProduction() {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 9; col++) {
                if (!(row == 2 && col == 4)) {
                    if (app.map.getMap()[row][col].getOwner() != null
                            && this.equals(app.map.getMap()[row][col].getOwner())
                            && app.map.getMap()[row][col].getMule() != null
                            && app.map.getMap()[row][col].getMule().outfit().equals("Energy")) {
                        app.map.getMap()[row][col].getProduction(this);
                        System.out.println("Generated some energy");
                        System.out.println(this.energy);
                    }
                }
            }
        }
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 9; col++) {
                if (!(row == 2 && col == 4)) {
                    if (app.map.getMap()[row][col].getOwner() != null
                            && this.equals(app.map.getMap()[row][col].getOwner())
                            && app.map.getMap()[row][col].getMule() != null
                            && !(app.map.getMap()[row][col].getMule().outfit().equals("Energy"))) {
                        if (this.energy > 0) {
                            this.addEnergy(-1);
                            app.map.getMap()[row][col].getProduction(this);
                            if (app.map.getMap()[row][col].getMule().outfit().equals("Miner")) {
                                System.out.println("Mined some smithore");
                                System.out.println(this.smithore);
                            } else if (app.map.getMap()[row][col].getMule().outfit().equals("Farmer")) {
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
