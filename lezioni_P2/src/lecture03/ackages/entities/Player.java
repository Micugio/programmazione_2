package lecture03.ackages.entities;

//@ Tecnicamente, il nome `lecture03.packages.entities.Player` e` il nome completo di questa classe
public class Player {
    public String username;
    private int health = 20;
    private boolean isPoisoned = false;
    int fakeHealth = 10;

    //@ Setter per `isPoisoned`
    public void setPoisoned(boolean p){
        this.isPoisoned = p;
    }
    //@ Getter per `username`
    public String getUsername(){
        return this.username;
    }

    //@ Metodo per applicare danno.
    //@ Si puo` estendere con logica ulteriore, per esempio per ridurre il danno in caso di armatura
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
