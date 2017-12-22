import java.io.Serializable;

public class SnakeSegment implements Serializable{
    private Point position;

    public SnakeSegment(Point position) {
        setPosition(position);
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SnakeSegment that = (SnakeSegment) o;
        return position.equals(that.position);
    }

    @Override
    public int hashCode() {
        return position.hashCode();
    }


}
