package lecture06.abstracts.entities;
import lecture06.abstracts.Entity;
public class Chicken extends Entity {
    private int speed;
    public Chicken(){
        super();
        System.out.println("Chicken-specific logic");
        this.speed = 10;
    }
    public void makeSound() {
        System.out.println("Chicken: Cluck Cluck!");
    }
}