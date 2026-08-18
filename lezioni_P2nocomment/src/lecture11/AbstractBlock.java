package lecture11;
public abstract class AbstractBlock {
    public final void destroyBlock() {
        spawnParticles();
        playBreakSound();
        dropLoot();
    }
    private void spawnParticles() {
        System.out.println(" *Poof* (Particles spawned)");
    }
    protected void playBreakSound() {
        System.out.println(" *Crack* (Sound played)");
    }
    protected abstract void dropLoot();
}
class DiamondOre extends AbstractBlock {
    @Override
    protected void dropLoot() {
        System.out.println(" Dropped: 1x Diamond");
    }
}
class GlassBlock extends AbstractBlock {
    @Override
    protected void dropLoot() {
        System.out.println(" Dropped: Nothing (It shattered)");
    }
    @Override
    protected  void playBreakSound() {
        System.out.println("*Tink* (Glass Sound)");
    }
}