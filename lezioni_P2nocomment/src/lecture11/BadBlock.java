package lecture11;
public class BadBlock {
    public void destroyBlock() {
        System.out.println("1. Spawning Particles...");
        if (this instanceof BadGlass) {
            System.out.println("2. *Tink* (Glass Sound)");
        } else {
            System.out.println("2. *Crack* (Default Stone Sound)");
        }
        if (this instanceof BadDiamond) {
            BadDiamond diamond = (BadDiamond) this;
            diamond.dropSpecialDiamond();
        }
        else if (this instanceof BadGlass) {
            System.out.println("3. Dropped Nothing (Shattered)");
        }
        else {
            System.out.println("3. Dropped Cobblestone");
        }
    }
}
class BadDiamond extends BadBlock {
    public void dropSpecialDiamond() {
        System.out.println("3. Dropped 1x Diamond!");
    }
}
class BadGlass extends BadBlock {
}