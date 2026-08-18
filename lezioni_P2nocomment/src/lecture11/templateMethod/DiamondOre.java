package lecture11.templateMethod;
public class DiamondOre extends AbstractBlock {
    @Override
    protected void dropLoot() {
        System.out.println(" Dropped: 1x Diamond");
    }
}