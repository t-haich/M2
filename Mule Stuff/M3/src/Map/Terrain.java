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
     * @param energy The energy
     * @param food The food
     * @param smithore The smithore
     * @param label The label
     */
    Terrain(int energy, int food, int smithore, String label) {
        this.energy = energy;
        this.food = food;
        this.smithore = smithore;
        this.label = label;
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
