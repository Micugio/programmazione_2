package lecture22.collectionsMVC.model.comparators;
import lecture22.asimpleMVC.model.StoneBlock;
import java.util.Comparator;
public class BlockQtyComparator implements Comparator<StoneBlock> {
    @Override
    public int compare(StoneBlock st, StoneBlock st1) {
        return st.getQty() - st1.getQty();
    }
}