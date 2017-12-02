public class Food extends GameObject{
    private final static int scores = 1;
    public Food(Point position){
        setPosition(position);
    }

    public int getScores(){
        return scores;
    }

    @Override
    public boolean checkEdible() {
        return true;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.drawObject(this);
    }
}
