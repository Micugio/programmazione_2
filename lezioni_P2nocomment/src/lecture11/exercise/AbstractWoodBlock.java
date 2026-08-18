package lecture11.exercise;
import lecture11.templateMethod.AbstractBlock;
public abstract class AbstractWoodBlock extends AbstractBlock {
    @Override
    protected void playBreakSound() { System.out.println(" (Soft Wood Sound)");}
 }