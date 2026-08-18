package lecture15.state.con.v2;
import lecture15.state.con.v1.states.PiglinStateInterface_v1;
import lecture15.state.con.v2.states.PiglinStateInterface_v2;
import lecture15.state.con.v2.states.old.PiglinPassiveState_v2;
public class Piglin_v2 implements PiglinStateInterface_v1, PiglinStateInterface_v2 {
    private PiglinStateInterface_v2 state;
    public Piglin_v2(){
        this.setState(new PiglinPassiveState_v2(this));
    }
    public void setState(PiglinStateInterface_v2 s) {
        this.state = s;
    }
    @Override
    public void see_player_no_gold() {
        this.state.see_player_no_gold();
    }
    @Override
    public void unsee_player() {
        this.state.unsee_player();
    }
    @Override
    public void see_player_wearing_gold() {
        this.state.see_player_wearing_gold();
    }
    @Override
    public void player_opens_chest() {
        this.state.player_opens_chest();
    }
    @Override
    public void player_attacks_piglin() {
        this.state.player_attacks_piglin();
    }
}