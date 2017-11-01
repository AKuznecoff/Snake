import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;
import java.util.Locale;

public class ChoseGameSizesWindow extends JFrame {
    private int height;
    private int width;
    private JTextField heightText = new JTextField(2);
    private JTextField widthText = new JTextField(2);
    private JButton startButton = new JButton("Satrt");
    private JButton cancelButton = new JButton("Cancel");
    private buttonClick clickAction = new buttonClick();
    //private GamePanel game;


    public ChoseGameSizesWindow(){
        super("Snake");
        JPanel panel = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new FlowLayout());
        JLabel lbl = new JLabel("Height");
        //lbl.setLocation(5, 5);
        lbl.setSize(lbl.getPreferredSize());
        panel.add(lbl);
        //heightText.setLocation(lbl.getX() + lbl.getWidth() + 5, lbl.getY());
        heightText.setSize(100, lbl.getHeight());
        heightText.setText("10");
        panel.add(heightText);
        lbl = new JLabel("Width");
        lbl.setSize(lbl.getPreferredSize());
        //lbl.setLocation(5, lbl.getHeight() + 10);
        panel.add(lbl);
        //widthText.setLocation(heightText.getX(), lbl.getY());
        widthText.setSize(100, lbl.getHeight());
        widthText.setText("10");
        panel.add(widthText);
        startButton.setSize(startButton.getPreferredSize());
        //startButton.setLocation(5, lbl.getY() + lbl.getWidth() + 5);
        startButton.addActionListener(clickAction);
        panel.add(startButton);
        cancelButton.setSize(cancelButton.getPreferredSize());
        cancelButton.addActionListener(clickAction);
        panel.add(cancelButton);
        setContentPane(panel);
        setSize(200, 100);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    /*private void startGame(){
        game = new GamePanel(new Board(height, width), this);
        setVisible(false);
    }*/

    class buttonClick implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            try {
                if (e.getSource() == startButton) {
                    height = Integer.parseInt(heightText.getText());
                    width = Integer.parseInt(widthText.getText());
                    //startGame();
                    //new GamePanel(new Board(height, width)).setVisible(true);
                    add(new Game());
                } else{
                    System.exit(0);
                }
            }
            catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Height and width must be integer");
            }

        }
    }
}
