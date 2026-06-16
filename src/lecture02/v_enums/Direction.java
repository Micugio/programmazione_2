package lecture02.v_enums;

//@ La Enum Direction e le sue varianti
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

