package Characters;
public enum Race {
    BONZOID("Bonzoid", 1000),
    UGAITE("Ugaite", 1000),
    BUZZITE("Buzzite", 1000),
    HUMANOID("Humanoid",600),
    FLUBBER("Flapper",1600);

    private final String race;
    private final int money;


    Race(String race, int money) {
        this.race = race;
        this.money = money;
    }

    public String race() {
        return race;
    }

    public int money() {
        return money;
    }
}