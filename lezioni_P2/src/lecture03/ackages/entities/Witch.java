package lecture03.ackages.entities;

public class Witch {

    //@ Da qui, possiamo accedere ai campi Package-Private di `Player`, ma non ai campi `private`
    public void fakeAttack(Player p) {
        p.fakeHealth = 0;
        //@ La riga di sotto non compila
        // p.health = 0;
    }

    public void attack(Player p){
        // p.damage(9);
        // p.damage(9);
        // p.poisonDamage();
        // p.setPoisoned();
        // p.poisonDamage();
    }
}
