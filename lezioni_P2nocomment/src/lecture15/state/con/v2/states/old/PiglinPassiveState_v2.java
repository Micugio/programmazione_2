package lecture15.state.con.v2.states.old;
import lecture15.state.con.v2.Piglin_v2;
import lecture15.state.con.v2.states.PiglinAggressive2State_v2;
import lecture15.state.con.v2.states.PiglinAggressive3State_v2;
import lecture15.state.con.v2.states.PiglinStateInterface_v2;
public class PiglinPassiveState_v2 extends AbstractPiglinState_v2 implements PiglinStateInterface_v2 {
    public PiglinPassiveState_v2(Piglin_v2 p) {
        super(p);
    }
    @Override
    public void see_player_no_gold() {
        this.pig.setState(new PiglinAggressive1State_v2(this.pig));
        System.out.println("Pigling vede che non indossi oro, diventa aggressivo");
    }
    @Override
    public void unsee_player() {
        System.out.println("Piglin non ti vede piu`");
    }
    @Override
    public void see_player_wearing_gold() {
        this.pig.setState(new PiglinFriendlyState_v2(this.pig));
        System.out.println("Piglin vede che indossi oro, e` amichevole");
    }
    @Override
    public void player_opens_chest() {
        this.pig.setState(new PiglinAggressive2State_v2(this.pig));
        System.out.println("Piglin vede che apri un forziere, diventa aggro 2");
    }
    @Override
    public void player_attacks_piglin() {
        this.pig.setState(new PiglinAggressive3State_v2(this.pig));
        System.out.println("Piglin viene attaccato e si arrabbia");
    }
}