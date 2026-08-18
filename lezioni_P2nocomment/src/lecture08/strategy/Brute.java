package lecture08.strategy;
public class Brute extends Piglin{
    @Override
    public void execute() {
        this.currentStrategy.execute();
    }
}