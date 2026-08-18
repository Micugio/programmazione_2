package lecture18.visitor;
import lecture18.visitor.visitors.InteractionVisitor;
public class Villager implements VisitableEntity {
    public String getProfession() { return "Farmer"; }
    @Override
    public void accept(InteractionVisitor visitor) {
        visitor.visit(this);
    }
}