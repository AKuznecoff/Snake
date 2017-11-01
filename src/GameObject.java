public abstract class GameObject implements IGameObject {
    private Point position;

    public Point getPosition() {
        return position;
    }

    protected void setPosition(Point position){
        this.position = position;
    }
}
