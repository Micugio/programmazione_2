package lecture02.v_enums;
public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST,
    UP,
    DOWN;
    public int pp(){
        return this.ordinal();
    }
}