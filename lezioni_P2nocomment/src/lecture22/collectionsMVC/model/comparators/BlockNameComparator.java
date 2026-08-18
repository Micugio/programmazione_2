package lecture22.collectionsMVC.model.comparators;
import lecture22.asimpleMVC.model.StoneBlock;
import java.util.Comparator;
public class BlockNameComparator implements Comparator<StoneBlock> {
    @Override
    public int compare(StoneBlock o1, StoneBlock o2) {
        return o1.getName().compareTo(o2.getName());
    }
}