package lecture06.abstracts;

public abstract class Entity {

    protected int x, y;

    //@ Tipicamente, il costruttore di una classe `abstract` e` `protected`, perche` va richiamato dalle sottoclassi, ma non permette di istanziare questa classe
    protected Entity(){
        this.x = 10;
        this.y = 10;
        System.out.println("Initialising entity common fields and other shared logic");
    }

    public void teleport(int x, int y) {
        this.x = x;
        this.y = y;
        System.out.println(this.getClass().getSimpleName() + " moved to " + x + ", " + y);
    }
}