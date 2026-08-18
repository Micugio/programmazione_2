package lecture20;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lecture20.lambdas.MyClassFI;
import lecture20.lambdas.MyClassNotFI;
import lecture20.lambdas.MyConsumer;
import java.util.ArrayList;
import java.util.function.ToIntFunction;
public class Lecture20 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Node n = new Text("");
        Text titleText = new Text("Game Menu");
        titleText.setFont(new Font("Courier New", 40));
        titleText.setFill(Color.WHITE);
        Label versionLabel = new Label("Minecraft 1.20.4 (Modded)");
        versionLabel.setTextFill(Color.LIGHTGRAY);
        Button resumeBtn = new Button("Back to Game");
        Button optionsBtn = new Button("Options...");
        Button quitBtn = new Button("Save and Quit to Title");
        Rectangle darkBackdrop = new Rectangle(600, 400);
        darkBackdrop.setFill(Color.rgb(0, 0, 0, 0.6));
        String playerName = "Steve";
        long worldSeed = 8472948274L;
        resumeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Resuming game for " + playerName + " in world: " + worldSeed);
            }
        });
        quitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Saving chunks... Exiting.");
                System.exit(0);
            }
        });
        Pane p = new Pane();
        p.addEventHandler(ActionEvent.ANY, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            }
        });
        int local = 0;
        p.addEventHandler(ActionEvent.ANY, (ActionEvent event) -> {System.out.println(local);});
        MyClassFI mfi = new MyClassFI();
        MyClassNotFI mnf = new MyClassNotFI();
        MyConsumer mc = new MyConsumer();
        mc.consumeFI(mfi);
        mc.consumeFI( () -> {return "What";} );
        mc.consumeFI( () -> {return "Flame";} );
        mc.consumeNotFI(mnf);
        ArrayList<Integer> a = new ArrayList<>(10);
        a.forEach((i) -> {i.hashCode();});
        a.forEach(Object::hashCode);
        a.stream().filter((i) -> {return i.intValue()>0;});
        ToIntFunction tif = new ToIntFunction() {
            @Override
            public int applyAsInt(Object value) {
                return 0;
            }
        };
        a.stream().mapToInt(tif);
        HBox bottomButtonsRow = new HBox(10);
        bottomButtonsRow.setAlignment(Pos.CENTER);
        bottomButtonsRow.getChildren().addAll(optionsBtn, quitBtn);
        VBox centerMenu = new VBox(15);
        centerMenu.setAlignment(Pos.CENTER);
        centerMenu.getChildren().addAll(resumeBtn, bottomButtonsRow);
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(titleText);
        BorderPane.setAlignment(titleText, Pos.CENTER);
        mainLayout.setCenter(centerMenu);
        mainLayout.setBottom(versionLabel);
        BorderPane.setAlignment(versionLabel, Pos.BOTTOM_LEFT);
        StackPane root = new StackPane();
        root.getChildren().addAll(darkBackdrop, mainLayout);
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Minecraft Pause Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}