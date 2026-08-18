package lecture17.sorting;
import java.util.Comparator;
public class NameSorter implements Comparator<LootItem> {
    @Override
    public int compare(LootItem o1, LootItem o2) {
        return o1.name.compareTo(o2.name);
    }
}