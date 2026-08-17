package lecture17.eq;

import java.util.Objects;

// 3. The Correct Class
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

    // RULE: If a.equals(b) is true, then a.hashCode() MUST == b.hashCode()
    // Here we generate a hash from the coordinates.
    // TODO: Connect to Crypto "Do not roll your own crypto"
    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
