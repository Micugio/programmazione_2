package lecture17.sorting;
public class LootItem implements Comparable<LootItem> {
    String name;
    private int goldValue;
    public LootItem(String name, int value) {
        this.name = name;
        this.goldValue = value;
    }
    public String getName() {
        return name;
    }
    public int getValue() {
        return goldValue;
    }
    @Override
    public int compareTo(LootItem other) {
        return other.goldValue - this.goldValue;
    }
    @Override
    public String toString() {
        return name + " (" + goldValue + "g)";
    }
}