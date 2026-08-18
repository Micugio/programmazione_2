package lecture08.multi_dimensions.brawlersAttacks;
import lecture08.multi_dimensions.attack.*;
import lecture08.multi_dimensions.attack_dimensions.areas.*;
import lecture08.multi_dimensions.attack_dimensions.distance.*;
import lecture08.multi_dimensions.attack_dimensions.lobbers.*;
import lecture08.multi_dimensions.attack_dimensions.multiple.*;
public class PiperAttack extends AbstractAttack {
    public PiperAttack() {
        super(
                new RectangleArea(),
                new NoLobber(),
                new SingleShotAttack(),
                new LongDistanceAttack()
        );
    }
    @Override
    protected int singleAttackDamage() {
        return 4000;
    }
}