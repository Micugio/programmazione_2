package lecture05.inheritance;
public class Zombie extends Entity {
    private boolean isHolding;
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
    public Zombie(int x){
        this();
        System.out.print(" in Zombie(x) ");
    }
}