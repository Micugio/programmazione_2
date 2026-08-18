package lecture08.multi_dimensions.attack;
import lecture08.multi_dimensions.attack_dimensions.areas.AreaInterface;
import lecture08.multi_dimensions.attack_dimensions.distance.DistanceInterface;
import lecture08.multi_dimensions.attack_dimensions.lobbers.LobberInterface;
import lecture08.multi_dimensions.attack_dimensions.multiple.MultipleShotsInterface;
import javafx.scene.shape.Shape;
import java.util.ArrayList;
public abstract class AbstractAttack implements AttackInterface {
    protected AreaInterface ai;
    protected LobberInterface la;
    protected MultipleShotsInterface msi;
    protected DistanceInterface di;
    protected AbstractAttack(AreaInterface a, LobberInterface l,
                             MultipleShotsInterface m, DistanceInterface d){
        this.ai = a;
        this.la = l;
        this.msi = m;
        this.di = d;
    }
    public void attack(String broname){
        ArrayList<Shape> allAtts = new ArrayList<>();
        int totatts = this.msi.multishots();
        for (int i = 0; i < totatts; i++) {
            allAtts.add(this.ai.getAttackShape());
        }
        String lobbing = this.lobs() ? "lobbing" : "not lobbing";
        System.out.println(broname + " attacks "+lobbing+": ");
        for (Shape s :allAtts) {
            System.out.println("\t "+this.singleAttackDamage()+
                    " danni a distanza "+this.length()+
                    " in area "+this.getAttackShape().getClass().getSimpleName());
        }
    }
    protected abstract int singleAttackDamage();
    @Override
    public int length(){
        return this.di.length();
    }
    @Override
    public int multishots(){
        return this.msi.multishots();
    }
    @Override
    public boolean lobs(){
        return this.la.lobs();
    }
    @Override
    public Shape getAttackShape(){
        return this.ai.getAttackShape();
    }
}