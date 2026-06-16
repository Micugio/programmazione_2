package lecture10.bindings;

public class DiamondBlock extends Block {

    public DiamondBlock() {
        super("diamond_block");
    }

    @Override
    public final void onBreak() {
        System.out.println("DiamondBlock: *CLING* (Drops Diamonds!)");
    }

    public void getDiamond(){}

    //@ Non possiamo fare override di `getRegistryName`
    //@ Se lo decommentate, il compilatore si arrabbia
    /*@Override
    public String getRegistryName() {
        return "Not_A_Diamond";
    }
    */
}