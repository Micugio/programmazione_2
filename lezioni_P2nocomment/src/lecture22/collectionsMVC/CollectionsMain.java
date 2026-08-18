package lecture22.collectionsMVC;
import lecture22.collectionsMVC.controller.BlockStackController;
import lecture22.asimpleMVC.controller.StoneBlockController;
import lecture22.collectionsMVC.model.BlockStack;
import lecture22.asimpleMVC.model.StoneBlock;
import lecture22.collectionsMVC.view.CollectionsMainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.ArrayList;
public class CollectionsMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        StoneBlock s0 = new StoneBlock(64, "Cobblestone");
        StoneBlockController sc0 = new StoneBlockController(s0);
        StoneBlock s1 = new StoneBlock(32, "Andesite");
        StoneBlockController sc1 = new StoneBlockController(s1);
        StoneBlock s2 = new StoneBlock(1, "Diorite");
        StoneBlockController sc2 = new StoneBlockController(s2);
        BlockStack c = new BlockStack();
        c.add(s0); c.add(s1); c.add(s2);
        ArrayList<StoneBlockController> asc = new ArrayList<>();
        asc.add(sc0); asc.add(sc1); asc.add(sc2);
        BlockStackController cc = new BlockStackController(c,asc);
        CollectionsMainView cmv = new CollectionsMainView(cc);
        StackPane root = new StackPane(cmv);
        primaryStage.setTitle("Collections and MVC");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}