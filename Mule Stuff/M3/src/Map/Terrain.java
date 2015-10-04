package Map;

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

    Terrain(int energy, int food, int smithore, String label) {
        this.energy = energy;
        this.food = food;
        this.smithore = smithore;
        this.label = label;
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

    public String label() {
        return label;
    }
}
