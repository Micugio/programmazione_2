package lecture03.static_globals.factory_methods;
public class TreeFactory {
    public static Tree createDarkOak() {
        return new Tree(Tree.Type.DarkOak, 5);
    }
    public static Tree createBirch() {
        return new Tree(Tree.Type.Birch, 7);
    }
    public static Tree createJungle() {
        return new Tree(Tree.Type.Jungle, 20);
    }
}