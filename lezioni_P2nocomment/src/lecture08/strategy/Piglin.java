package lecture08.strategy;
import lecture08.strategy.strategies.AttackStrategy;
import lecture08.strategy.strategies.PunchAttack;
import lecture08.strategy.strategies.SwordAttack;
public class Piglin implements AttackStrategy{
    protected AttackStrategy currentStrategy;
    public Piglin(){
        this.currentStrategy = new PunchAttack();
    }
    public void setStrategy(AttackStrategy newStrategy) {
        this.currentStrategy = newStrategy;
        System.out.println("Piglin changed strategy to " + newStrategy.getClass().getSimpleName());
    }
    public void pickUpSword(){
        this.currentStrategy = new SwordAttack();
    }
    public void fight() {
        if (currentStrategy == null) {
            System.out.println("Piglin stands still (No Strategy).");
            return;
        }
        System.out.print("Piglin acts: ");
        currentStrategy.execute();
    }
    @Override
    public void execute() {
        this.currentStrategy.execute();
    }
}