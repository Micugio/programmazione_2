package lecture15.state.con.v2.states.old;
import lecture15.state.con.v2.Piglin_v2;
import lecture15.state.con.v2.states.PiglinAggressive2State_v2;
import lecture15.state.con.v2.states.PiglinAggressive3State_v2;
import lecture15.state.con.v2.states.PiglinStateInterface_v2;
public class PiglinAggressive1State_v2 extends AbstractPiglinState_v2 implements PiglinStateInterface_v2 {
    protected PiglinAggressive1State_v2(Piglin_v2 p) {
        super(p);
    }
    @Override
    public void see_player_no_gold() {}
    @Override
    public void unsee_player() {
        this.pig.setState(new PiglinPassiveState_v2(this.pig));
        System.out.println("Piglin non ti vede piu`");
    }
    @Override
    public void see_player_wearing_gold() {
        this.pig.setState(new PiglinFriendlyState_v2(this.pig));
        System.out.println("Piglin vede che indossi oro, diventa amichevole");
    }
    @Override
    public void player_opens_chest() {
        super.player_opens_chest();
    }
    @Override
    public void player_attacks_piglin() {
        super.player_attacks_piglin();
    }
}