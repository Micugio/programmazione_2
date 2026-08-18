package lecture03.ackages.entities;
public class Player {
    public String username;
    private int health = 20;
    private boolean isPoisoned = false;
    int fakeHealth = 10;
    public void setPoisoned(boolean p){
        this.isPoisoned = p;
    }
    public String getUsername(){
        return this.username;
    }
    public void damage(int amount) {
        this.health = this.health - amount;
        if (this.health <= 0) {
            System.out.println("Player died!");
        }
    }
    public void poison() {
        this.setPoisoned(true);
    }
    public void poisonDamage(){
        if (!this.isPoisoned){
            return;
        }
        if (this.isPoisoned && this.health < 2 ){
            return;
        }
        this.health -= 1;
    }
    public void isAlive(){
        if (this.health <= 0 ){
            System.out.println("Giocatore morto");
        }else{
            System.out.println("Giocatore vivo");
        }
    }
}