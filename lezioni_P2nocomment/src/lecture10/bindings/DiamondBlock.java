package lecture10.bindings;
public class DiamondBlock extends Block {
    public DiamondBlock() {
        super("diamond_block");
    }
    @Override
    public final void onBreak() {
        System.out.println("DiamondBlock: *CLING* (Drops Diamonds!)");
    }
    public void getDiamond(){
        this.getRegistryCalculations();
    }
    public String getRegistryCalculations(){
        return "D";
    }
}