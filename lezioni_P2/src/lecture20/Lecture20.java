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

        //@ ## Nodes
        //@ Tutto ciò che fa puoi visualizzare in JavaFX è un 'Node'.
        //@ I Node possono essere forme, testo o controlli interattivi (come i pulsanti).
        //@ Text vs Label: 'Text' è testo grafico puro. 'Label' è una parte di UI a cui si possono applicare sfondi, bordi e stili CSS.
        //@ Colore e Forme: Usiamo la classe 'Color' per colorare i Node, e le classi 'Shape' (come Rectangle) per disegnare geometrie primitive.

        Node n = new Text("");
        //@ Text e Labels
        Text titleText = new Text("Game Menu");
        titleText.setFont(new Font("Courier New", 40));
        titleText.setFill(Color.WHITE);
        Label versionLabel = new Label("Minecraft 1.20.4 (Modded)");
        versionLabel.setTextFill(Color.LIGHTGRAY);

        //@ Bottoni
        Button resumeBtn = new Button("Back to Game");
        Button optionsBtn = new Button("Options...");
        Button quitBtn = new Button("Save and Quit to Title");

        //@ Shapes
        Rectangle darkBackdrop = new Rectangle(600, 400);
        darkBackdrop.setFill(Color.rgb(0, 0, 0, 0.6)); // Black with 60% opacity

        //@ ## Classi Anonime e Comportamento
        //@ I 'Button' non fanno nulla di default. Dobbiamo assegnare loro un 'EventHandler'.
        //@ Invece di scrivere un’intera nuova classe pubblica in un file separato solo per un pulsante, utilizziamo una "Classe Anonima" — una classe definita esattamente nel punto in cui viene istanziata.
        //@ Una classe anonima tipicamente e` l'implementazione di una classe astratta o di una interfaccia.
        //@ Realmente, non stiamo allocando una interfaccia, stiamo definendo una classe anonima che fornisce l'implementazione dei metodi richiesti dall'interfaccia.

        //@ Se una classe anonima deve usare una variabile locale del metodo esterno (come 'playerName'), quella variabile DEVE essere "effectively final".
        //@ Questo significa che la variabile conta come 'final', cioe` non la riassegniamo mai dopo averla inizializzata. Questo previene race conditions.
        String playerName = "Steve";
        long worldSeed = 8472948274L;

        //@ Creazione di due classi anonime.
        //@ Ognuna viene istanziata come una classe (`new EventHandler...`), ma viene aggiunto il body dei metodi mancanti
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

        //@ # Lambdas
        //@ Cosa e` una lambda?
        //@ - teoria: una funzione ANONIMA: https://en.wikipedia.org/wiki/Anonymous_function
        //@ Perche` anonima? -> non la invochiamo tramite il suo nome, ma in costrutti appositi dove il nome non serve o dove e` implicito
        //@ Esempio canonico: la map, la filter
        //@ - list.map(\lambda x. x.toString());
        //@ - list.filter(\lambda x. x.isOdd());
        //@ Non serve il nome delle 2 lambda qui: vengono usate qui e mai piu`
        //@ Implementazione di una lambda: una closure
        //@ chiusura: coppia codice-environment con i bindings per risolvere le variabili CATTURATE dal codice
        //@ - cattura in java? -> variabili locali che siano effectively final
        Pane p = new Pane();
        //@ Non e` una lambda questa, e` un oggetto di una anonymous class
        p.addEventHandler(ActionEvent.ANY, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //@ `handle` potrebbe catturare, ma qui` non lo fa
            }
        });
        //@ Questa e` una lambda, in questo caso, cattura la variable `local`
        int local = 0;
        p.addEventHandler(ActionEvent.ANY, (ActionEvent event) -> {System.out.println(local);});
        //@ Sintassi delle lambda: PARAM(s) -> BODY
        //@ Perche` funziona?
        //@ https://docs.oracle.com/javase/8/javafx/api/javafx/event/EventHandler.html
        //@ controlliamo EventHandler -> ha un solo metodo!
        //@ https://docs.oracle.com/javase/8/docs/api/java/lang/FunctionalInterface.html

        //QUIZ: Quali di queste due e` una FunctionalInterface?
        //@ ---
        //QUIZ: Quali di queste classi si puo` trasformare in una lambda function?
        //@ ---
        MyClassFI mfi = new MyClassFI();
        MyClassNotFI mnf = new MyClassNotFI();

        MyConsumer mc = new MyConsumer();
        mc.consumeFI(mfi);

        //@ Il seguente metodo `consumeFI` vuole un oggetto che abbia il comportamento 'void to String'
        //@ Quindi queste due invocazioni tramite lambdas, funzionano.
        mc.consumeFI( () -> {return "What";} );
        mc.consumeFI( () -> {return "Flame";} );

        //@ Il seguente metodo `consumeNotFI` vuole un oggetto di tipo `MyClassNotFI`, che ha due comportamenti
        mc.consumeNotFI(mnf);
        //@ Se gli passiamo un oggetto, ok, ma se gli passiamo una lambda ovviamente non funziona: non sa cosa chiamare!
