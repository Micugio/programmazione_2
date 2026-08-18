package lecture11;
import lecture05.inheritance.*;
import lecture10.arguments.*;
import lecture11.bad.*;
import lecture11.exercise.CherryBlock;
import lecture11.templateMethod.AbstractBlock;
import lecture11.templateMethod.DiamondOre;
import lecture11.templateMethod.GlassBlock;
public class Lecture11 {
    public static void main(String[] args) {
        System.out.println("---------------- Object Memory Layout: la v-table ----------------");
        vtableTest();
        System.out.println("---------------- Instanceof e casting ----------------");
        castingAndInstanceof();
        runBadInstanceExample();
        System.out.println("---------------- Il pattern Template Method ----------------");
        runTemplateExample();
    }
    private static void vtableTest() {
        Miner m = new Miner();
        Pick pp = new Pick();
        Pick pd = new DiamondPick();
        DiamondPick dd = new DiamondPick();
        m.mine(pp);
        m.mine(pd);
        m.mineWithSubcall(pp);
        m.mineWithSubcall(pd);
        m.mineWithSubcall(dd);
    }
    private static void castingAndInstanceof() {
        Entity genericMob1 = new Creeper(5);
        if (genericMob1 instanceof Creeper) {
            Creeper c = (Creeper) genericMob1;
            System.out.println("Watch out!");
            c.hiss();
        }
        if (genericMob1 instanceof Entity){
            System.out.println("Works with superclasses");
        }
    }
    private static void runBadInstanceExample() {
        BadBlock d = new BadDiamond();
        BadBlock g = new BadGlass();
        d.destroyBlock();
        g.destroyBlock();
    }
    private static void runTemplateExample() {
        AbstractBlock diamondOre = new DiamondOre();
        AbstractBlock glass = new GlassBlock();
        diamondOre.destroyBlock();
        glass.destroyBlock();
        AbstractBlock cherry = new CherryBlock();
        cherry.destroyBlock();
    }
}