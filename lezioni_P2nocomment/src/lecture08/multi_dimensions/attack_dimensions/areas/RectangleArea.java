package lecture08.multi_dimensions.attack_dimensions.areas;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
public class RectangleArea implements AreaInterface{
    @Override
    public Shape getAttackShape() {
        return new Rectangle();
    }
}