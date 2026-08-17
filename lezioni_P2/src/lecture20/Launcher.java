package lecture20;

//@ # Lezione 20
public class Launcher {
    //@ ## Perche` la classe Launcher?
    //@ * A partire da Java 11, JavaFX non è più incluso nel Java Development Kit (JDK) standard. E` una libreria esterna che utilizza il rigido "Module System" di Java.
    //@ * Se la JVM vede che la tua classe 'main' estende 'Application', entra in modalità "strict".
    //@ * La classe 'Launcher' pero` NON estende 'Application'.
    //@ * Quando la JVM avvia il Launcher, rimane nella modalità più permissiva "Class Path".

    public static void main(String[] args) {
        //@ BT: Understand, Analyze
        //@ Nodi
        //@ BT: Understand, Analyze, Evaluate
        //@ Classi anonime
        //@ BT: Analyze, Evaluate
        //@ Lambda
        //@ BT: Understand
        //@ Layout
        Lecture20.main(args);
    }
}