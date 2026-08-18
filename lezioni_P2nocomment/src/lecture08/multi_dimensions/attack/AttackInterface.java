package lecture08.multi_dimensions.attack;
import lecture08.multi_dimensions.attack_dimensions.areas.AreaInterface;
import lecture08.multi_dimensions.attack_dimensions.distance.DistanceInterface;
import lecture08.multi_dimensions.attack_dimensions.lobbers.LobberInterface;
import lecture08.multi_dimensions.attack_dimensions.multiple.MultipleShotsInterface;
public interface AttackInterface extends AreaInterface, LobberInterface, MultipleShotsInterface, DistanceInterface {
    void attack(String broname);
}