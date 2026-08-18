package lecture08;
import lecture08.multi_dimensions.brawlersAttacks.DynamikeAttack;
import lecture08.multi_dimensions.brawlersAttacks.ElPrimoAttack;
import lecture08.strategy.strategies.*;
import lecture08.strategy.Piglin;
public class Lecture8 {
    public static void main(String[] args){
        System.out.println("---------------- Ereditarieta` VS Composizione ----------------");
        System.out.println("---------------- Strategy pattern ----------------");
        strategyPatternExample();
        System.out.println("---------------- Dimensioni multiple ----------------");
        multiDimensionDelegation();
    }
    public static void strategyPatternExample() {
        Piglin monster = new Piglin();
        System.out.println("\n--- Round 1: Default Behavior ---");
        monster.fight();
        System.out.println("\n--- Round 2: Picking up a crossbow ---");
        monster.setStrategy(new CrossbowAttack());
        monster.fight();
    }
    private static void multiDimensionDelegation(){
        DynamikeAttack da = new DynamikeAttack();
        da.attack("Dynamike");
        ElPrimoAttack ea = new ElPrimoAttack();
        ea.attack("El Primo");
    }
}