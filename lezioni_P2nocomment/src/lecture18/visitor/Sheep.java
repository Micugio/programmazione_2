package lecture18.visitor;
import lecture18.visitor.visitors.InteractionVisitor;
public class Sheep implements VisitableEntity {
    public boolean hasWool = true;
    @Override
    public void accept(InteractionVisitor visitor) {
        visitor.visit(this);
    }
}