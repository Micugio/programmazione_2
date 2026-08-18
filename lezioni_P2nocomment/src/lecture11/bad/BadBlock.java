package lecture11.bad;
public class BadBlock {
    public void destroyBlock() {
        System.out.println("1. Spawning Particles...");
        System.out.print("2. Playing Sound: ");
        if (this instanceof BadGlass) {
            System.out.println("*Tink* (Glass Sound)");
        } else {
            System.out.println("*Crack* (Default Stone Sound)");
        }
        System.out.print("3. Dropping: ");
        if (this instanceof BadDiamond) {
            BadDiamond diamond = (BadDiamond) this;
            diamond.dropSpecialDiamond();
        } else if (this instanceof BadGlass) {
            System.out.println("Nothing (Shattered)");
        } else {
            System.out.println("Cobblestone");
        }
    }
}