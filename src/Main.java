import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board b = new Board(10, 10, 1);
        JFrame frame = new JFrame("GAME");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GamePanel(b));
        frame.pack();
        frame.setVisible(true);
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!b.checkFinished()) {
                    b.nextStep();
                    frame.repaint();
                }
            }
        });
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                switch(e.getKeyChar()) {
                    case 'a':
                        b.setDirection(Directions.LEFT);
                        break;
                    case 'w':
                        b.setDirection(Directions.UP);
                        break;
                    case 'd':
                        b.setDirection(Directions.RIGHT);
                        break;
                    case 's':
                        b.setDirection(Directions.DOWN);
                        break;
                    case ' ':
                        b.pause();
                        if (b.checkPause()) {
                            timer.stop();
                        } else {
                            timer.start();
                        }
                        break;
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        JLabel lbl = new JLabel(String.format("Scores: %s", b.getScores()));
        frame.add(lbl);
    }

    public static void print(Board b) {
        //StringBuilder builder = new StringBuilder();
        for (Iterator<SnakeSegment> i = b.getSnake().iterator(); i.hasNext(); ) {
            SnakeSegment snakeSegment = i.next();
            System.out.print(String.format("(%s, %d) ", snakeSegment.getPosition().getX(), snakeSegment.getPosition().getY()));

        }
        System.out.println();
        //System.out.println(String.format("(%s, %d)", b.getFood().getX(), b.getFood().getY()));
    }
}
