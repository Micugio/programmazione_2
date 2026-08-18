package lecture21;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class Lecture21 extends Application {
    private boolean isInventoryLocked = false;
    private int dirtCount = 64;
    @Override
    public void start(Stage primaryStage) {
        Rectangle slotBackground = new Rectangle(100, 100);
        slotBackground.setFill(Color.web("#8b8b8b"));
        slotBackground.setStroke(Color.web("#373737"));
        slotBackground.setStrokeWidth(4);
        Text itemText = new Text(dirtCount + " Dirt");
        itemText.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        itemText.setFill(Color.WHITE);
        StackPane inventorySlot = new StackPane(slotBackground, itemText);
        inventorySlot.setMaxSize(100, 100);
        StackPane root = new StackPane(inventorySlot);
        root.setStyle("-fx-background-color: #c6c6c6;");
        Scene scene = new Scene(root, 600, 400);
        inventorySlot.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            slotBackground.setFill(Color.web("#a0a0a0"));
            System.out.println("[Handler] Mouse hovered over the Dirt.");
        });
        inventorySlot.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            slotBackground.setFill(Color.web("#8b8b8b"));
        });
        inventorySlot.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                System.out.println("[Handler] Picked up all 64 Dirt.");
                dirtCount = 0;
            } else if (event.getButton() == MouseButton.SECONDARY) {
                System.out.println("[Handler] Picked up half (32) Dirt.");
                dirtCount = dirtCount / 2;
            }
            itemText.setText(dirtCount + " Dirt");
        });
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.E) {
                System.out.println("[Filter] 'E' pressed. Closing inventory...");
                primaryStage.close();
            }
            if (event.getCode() == KeyCode.R) {
                System.out.println("[Filter] 'R' pressed. Resetting Dirt");
                dirtCount = 64;
                itemText.setText(dirtCount + " Dirt");
            }
            if (event.getCode() == KeyCode.L) {
                isInventoryLocked = !isInventoryLocked;
                System.out.println("[Filter] Inventory Locked state: " + isInventoryLocked);
            }
        });
        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (isInventoryLocked) {
                System.out.println("[Filter] WARNING: Inventory is locked! Click intercepted.");
                event.consume();
            }
        });
        primaryStage.setTitle("Minecraft Event Handling");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}