package lecture22.asimpleMVC.view;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
public class MainView extends BorderPane {
    public MainView(Node cv) {
        this.setCenter(cv);
    }
}