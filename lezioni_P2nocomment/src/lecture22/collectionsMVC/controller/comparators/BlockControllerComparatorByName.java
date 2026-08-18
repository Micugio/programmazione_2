package lecture22.collectionsMVC.controller.comparators;
import lecture22.collectionsMVC.model.comparators.BlockNameComparator;
import lecture22.asimpleMVC.controller.StoneBlockController;
import java.util.Comparator;
public class BlockControllerComparatorByName implements Comparator<StoneBlockController> {
    @Override
    public int compare(StoneBlockController o1, StoneBlockController o2) {
        return new BlockNameComparator().compare(o1.getSt(),o2.getSt());
    }
}