package lecture06.abstracts.blocks;
import lecture06.abstracts.Block;
public class Dirt extends Block {
    public Material mineWithDrop(){
        this.mine();
        return this.getDrop();
    }
    public Material getDrop() {
        return Material.DIRT;
    }
}