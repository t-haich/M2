package Characters;

public class Player {
    private String name;
    private PColor color;
    private Race race;
    private int smithore;
    private int food;
    private int energy;
    private int money;
    private boolean turn;
    private boolean hasMule;
    private Mule mule;

    public Player (String name, PColor color, Race race) {
        this.name = name;
        this.color = color;
        this.race = race;
        this.food = 8;
        this.smithore = 0;
        this.energy = 4;
        this.money = race.money();
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