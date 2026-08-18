package lecture08.strategy.strategies;
public class PunchAttack implements AttackStrategy {
    @Override
    public void execute() {
        System.out.println(">> Attack: *Punch* (Melee Hit)");
    }
}