package Characters;
public enum Mule {
    FARMER("Farmer"),
    MINER("Miner"),
    ENERGY("Energy"),
    EMPTY("Empty");

    private String outfit;

    /**
     * Constructor method for mule
     * @param outfit
     */
    Mule(String outfit) {
        this.outfit = outfit;
    }

    /**
     * Gets the mule's outfit
     * @return Returns the mule's outfit
     */
    public String outfit() {
        return outfit;
    }

    /**
     * Sets the mule's outfit
     * @param outfit The desired outfit the player has for him/her mule
     */
    public void setOutfit(String outfit) {
        this.outfit = outfit;
    }
}
