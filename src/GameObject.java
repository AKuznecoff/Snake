import java.io.Serializable;

public abstract class GameObject implements IGameObject, Serializable {
    private Point position;

    public Point getPosition() {
        return position;
    }

    protected void setPosition(Point position){
        this.position = position;
    }
}
