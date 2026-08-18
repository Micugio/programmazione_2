package lecture22.asimpleMVC;
import lecture22.asimpleMVC.controller.StoneBlockController;
import lecture22.asimpleMVC.model.StoneBlock;
import lecture22.asimpleMVC.view.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        StoneBlock s0 = new StoneBlock(64, "Cobblestone");
        StoneBlockController sc0 = new StoneBlockController(s0);
        MainView mv = new MainView(sc0.getSv());
        StackPane root = new StackPane(mv);
        primaryStage.setTitle("Simple MVC Example");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}