//        mc.consumeNotFI( ()->{return 2;} );
//        mc.consumeNotFI( (()->{return 2;}, ()->{return "2";}) );

        //@ I mouse events, pero` non ci dicono tutta la storia delle lambda
        ArrayList<Integer> a = new ArrayList<>(10);
        //@ Guardiamo il tipo del primo metodo (forEach): https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
        a.forEach((i) -> {i.hashCode();});
        //@ Alcune volte possiamo sostituire una lambda con una reference al metodo.
        a.forEach(Object::hashCode);
        //QUIZ: Qual e` il tipo di `i`?
        //@ ---
        //@ Ma cos'e` Consumer? membro di https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
        //@ Qui` ci sono diverse interfacce che forzano il tipo delle funzioni anonime che possono venire implementate
        //@ Consumer per esempio e` un T -> void, cioe` consuma cio` che gli vien dato

        a.stream().filter((i) -> {return i.intValue()>0;});
        //@ Filter vuole un predicato: https://docs.oracle.com/javase/8/docs/api/java/util/function/Predicate.html

        ToIntFunction tif = new ToIntFunction() {
            @Override
            public int applyAsInt(Object value) {
                return 0;
            }
        };
        a.stream().mapToInt(tif);
        //@ Non dobbiamo per forza passare lambdas, ma vanno bene anche oggetti che abbiano il tipo corretto

        //QUIZ: Quale e` il tipo piu` generale per una funzione 'f'?
        //@ ---

        //@ Vediamolo in: https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html
        //@ Al seguente link trovate una buona lista dei contenuti del package
        //@  http://www.java2s.com/Tutorials/Java/java.util.function/Function/index.htm

        //#### Lambdas e Implementazione
        //@ Classi e funzioni anonime sono 2 cose DIVERSE a livello di bytecode
        //@ Le anon class finiscono ognuna in 1 file.
        //@ Per esempio, vediamo i files in folder .../target/src/lecture20/Lecture_20$1 ...
        //@ Lambdas non diventano anonymous class, ma sono entita` che vengono chiamate tramite l'istruzione `invokedynamic`
        //@ More infos: https://www.infoq.com/articles/Java-8-Lambdas-A-Peek-Under-the-Hood/

        //@ # Layouts (Panes)
        //@ Inserendo semplicemente i Node sullo schermo, si sovrappongono alle coordinate (0,0).
        //@ I layout (Pane) sono contenitori invisibili che calcolano automaticamente le posizioni, permettendo il display su schermi di varia dimensione, senza dover pensare a queste (qualcun altro lo ha fatto per noi)
        //@ Ci sono tanti Layouts, noi ci focalizziamo su:
        //@ - HBox: dispone i suoi elementi orizzontalmente in un’unica riga.
        //@ - VBox: dispone i suoi elementi verticalmente in un’unica colonna.
        //@ - BorderPane: divide lo schermo in 5 aree fisse (Top, Bottom, Left, Right, Center).
        //@ - StackPane: sovrappone gli elementi uno sopra l’altro. Il primo elemento aggiunto è sullo sfondo.

        //@ #### HBOX (Horizontal)
        //@ Mettiamo i bottoni 'Options' e 'Quit' side-by-side.
        HBox bottomButtonsRow = new HBox(10);
        //@ Il parametro di 10px definisce lo spazio tra gli elementi
        bottomButtonsRow.setAlignment(Pos.CENTER);
        //@ Attenzione alla chiamata: `getChildren`
        bottomButtonsRow.getChildren().addAll(optionsBtn, quitBtn);

        //@ #### VBOX (Vertical)
        //@ Allineiamo il 'Resume' button sopra all' HBox appena fatto
        VBox centerMenu = new VBox(15);
        centerMenu.setAlignment(Pos.CENTER);
        centerMenu.getChildren().addAll(resumeBtn, bottomButtonsRow);

        //@ #### BORDERPANE
        //@ La classica finestra da app
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(titleText);
        BorderPane.setAlignment(titleText, Pos.CENTER);
        mainLayout.setCenter(centerMenu);
        mainLayout.setBottom(versionLabel);
        BorderPane.setAlignment(versionLabel, Pos.BOTTOM_LEFT);
        //@ Ha le zone: TOP/BOTTOM, LEFT/RIGHT e CENTER

        //@ #### STACKPANE
        //@ Mettiamo uno sfondo nero alla app
        StackPane root = new StackPane();
        root.getChildren().addAll(darkBackdrop, mainLayout);
        //@ Spesso usato in altri modi, per impilare Shape, Text, mantenendo l'allineamento

        //@ ## Scene e Stage
        //@ La Scene e` il contenitore per tutta la GUI dell'app.
        //@ Lo Stage e` la finestra di OS
        //@ Per mostrare l'app,
        //@ - mettiamo il layout come root della Scene
        //@ - mettiamo la Scene dentro lo Stage
        //@ - diciamo allo Stage di mostrarsi `show`

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Minecraft Pause Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}