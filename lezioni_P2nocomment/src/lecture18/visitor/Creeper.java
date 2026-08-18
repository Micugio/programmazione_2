package lecture18.visitor;
import lecture18.visitor.visitors.InteractionVisitor;
public class Creeper implements VisitableEntity {
    public boolean isIgnited = false;
    @Override
    public void accept(InteractionVisitor visitor) {
        visitor.visit(this);
    }
}