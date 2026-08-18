package lecture08.multi_dimensions.attack_dimensions.areas;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
public class CircleArea implements AreaInterface {
    @Override
    public Shape getAttackShape() {
        return new Circle();
    }
}