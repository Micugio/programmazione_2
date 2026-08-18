package lecture22.multiInputsMVC;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lecture22.multiInputsMVC.controller.MultiStoneBlockController;
import lecture22.asimpleMVC.model.StoneBlock;
import lecture22.asimpleMVC.view.MainView;
public class MultiMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        StoneBlock s0 = new StoneBlock(64, "Cobblestone");
        MultiStoneBlockController sc0 = new MultiStoneBlockController(s0);
        MainView mv = new MainView(sc0.getSv());
        StackPane root = new StackPane(mv);
        primaryStage.setTitle("Multiple Inputs MVC Example");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}