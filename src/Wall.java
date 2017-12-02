public class Wall extends GameObject {
    public Wall(Point position){
        setPosition(position);
    }

    @Override
    public boolean checkEdible() {
        return false;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.drawObject(this);
    }
}
