package lecture22.collectionsMVC.view;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import lecture22.collectionsMVC.controller.BlockStackController;
import lecture22.collectionsMVC.controller.comparators.BlockControllerComparatorByName;
import lecture22.collectionsMVC.controller.comparators.BlockControllerComparatorByQty;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
public class CollectionsMainView extends BorderPane {
    public CollectionsMainView(BlockStackController cc){
        this.updateClassroom(cc.getCv());
        cc.setMv(this);
        Button sbn = new Button("Ordina per Nome");
        sbn.setOnAction((e)->{
            cc.controllerSort(new BlockControllerComparatorByName());
        });
        Button sbq = new Button("Ordina per Qty");
        sbq.setOnAction((e)->{
            cc.controllerSort(new BlockControllerComparatorByQty());
        });
        VBox hb = new VBox();
        hb.getChildren().add(sbn);
        hb.getChildren().add(sbq);
        hb.setSpacing(10);
        hb.setAlignment(Pos.CENTER_LEFT);
        this.setTop(hb);
        this.addEventHandler(KeyEvent.ANY, (e) -> {
            if( e.getCode() == KeyCode.M ){
                cc.controllerSort(new BlockControllerComparatorByName());
            }
            if( e.getCode() == KeyCode.W ){
                cc.controllerSort(new BlockControllerComparatorByQty());
            }
        });
    }
    public void updateClassroom(BlockStackView cv){
        cv.setAlignment(Pos.CENTER);
        this.setCenter(cv);
    }
}