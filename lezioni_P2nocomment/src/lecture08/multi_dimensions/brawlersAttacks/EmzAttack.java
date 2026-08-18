package lecture08.multi_dimensions.brawlersAttacks;
import lecture08.multi_dimensions.attack.*;
import lecture08.multi_dimensions.attack_dimensions.areas.*;
import lecture08.multi_dimensions.attack_dimensions.distance.*;
import lecture08.multi_dimensions.attack_dimensions.lobbers.*;
import lecture08.multi_dimensions.attack_dimensions.multiple.*;
public class EmzAttack extends AbstractAttack {
    public EmzAttack() {
        super(
                new TriangleArea(),
                new NoLobber(),
                new TwoShotsAttack(),
                new MedDistanceAttack()
        );
    }
    @Override
    protected int singleAttackDamage() {
        return 1500;
    }
}