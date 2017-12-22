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
    static Board board;
    static int level;
    public static void main(String[] args) {
        //board = new Board(20, 20, 1);
        level = 1;
        initBoard();
        JFrame frame = new JFrame("GAME");
        tuneFrame(frame);
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!board.checkFinished()) {
                    board.nextStep();
                    if (board.getScores() > 20) {
                        if (level < 2)
                            level++;
                        initBoard();
                        tuneFrame(frame);
                    }
                    frame.repaint();
                }
            }
        });

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                switch(e.getKeyChar()) {
                    case 'a':
                        board.setDirection(Directions.LEFT);
                        break;
                    case 'w':
                        board.setDirection(Directions.UP);
                        break;
                    case 'd':
                        board.setDirection(Directions.RIGHT);
                        break;
                    case 's':
                        board.setDirection(Directions.DOWN);
                        break;
                    case 'l':
                        if(!board.checkPause())
                            timer.stop();
                        board = load(board, "save.ser");
                        tuneFrame(frame);
                        //frame.repaint();
                        break;
                    case 'k':
                        if (!board.checkPause()) {
                            board.pause();
                            timer.stop();
                        }
                        save(board);
                        board.pause();
                        timer.start();
                        break;
                    case ' ':
                        board.pause();
                        if (board.checkPause()) {
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


        /*Game game = new Game();
        frame.add(game);
        frame.setSize(300, 300);
        frame.setVisible(true);*/
        /*JFrame frame = new JFrame("Snake");
        GamePanel game = new GamePanel(new Board(10, 10), null);
        Game p = new Game();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.add(game);
        frame.setVisible(true);
        frame.setSize(game.getSize());*/
    }

    static void initBoard() {
        board = load(null, "levels/" + level + ".ser");
    }

    static void tuneFrame(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GamePanel(board));
        frame.pack();
        JLabel lbl = new JLabel(String.format("Scores: %s", board.getScores()));
        frame.add(lbl);
        frame.setVisible(true);
    }

    static void save(Board board){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("save.ser"))){
            out.writeObject(board);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static Board load(Board oldBoard, String name){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(name))){
            return (Board) in.readObject();
        } catch (FileNotFoundException e) {
            return oldBoard;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
