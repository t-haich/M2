package map;

public enum Terrain {
    PLAIN(2, 3, 1, "Plain"),
    RIVER(4, 2, 0, "River"),
    MOUNTAIN1(1, 1, 2, "Mountain1"),
    MOUNTAIN2(1, 1, 3, "Mountain2"),
    MOUNTAIN3(1, 1, 4, "Mountain3");

    private int smithore;
    private int food;
    private int energy;
    private String label;

    /**
     * The terrain constructor
     * @param energyArg The energy
     * @param foodArg The food
     * @param smithoreArg The smithore
     * @param labelArg The label
     */
    Terrain(int energyArg, int foodArg, int smithoreArg, String labelArg) {
        this.energy = energyArg;
        this.food = foodArg;
        this.smithore = smithoreArg;
        this.label = labelArg;
    }

    /**
     * Access to the smithore of the terrain
     * @return The amount of smithore
     */
    public int smithore() {
        return smithore;
    }

    /**
     * Access to the food of the terrain
     * @return The amount of food
     */
    public int food() {
        return food;
    }

    /**
     * Access to the energy of the terrain
     * @return The amount of energy
     */
    public int energy() {
        return energy;
    }

    /**
     * Access to the label of the terrain
     * @return The amount of label
     */
    public String label() {
        return label;
    }
}
