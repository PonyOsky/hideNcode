public class Number {

    private int value;
    private boolean known;

    /**
     * @brief Nubmer constructor
     * 
     * @param val    int value
     * @param hidden bool if is number guessed
     */
    public Number(int val, boolean hidden) {
        value = val;
        known = hidden;
    }

    /**
     * @brief getValue getter
     * 
     * @return int value
     */
    public int getValue() {
        return value;
    }

    /**
     * @brief setter for bool
     * 
     * @param hidden changing status
     */
    public void setKnown(boolean hidden) {
        known = hidden;
    }

    /**
     * @brief bool getter
     * 
     * @return konwn bool value
     */
    public boolean getKnown() {
        return known;
    }

}
