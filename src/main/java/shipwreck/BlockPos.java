package shipwreck;

public class BlockPos {
    private int X = 0;
    private int Y = 0;
    private int Z = 0;

    public BlockPos(int x, int y, int z) {
        this.X = x;
        this.Y = y;
        this.Z = z;
    }

    public BlockPos() {

    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public int getZ() {
        return Z;
    }

    public void setX(int x) {
        X = x;
    }

    public void setY(int y) {
        Y = y;
    }

    public void setZ(int z) {
        Z = z;
    }

}
