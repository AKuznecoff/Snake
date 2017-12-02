import java.awt.*;

public class DrawVisitor implements IVisitor {
    private Graphics graphics;
    private int cellSize;
    public DrawVisitor(int size, Graphics g){
        cellSize = size;
        graphics = g;
    }
    @Override
    public void drawObject(Wall wall) {
        Point position = wall.getPosition();
        graphics.setColor(Color.BLUE);
        graphics.fillRect(position.getX() * cellSize, position.getY() * cellSize, cellSize, cellSize);
    }

    @Override
    public void drawObject(Food food) {
        Point position = food.getPosition();
        graphics.setColor(Color.RED);
        graphics.fillOval(position.getX() * cellSize, position.getY() * cellSize, cellSize, cellSize);
    }
}
