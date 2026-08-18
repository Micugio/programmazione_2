package lecture18.visitor.visitors;
import lecture18.visitor.*;
public class EmptyHandInteraction implements InteractionVisitor {
    @Override
    public void visit(Villager villager) {
        System.out.println("Opening Trading GUI for " + villager.getProfession() + " Villager.");
    }
    @Override
    public void visit(Sheep sheep) {
        System.out.println("Petting the sheep. (Needs shears to get wool).");
    }
    @Override
    public void visit(Creeper creeper) {
        System.out.println("Punched a Creeper. It stares at you blankly.");
    }
}