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
    //@ Nella lezione precedente abbiamo usato `setOnAction` che e` un convenience method per assegnare comportamento ad un bottone.
    //@ Oggi guardiamo ai dettagli do come JavaFX gestisce il mouse e la tastiera

    @Override
    public void start(Stage primaryStage) {

        //@ ## La catena degli Eventi
        //@ JavaFX e` un framework ad Eventi: tutto e` un evento, e gli eventi vengono creati, gestiti, filtrati e consumati.
        //@ Quando clicchiamo il mouse, JavaFX crea un `Event`, che segue un percorso specifico nella 'Event Dispatch Chain'.
        //@ Questa ha due fasi:
        //@ 1. Fase di Cattura (Filters): L'evento viaggia top-down dalla root (Scene) al Node destinazione
        //@ 2. Fase di Emersione (Handlers): L'evento viaggia bottom-up, dal nodo indietro alla root

        //@ Creiamo uno slot per inventario
        Rectangle slotBackground = new Rectangle(100, 100);
        slotBackground.setFill(Color.web("#8b8b8b"));
        slotBackground.setStroke(Color.web("#373737"));
        slotBackground.setStrokeWidth(4);

        Text itemText = new Text(dirtCount + " Dirt");
        itemText.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        itemText.setFill(Color.WHITE);

        StackPane inventorySlot = new StackPane(slotBackground, itemText);

        //@ Settando la `MaxSize` preveniamo che lo StackPane diventi largo come la finestra intera
        //@ Pertanto, gli eventi si generano solo in questa superficie 100x100
        inventorySlot.setMaxSize(100, 100);

        StackPane root = new StackPane(inventorySlot);
        root.setStyle("-fx-background-color: #c6c6c6;");
        Scene scene = new Scene(root, 600, 400);

        //@ ## Event Handlers

        //@ Gli handler intercettano gli eventi durante la fase di emersione, e sicollegano direttamente agli elementi dell’interfaccia con cui vuoi interagire.
        //@ Di seguito utilizziamo le lambda (e -> { ... }) invece delle classi anonime.

        //@ Il metodo `addEventHandler` vuole non solo la lambda, ma anche il tipo di evento da filtrare.
        //@ In questo caso `MOUSE_ENTERED` e` quello che viene creato quando il mouse passa sull'elemento.
        inventorySlot.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            slotBackground.setFill(Color.web("#a0a0a0"));
            System.out.println("[Handler] Mouse hovered over the Dirt.");
        });

        //@ In questo caso, `MOUSE_EXITED` si genera quando il cursore esce dall'elemento.
        inventorySlot.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            slotBackground.setFill(Color.web("#8b8b8b"));
        });

        //@ L'evento `MOUSE_CLICKED` permette di differenziare tra right e left click
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

        //@ ## Event Filters

        //@ I filtri intercettano gli eventi durante la fase di cattura.
        //@ Poiché vengono eseguiti PER PRIMI, di solito si applicano alla 'Scene' principale per intercettare input globali.
        //@ Un filtro, o un handler puo` chiamare `event.consume()`, e l’evento viene distrutto, non raggiungerà mai gli elementi successivi (altri filtri, handlers).
        //@ Il tipo `KEYBOARD_PRESSED` permette di aggiungere logica agli shortcut da tastiera
        //@ Notate che aggiungiamo un filtro qui.
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            //@ Controlliamo il Code per vedere quale tasto e` stato premuto
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

        //@ Questo e` un altro filtro per controllare la propagazione degli eventi
        //@ Viene chiamata la `consume` per evitare che l'evento raggiunga gli handlers di prima
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