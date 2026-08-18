package lecture06.overriding;
import lecture06.abstracts.Block;
public class Bedrock extends Block {
    @Override
    public void mine() {
        System.out.println(">> *CLINK!* (Too hard to break!)");
    }
    @Override
    public Block.Material getDrop() {
        return Material.BEDROCK;
    }
}