package lecture06.overloading;
import lecture06.abstracts.blocks.Dirt;
import lecture06.abstracts.entities.Chicken;
import lecture06.overriding.Bedrock;
import lecture10.bindings.Block;
public class Pick {
    public void use(Dirt d){
        d.mineWithDrop();
    }
    public void use(Bedrock b){
        b.mine();
    }
    public void use(Chicken c){
        c.makeSound();
    }
}