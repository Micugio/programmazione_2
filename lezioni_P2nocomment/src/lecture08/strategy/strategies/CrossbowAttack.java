package lecture08.strategy.strategies;
public class CrossbowAttack implements AttackStrategy {
    @Override
    public void execute() {
        System.out.println(">> Attack: *TWANG* (Fired Arrow)");
    }
}