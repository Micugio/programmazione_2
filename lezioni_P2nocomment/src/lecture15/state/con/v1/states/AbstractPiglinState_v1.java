package lecture15.state.con.v1.states;
import lecture15.state.con.v1.Piglin_v1;
public abstract class AbstractPiglinState_v1 {
    protected Piglin_v1 pig;
    protected AbstractPiglinState_v1(Piglin_v1 p){
        this.pig = p;
    }
}