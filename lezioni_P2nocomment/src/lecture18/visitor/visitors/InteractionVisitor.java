package lecture18.visitor.visitors;
import lecture18.visitor.Creeper;
import lecture18.visitor.Sheep;
import lecture18.visitor.Villager;
public interface InteractionVisitor {
    void visit(Villager villager);
    void visit(Sheep sheep);
    void visit(Creeper creeper);
}