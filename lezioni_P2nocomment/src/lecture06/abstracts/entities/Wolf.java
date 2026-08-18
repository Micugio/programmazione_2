package lecture06.abstracts.entities;
import lecture06.abstracts.Entity;
public class Wolf extends Entity {
    private boolean isTamed;
    public Wolf(){
        super();
        System.out.println("Wolf-specific logic");
        this.isTamed = false;
    }
}