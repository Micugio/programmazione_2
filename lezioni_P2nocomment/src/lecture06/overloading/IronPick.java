package lecture06.overloading;
import lecture06.abstracts.blocks.Dirt;
public class IronPick extends Pick{
    public void use(Dirt d) {}
    public void use(Dirt d, IronBlock b) { super.use(d);}
    public void use(IronBlock b) {}
}