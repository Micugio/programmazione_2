package lecture15.state.con.v2.states.old;
import lecture15.state.con.v2.Piglin_v2;
import lecture15.state.con.v2.states.PiglinAggressive2State_v2;
import lecture15.state.con.v2.states.PiglinAggressive3State_v2;
public abstract class AbstractPiglinState_v2 {
    protected Piglin_v2 pig;
    protected AbstractPiglinState_v2(Piglin_v2 p){
        this.pig = p;
    }
    protected void player_opens_chest() {
        this.pig.setState(new PiglinAggressive2State_v2(this.pig));
        System.out.println("Piglin vede che apri un forziere, diventa aggro 2");
    }
    protected void player_attacks_piglin() {
        this.pig.setState(new PiglinAggressive3State_v2(this.pig));
        System.out.println("Piglin viene attaccato e si arrabbia");
    }
}