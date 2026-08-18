package lecture15.state.con.v2.states;
import lecture15.state.con.v1.states.PiglinStateInterface_v1;
public interface PiglinStateInterface_v2 extends PiglinStateInterface_v1 {
    void player_opens_chest();
    void player_attacks_piglin();
}