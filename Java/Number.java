public class Number {

    private int value;
    private boolean known;

    public Number(int val, boolean hidden) {
        value = val;
        known = hidden;
    }

    public int getValue() {
        return value;
    }

    public void setKnown(boolean hidden) {
        known = hidden;
    }

    public boolean getKnown() {
        return known;
    }

}
