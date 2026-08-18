package lecture22.asimpleMVC.view;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
public class StoneBlockView extends StackPane {
    private HBox hb;
    private Rectangle r;
    private Button nameButton;
    private Text qtyText;
    public StoneBlockView(int age, String name){
        this.nameButton = new Button();
        this.nameButton.setText(name);
        this.qtyText = new Text();
        this.qtyText.setText(age+"");
        this.r = new Rectangle(150, 32);
        this.r.setStroke(Color.GRAY);
        this.r.setStrokeWidth(2);
        this.r.setFill(Color.DARKGRAY);
        this.hb = new HBox();
        this.hb.getChildren().add(this.nameButton);
        this.hb.getChildren().add(this.qtyText);
        this.getChildren().add(r);
        this.getChildren().add(hb);
        this.hb.setAlignment(Pos.CENTER);
        this.hb.setSpacing(10);
        this.setAlignment(this.hb.getAlignment());
    }
    public Button getNameButton() { return this.nameButton; }
    public Text getQtyText() { return this.qtyText; }
}