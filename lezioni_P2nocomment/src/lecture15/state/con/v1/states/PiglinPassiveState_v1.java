package lecture15.state.con.v1.states;
import lecture15.state.con.v1.Piglin_v1;
public class PiglinPassiveState_v1 extends AbstractPiglinState_v1 implements PiglinStateInterface_v1{
    public PiglinPassiveState_v1(Piglin_v1 p) {
        super(p);
    }
    @Override
    public void see_player_no_gold() {
        this.pig.setState(new PiglinAggressive1State_v1(this.pig));
        System.out.println("Pigling vede che non indossi oro, diventa aggressivo");
    }
    @Override
    public void unsee_player() {
        System.out.println("Piglin non ti vede piu`");
    }
    @Override
    public void see_player_wearing_gold() {
        this.pig.setState(new PiglinFriendlyState_v1(this.pig));
        System.out.println("Piglin vede che indossi oro, e` amichevole");
    }
}