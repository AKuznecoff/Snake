//import com.sun.corba.se.impl.oa.poa.ActiveObjectMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Queue;

public class GamePanel extends JPanel {
    private Board board;
    private final int CELL_SIZE = 30;
    private int width, height;

    public GamePanel(Board board)
    {
        this.board = board;
        width = board.getWidth();
        height = board.getHeight() + 1;
    }

    public Dimension getPreferredSize() {
        return new Dimension(width * CELL_SIZE, height * CELL_SIZE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        HashMap<Point, GameObject> gameObjects = board.getGameObjects();
        DrawVisitor visitor = new DrawVisitor(CELL_SIZE, g);
        for (GameObject object: gameObjects.values()) {
            object.accept(visitor);
        }

        for (SnakeSegment segment: board.getSnake()) {
            Point position = segment.getPosition();
            g.setColor(Color.GREEN);
            g.fillOval(position.getX() * CELL_SIZE, position.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
        g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 26));
        g.setColor(Color.BLACK);
        if (board.checkFinished()) {
            g.drawString("GAME OVER", (width - 5) * CELL_SIZE / 2, height * CELL_SIZE / 2);
        }
        if (board.checkPause()) {
            g.drawString("PAUSE", (width - 2) * CELL_SIZE / 2, height * CELL_SIZE / 2);
        }
        g.drawString(String.format("Score: %s", board.getScores()), 5, height * CELL_SIZE);
    }
}