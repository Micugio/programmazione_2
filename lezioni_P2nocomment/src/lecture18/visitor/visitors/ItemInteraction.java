package lecture18.visitor.visitors;
import lecture18.visitor.*;
public class ItemInteraction implements InteractionVisitor {
    private String holdingItem;
    public ItemInteraction(String holdingItem) {
        this.holdingItem = holdingItem;
    }
    @Override
    public void visit(Villager villager) {
        System.out.println("Villager refuses " + holdingItem + ". Opens Trading GUI.");
    }
    @Override
    public void visit(Sheep sheep) {
        if (holdingItem.equals("Shears") && sheep.hasWool) {
            System.out.println("SNIP! Sheared the sheep.");
            sheep.hasWool = false;
        } else {
            System.out.println("Punched sheep with " + holdingItem);
        }
    }
    @Override
    public void visit(Creeper creeper) {
        if (holdingItem.equals("Flint and Steel")) {
            System.out.println("TSSSS... Creeper ignited!");
            creeper.isIgnited = true;
        } else {
            System.out.println("Hit Creeper with " + holdingItem);
        }
    }
}