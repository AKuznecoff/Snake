public class Wall extends GameObject {
    public Wall(Point position){
        setPosition(position);
    }

    @Override
    public boolean checkEdible() {
        return false;
    }
}
