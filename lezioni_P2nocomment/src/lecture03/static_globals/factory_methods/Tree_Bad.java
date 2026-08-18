package lecture03.static_globals.factory_methods;
public class Tree_Bad {
    private String type;
    public int height;
    Tree_Bad(String type, int height) {
        this.type = type;
        this.height = height;
    }
    public static Tree_Bad createBadOak() {
        return new Tree_Bad("Oak", 5);
    }
    public static Tree_Bad createBadBirch() {
        return new Tree_Bad("Birch", 7);
    }
    public static Tree_Bad createBadJungle() {
        return new Tree_Bad("Jungle", 20);
    }
}