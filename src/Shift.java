public class Shift {
    private int dx;
    private int dy;

    public Shift(int dx, int dy) {
        if (Math.abs(dx) == Math.abs(dy) || Math.abs(dx) > 1 || Math.abs(dy) > 1)
            throw new IllegalArgumentException("The shift must be horizontal or vertical by one position");
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}
