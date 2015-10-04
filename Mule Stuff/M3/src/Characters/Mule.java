package Characters;
public enum Mule {
    FARMER("Farmer"),
    MINER("Miner"),
    ENERGY("Energy"),
    EMPTY("Empty");

    private final String outfit;

    Mule(String outfit) {
        this.outfit = outfit;
    }

    public String outfit() {
        return outfit;
    }

    public void setOutfit(String outfit) {
        this.outfit = outfit;
    }
}
