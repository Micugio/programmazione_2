package lecture03.final_mod;
public enum ToolTier {
    WOOD(59, 2.0f),
    STONE(131, 4.0f),
    IRON(250, 6.0f),
    DIAMOND(1561, 8.0f),
    GOLD(32, 12.0f);
    private final int maxUses;
    private final float efficiency;
    ToolTier(int maxUses, float efficiency) {
        this.maxUses = maxUses;
        this.efficiency = efficiency;
    }
    public float getEfficiency() {
        return this.efficiency;
    }
    public int getMaxUses() {
        return this.maxUses;
    }
}