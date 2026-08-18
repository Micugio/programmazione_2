package lecture22.collectionsMVC.view;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lecture22.collectionsMVC.controller.BlockStackController;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import lecture22.collectionsMVC.controller.comparators.BlockControllerComparatorByName;
import lecture22.collectionsMVC.controller.comparators.BlockControllerComparatorByQty;
import lecture22.asimpleMVC.view.StoneBlockView;
import java.util.ArrayList;
public class BlockStackView extends VBox {
    private Button usaButton;
    public BlockStackView(ArrayList<StoneBlockView> asv, BlockStackController cc){
        for (StoneBlockView sv: asv) {
            this.getChildren().add(sv);
        }
        this.usaButton = new Button("Usa");
        this.usaButton.setOnAction( (e) -> {
            cc.usaBlocchi();
        } );
        this.getChildren().add(usaButton);
        this.addEventHandler(KeyEvent.ANY, (e) -> {
            if( e.getCode() == KeyCode.N ){
                cc.controllerSort(new BlockControllerComparatorByName());
            }
            if( e.getCode() == KeyCode.Q ){
                cc.controllerSort(new BlockControllerComparatorByQty());
            }
        });
    }
}