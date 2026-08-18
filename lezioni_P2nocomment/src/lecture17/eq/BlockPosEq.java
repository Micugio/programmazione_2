package lecture17.eq;
public class BlockPosEq {
    int x, y, z;
    public BlockPosEq(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockPosEq that = (BlockPosEq) o;
        return x == that.x && y == that.y && z == that.z;
    }
}