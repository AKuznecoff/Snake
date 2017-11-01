import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {
    /*public Game(){
        JFrame frame = new JFrame("GAME");
        frame.setSize(100, 10);
        frame.setContentPane(this);
        frame.setVisible(true);
    }*/

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        g.setColor(Color.RED);
        g.fillRect(10, 10, 10, 10);
    }
}
