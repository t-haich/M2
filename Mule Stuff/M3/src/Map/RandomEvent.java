package Map;

public enum RandomEvent {

    ONE(0, 3, 2, 0, "YOU JUST RECEIVED A PACKAGE FROM THE GT ALUMNI CONTAINING 3 FOOD AND 2 ENERGY UNITS.", 0, 0),
    TWO(2, 0, 0, 0,"A WANDERING TECH STUDENT REPAID YOUR HOSPITALITY BY LEAVING TWO BARS OF ORE.", 0, 0),
    THREE(0, 0, 0, 8, "THE MUSEUM BOUGHT YOUR ANTIQUE PERSONAL COMPUTER FOR $ 8*m.", 1 , 1),
    FOUR(0, 0, 0, 2, "YOU FOUND A DEAD MOOSE RAT AND SOLD THE HIDE FOR $2*m.", 1 , 1),
    FIVE(0, 0, 0, -4, "FLYING CAT-BUGS ATE THE ROOF OFF YOUR HOUSE. REPAIRS COST $4*m.", 1, 1),
    SIX(0, -0.5, 0, 0, "MISCHIEVOUS UGA STUDENTS BROKE INTO YOUR STORAGE SHED AND STOLE HALF YOUR FOOD.", 1, 0),
    SEVEN(0, 0, 0, -6, "YOUR SPACE GYPSY INLAWS MADE A MESS OF THE TOWN. IT COST YOU $6*m TO CLEAN IT UP.", 1, 1);

    private int smithore;
    private double food;
    private int energy;
    private int money;
    private int arith;
    private int m;
    private String label;

    /**
     * Random event constructor
     * @param smithore current smithore
     * @param food current food
     * @param energy current energy
     * @param money current money
     * @param label current label
     * @param arith arith
     * @param m m
     */
    RandomEvent(int smithore, double food, int energy, int money, String label, int arith, int m) {
        this.energy = energy;
        this.food = food;
        this.smithore = smithore;
        this.label = label;
        this.arith = arith;
        this.money = money;
        this.m = m;
    }

    /**
     * Gets smithore
     * @return smithore
     */
    public int smithore() {
        return smithore;
    }

    /**
     * Gets food
     * @return food
     */
    public double food() {
        return food;
    }

    /**
     * Gets energy
     * @return energy
     */
    public double energy() {
        return energy;
    }

    /**
     * Get money
     * @return money
     */
    public int money() {
        return money;
    }

    /**
     * Gets label
     * @return label
     */
    public String label() {
        return label;
    }

    /**
     * Gets arith
     * @return arith
     */
    public int arith() {
        return arith;
    }

    /**
     * Gets m
     * @return m
     */
    public int m() {
        return m;
    }

}