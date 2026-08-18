package lecture08.multi_dimensions.brawlersAttacks;
import lecture08.multi_dimensions.attack.*;
import lecture08.multi_dimensions.attack_dimensions.areas.*;
import lecture08.multi_dimensions.attack_dimensions.distance.*;
import lecture08.multi_dimensions.attack_dimensions.lobbers.*;
import lecture08.multi_dimensions.attack_dimensions.multiple.*;
public class ElPrimoAttack extends AbstractAttack {
    public ElPrimoAttack() {
        super(
                new RectangleArea(),
                new NoLobber(),
                new SixShotsAttack(),
                new ShortDistanceAttack()
        );
    }
    @Override
    protected int singleAttackDamage() {
        return 600;
    }
}