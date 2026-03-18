package lecture15.factory_method;

public class Tree {
    //@ Mettiamo la Enum dentro la classe perche` e` rilevante solo per i Tree
    //@ E` accedibile dall'esterno con Tree_Good.Type.OAK perche` e` `public`
    //@ Una Enum qui ci assicura l'assenza di errori di spelling, di tipi non esistenti, etc.
    //@ Usare una Enum fa si che si possano usare solo le varianti che decidiamo noi
    public enum Type {
        DarkOak,
        Birch,
        Jungle
    }
    public final Type type;
    private int height;

    Tree(Type type, int height) {
        this.type = type;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }
}
