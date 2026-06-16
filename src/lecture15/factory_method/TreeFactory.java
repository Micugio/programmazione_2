package lecture15.factory_method;

public class TreeFactory {
    //@ I metodi di questa classe contengono la logica, e la complessita` legata a creare un certo tipo di albero
    //@ Questa complessita` e` omessa per fini didattici
    public static Tree createDarkOak() {
        //@ inspessire il tronco
        return new Tree(Tree.Type.DarkOak, 5);
    }

    public static Tree createBirch() {
        return new Tree(Tree.Type.Birch, 7);
    }

    public static Tree createJungle() {
        //@ aggiungere rampicanti
        return new Tree(Tree.Type.Jungle, 20);
    }
}
