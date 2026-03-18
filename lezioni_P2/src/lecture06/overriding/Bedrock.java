package lecture06.overriding;

import lecture06.abstracts.Block;

public class Bedrock extends Block {

    //@ Un metodo che fa `override` non deve per forza richiamare il `super`, anzi
    @Override
    public void mine() {
        System.out.println(">> *CLINK!* (Too hard to break!)");
    }

    @Override
    public Block.Material getDrop() {
        return Material.BEDROCK;
    }
}
