package lecture18.visitor;
import lecture18.visitor.visitors.InteractionVisitor;
public interface VisitableEntity {
    void accept(InteractionVisitor visitor);
}