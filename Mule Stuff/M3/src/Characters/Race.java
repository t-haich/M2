package Characters;
public enum Race {
    MECHTRON("Mechtron", *****),
    HUMANOID("Humanoid",*****),
    FLUBBER("Flubber",*****);

    private final String race;
    private final int smithore;
    private final int food;
    private final int energy;
    private final int money;


    public Race(String race, int smithore, int food, int energy, int money) {
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