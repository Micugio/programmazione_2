package lecture02.v_enums;

//@ La Enum Direction e le sue varianti
public enum Direction {
    NORTH, //@ NOTA: per il compilatore se non scrivo niente NORTH=0, EAST=1, ...
    EAST,
    SOUTH,
    WEST,
    UP,
    DOWN;

    public int pp(){
        return this.ordinal();
    }

}

