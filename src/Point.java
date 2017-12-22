import java.io.Serializable;

public final class Point implements Serializable {
    private final int x;
    private final int y;

    public Point(int x, int y){
        if (x < 0 || y < 0) throw new IllegalArgumentException("Coordinates must be non-negative");
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public Point shift(Shift shift){
        if (x + shift.getDx() < 0 || y + shift.getDy() < 0)
            throw new IllegalArgumentException("Coordinates must be non-negative");
        return new Point(x + shift.getDx(), y + shift.getDy());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
