package lecture05.inheritance;
public class Entity {
    protected double x;
    protected double y;
    public void teleport(double newX, double newY) {
        this.x = newX;
        this.y = newY;
    }
    public void printLocation(){
        System.out.println("Entity " + this.getClass().toString().substring(28) + " at: " + this.x + " " + this.y );
    }
    public Entity(){
        this.x = 10;
        this.y = 10;
        System.out.print(" in Entity() ");
    }
    public Entity(int x, int y){
        this.x = x;
        this.y = y;
        System.out.print(" in Entity(x,y) ");
    }
}