package lecture06.overriding;
import lecture06.abstracts.Block;
public class Andesite extends Block {
    public void mine() {
        super.mine();
    }
    @Override
    public Material getDrop() {
        return Material.ANDESITE;
    }
}