package lecture15.state.con.v1.states;
import lecture15.state.con.v1.Piglin_v1;
public class PiglinAggressive1State_v1 extends AbstractPiglinState_v1 implements PiglinStateInterface_v1{
    protected PiglinAggressive1State_v1(Piglin_v1 p) {
        super(p);
    }
    @Override
    public void see_player_no_gold() {}
    @Override
    public void unsee_player() {
        this.pig.setState(new PiglinPassiveState_v1(this.pig));
        System.out.println("Piglin non ti vede piu`");
    }
    @Override
    public void see_player_wearing_gold() {
        this.pig.setState(new PiglinFriendlyState_v1(this.pig));
        System.out.println("Piglin vede che indossi oro, diventa amichevole");
    }
}