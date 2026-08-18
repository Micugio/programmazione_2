package lecture17.eq;
import java.util.Objects;
public class BlockPosEqHc {
    final int x, y, z;
    public BlockPosEqHc(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockPosEqHc that = (BlockPosEqHc) o;
        return x == that.x && y == that.y && z == that.z;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}