package lecture05.inheritance;

public class Zombie extends Entity {

    private boolean isHolding;

    //@ Il metodo `groan()` usa 'this.x' e 'this.y' che eredita da `Entity`
    public void groan() {
        System.out.println("Zombie at [" + this.x + ", " + this.y + "] says: Groaaaann...");
    }

    public double getX(){
        return this.x;
    }

    public Zombie(){
        isHolding = false;
        System.out.print(" in Zombie() ");
    }
    //@ Il costruttore `Zombie(int x)` richiama il costruttore `Zombie`, non `Entity`
    public Zombie(int x){
        this();
        System.out.print(" in Zombie(x) ");
    }
}
