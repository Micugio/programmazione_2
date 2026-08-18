package lecture10.arguments;
public class Miner {
    public void mine(Pick p) {
        System.out.println("-> Used Generic Mining (Slow)");
    }
    public void mine(DiamondPick p) {
        System.out.println("-> Used Diamond Mining (Instant!)");
    }
    public void mineWithSubcall(Pick p) {
        System.out.println("-> Used Generic Mining (Slow)");
        p.pick();
    }
}