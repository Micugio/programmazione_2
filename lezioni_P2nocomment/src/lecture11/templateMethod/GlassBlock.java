package lecture11.templateMethod;
public class GlassBlock extends AbstractBlock {
    @Override
    protected void dropLoot() {
        System.out.println(" Dropped: Nothing (It shattered)");
    }
    @Override
    protected void playBreakSound() {
        System.out.println("*Tink* (Glass Sound)");
    }
}