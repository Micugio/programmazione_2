package lecture06.overriding;

import lecture06.abstracts.Block;

public class GoldBlock extends Block {

    @Override
    public void mine() {
        super.mine();
        System.out.println("Alerting Piglins ...");
        super.visuals();
    }

    //@ Il metodo `getDrop()` mostra che il tag `@Override` si usa anche per i metodi `abstract`
    @Override
    public Material getDrop() {
        return Material.GOLD_BLOCK;
    }
}
