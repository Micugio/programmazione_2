package lecture08.multi_dimensions.attack_dimensions.areas;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
public class TriangleArea implements AreaInterface{
    @Override
    public Shape getAttackShape() {
        Polygon triangle = new Polygon();
        triangle.getPoints().setAll(
                0.0, 0.0,
                60.0, 0.0,
                45.0, 30.0
        );
        return triangle;
    }
}