package Characters;
public enum Race {
    MECHTRON("Mechtron", 1,1,1,1),
    HUMANOID("Humanoid",2,2,2,2),
    FLUBBER("Flubber",3,3,3,3);

    private final String race;
    private final int smithore;
    private final int food;
    private final int energy;
    private final int money;


    Race(String race, int smithore, int food, int energy, int money) {
        this.race = race;
        this.smithore = smithore;
        this.food = food;
        this.energy = energy;
        this.money = money;
    }

    public String race() {
        return race;
    }

    public int smithore() {
        return smithore;
    }

    public int food() {
        return food;
    }

    public int energy() {
        return energy;
    }

    public int money() {
        return money;
    }
}