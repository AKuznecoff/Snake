import java.awt.*;
import java.awt.image.BufferedImage;

public interface Visitor {
    public void drawWall(Graphics g, Point position, Wall wa);
    public void drawFood();
}
