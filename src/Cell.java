public class Cell {
    private int x;
    private int y;
    private int state;
    //private int time;

    public Cell(int x, int y, int state){
        if (x < 0 || y < 0) throw new IllegalArgumentException("Coordinates must be nonnegative");
        if (state < 0 || state > 3) throw new IllegalArgumentException("Incorrect state value"); // state = 0 - пустая ячейка, 1 - тело змейки, 2 - голова, 3 - еда
        this.x = x;
        this.y = y;
        this.state = state;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getState(){
        return state;
    }

    public void setState(int state){
        if (state < 0 || state > 3) throw new IllegalArgumentException("Incorrect state value");
        this.state = state;
    }
}
