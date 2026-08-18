package lecture15.state.con.v1;
import lecture15.state.con.v1.states.PiglinPassiveState_v1;
import lecture15.state.con.v1.states.PiglinStateInterface_v1;
public class Piglin_v1 implements PiglinStateInterface_v1 {
    private PiglinStateInterface_v1 state;
    public Piglin_v1(){
        this.state = new PiglinPassiveState_v1(this);
    }
    public void setState(PiglinStateInterface_v1 s){
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
}