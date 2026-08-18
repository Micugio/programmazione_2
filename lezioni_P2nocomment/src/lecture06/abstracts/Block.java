package lecture06.abstracts;
public abstract class Block {
    public enum Material {
        DIRT,
        BEDROCK,
        ANDESITE,
        GOLD_BLOCK
    }
    public void mine() {
        System.out.println(">> *Crackle* (Generic Digging Sound)");
        System.out.println(">> *Particles fly*");
        System.out.println("-------------------------------");
    }
    public void visuals(){}
    public abstract Material getDrop();
}