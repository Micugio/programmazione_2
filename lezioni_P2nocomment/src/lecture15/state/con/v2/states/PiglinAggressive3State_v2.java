package lecture15.state.con.v2.states;
import lecture15.state.con.v1.states.AbstractPiglinState_v1;
import lecture15.state.con.v2.Piglin_v2;
import lecture15.state.con.v2.states.old.AbstractPiglinState_v2;
public class PiglinAggressive3State_v2 extends AbstractPiglinState_v2 implements PiglinStateInterface_v2 {
    public PiglinAggressive3State_v2(Piglin_v2 p) {
        super(p);
    }
    @Override
    public void see_player_no_gold() {}
    @Override
    public void unsee_player() {}
    @Override
    public void see_player_wearing_gold() {}
    @Override
    public void player_opens_chest() {}
    @Override
    public void player_attacks_piglin() {}
}