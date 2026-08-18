package lecture06.overriding;
import lecture06.abstracts.Block;
public class GoldBlock extends Block {
    @Override
    public void mine() {
        super.mine();
        System.out.println("Alerting Piglins ...");
        super.visuals();
    }
    @Override
    public Material getDrop() {
        return Material.GOLD_BLOCK;
    }
}