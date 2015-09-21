package Map;

public enum Terrain {
    PLAIN(1, 2, 3),
    RIVER(0, 4, 2),
    MOUNTAIN(3, 1, 1);

    private final int smithore;
    private final int food;
    private final int energy;

    Terrain (int smithore, int food, int energy) {
        this.smithore = smithore;
        this.food = food;
        this.energy = energy;
    }


}
