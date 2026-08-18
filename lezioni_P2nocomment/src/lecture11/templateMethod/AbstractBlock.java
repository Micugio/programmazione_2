package lecture11.templateMethod;
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