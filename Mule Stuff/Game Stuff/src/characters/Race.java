package characters;
public enum Race {
    BONZOID("Bonzoid", 1000),
    UGAITE("Ugaite", 1000),
    BUZZITE("Buzzite", 1000),
    HUMANOID("Humanoid",600),
    FLAPPER("Flapper",1600);

    private final String race;
    private final int money;

    /**
     * Sets desired race of player
     * @param raceArg The race desired
     * @param moneyArg The amount of money the race is entitled to
     */
    Race(String raceArg, int moneyArg) {
        this.race = raceArg;
        this.money = moneyArg;
    }

    /**
     * Gets the race
     * @return race
     */
    public String race() {
        return race;
    }

    /**
     * Gets the amount of money
     * @return money
     */
    public int money() {
        return money;
    }
}