package lecture03.static_globals.factory_methods;
public class Tree {
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