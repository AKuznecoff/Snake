import java.awt.*;

public enum Directions {
    UP(new Shift(0, -1)),
    DOWN(new Shift(0, 1)),
    LEFT(new Shift(-1, 0)),
    RIGHT(new Shift(1, 0));

    private Shift shift;

    Directions(Shift shift) {
        this.shift = shift;
    }

    public Directions getOpposite() {
        switch (this){
            case RIGHT: return LEFT;
            case UP: return DOWN;
            case DOWN: return UP;
            case LEFT: return RIGHT;
            default: throw new IllegalStateException(this + "haven't opposite");
        }
    }

    public Shift getShift() {
        return shift;
    }
}
