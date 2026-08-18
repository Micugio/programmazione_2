package lecture08.multi_dimensions.brawlersAttacks;
import lecture08.multi_dimensions.attack.*;
import lecture08.multi_dimensions.attack_dimensions.areas.*;
import lecture08.multi_dimensions.attack_dimensions.distance.*;
import lecture08.multi_dimensions.attack_dimensions.lobbers.*;
import lecture08.multi_dimensions.attack_dimensions.multiple.*;
public class DynamikeAttack extends AbstractAttack {
    public DynamikeAttack() {
        super(
                new CircleArea(),
                new LobberAttack(),
                new TwoShotsAttack(),
                new MedDistanceAttack()
        );
    }
    @Override
    protected int singleAttackDamage() {
        return 2500;
    }
}