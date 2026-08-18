package lecture18.observer.v2.behaviours;
import lecture18.observer.Player;
import lecture18.observer.v2.mob.MobInterface;
public class ChaseAndAttackBehaviour {
    public void action(Player p, MobInterface m) {
            if (p.getLocation() < m.getLocation()){
                m.addToLocation( - m.getSpeed() );
            }
            if (p.getLocation() > m.getLocation()){
                m.addToLocation( m.getSpeed() );
            }
            if (p.getLocation() == m.getLocation()){
                System.out.println(m.getName() + " attacks");
            }
    }
}