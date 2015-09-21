package Map;

public enum Terrain {
    PLAIN(2, 4, 1),
    RIVER(4, 2, 1),
    MOUNTAIN(1, 2, 4);

    private int smithore;
    private int food;
    private int energy;

    Terrain(int energy, int food, int smithore) {
        this.energy = energy;
        this.food = food;
        this.smithore = smithore;
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
}
