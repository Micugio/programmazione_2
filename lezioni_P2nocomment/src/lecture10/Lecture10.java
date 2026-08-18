package lecture10;
import lecture10.arguments.*;
import lecture10.bindings.*;
public class Lecture10 {
    public static void main(String[] args) {
        System.out.println("---------------- Static VS Dynamic binding ----------------");
        predictBindingExample();
        dynamicTypeLookupExample();
        staticTypeLookupExample();
        suggestionsExample();
        varExample();
        bindingRecapExample();
        bindingExamples();
        System.out.println("---------------- Binding e tipo degli argomenti ----------------");
        argBindingExample();
        argBindingAndSubcallExample();
    }
    private static void predictBindingExample() {
        Block bb = new Block("dirt");
        bb.onBreak();
        Block bd = new DiamondBlock();
        bd.onBreak();
        DiamondBlock dd = new DiamondBlock();
        dd.onBreak();
    }
    private static void suggestionsExample() {
        Block bd = new DiamondBlock();
        DiamondBlock dd = new DiamondBlock();
    }
    private static void varExample() {
        var b = new Block("dirt");
        var d = new DiamondBlock();
        b.onBreak();
        d.onBreak();
    }
    private static void dynamicTypeLookupExample() {
        breakBlock(new Block("dirt"));
        breakBlock(new DiamondBlock());
    }
    private static void breakBlock(Block b) {
        b.onBreak();
    }
    private static void staticTypeLookupExample() {
        Block bb = new Block("dirt");
        bb.getRegistryName();
        Block bd = new DiamondBlock();
        bd.getRegistryName();
        DiamondBlock dd = new DiamondBlock();
        dd.getRegistryName();
    }
    private static void bindingRecapExample() {
        DiamondBlock dd = new DiamondBlock();
        dd.onBreak();
        dd.getRegistryName();
    }
    private static void bindingExamples() {
        class Shield {
            final void block() {
                System.out.println("Blocked");
            }
        }
        Shield s = new Shield();
        s.block();
        class Monster {
            public void growl() {
                System.out.println("Monster growls");
            }
        }
        class Zombie extends Monster {
            public void growl() {
            }
        }
        Monster m = new Zombie();
        m.growl();
        Math.abs(10);
        class Potion {
            private void brew() { }
            public Potion() {
                this.brew();
            }
        }
    }
    private static void argBindingExample() {
        Miner steve = new Miner();
        DiamondPick realDiamond = new DiamondPick();
        Pick hiddenDiamond = new DiamondPick();
        steve.mine(realDiamond);
        steve.mine(hiddenDiamond);
    }
    private static void argBindingAndSubcallExample() {
        Miner steve = new Miner();
        Pick pp = new Pick();
        Pick pd = new DiamondPick();
        steve.mineWithSubcall(pp);
        steve.mineWithSubcall(pd);
        DiamondPick dd = new DiamondPick();
    }
}