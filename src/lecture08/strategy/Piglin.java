package lecture08.strategy;

import lecture08.strategy.strategies.AttackStrategy;
import lecture08.strategy.strategies.PunchAttack;

public class Piglin {
    private AttackStrategy currentStrategy;

    public Piglin(){
        this.currentStrategy = new PunchAttack();
    }

    public void setStrategy(AttackStrategy newStrategy) {
        this.currentStrategy = newStrategy;
        System.out.println("Piglin changed strategy to " + newStrategy.getClass().getSimpleName());
    }

    public void fight() {
        if (currentStrategy == null) {
            System.out.println("Piglin stands still (No Strategy).");
            return;
        }
        // DELEGATION
        System.out.print("Piglin acts: ");
        currentStrategy.execute();
    }
}
