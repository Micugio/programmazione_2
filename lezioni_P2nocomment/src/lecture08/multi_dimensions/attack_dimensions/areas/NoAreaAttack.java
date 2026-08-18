package lecture08.multi_dimensions.attack_dimensions.areas;
import javafx.scene.shape.Shape;
public class NoAreaAttack implements AreaInterface {
    @Override
    public Shape getAttackShape() {
        return null;
    }
    @Override
    public String toString(){
        return "";
    }
}