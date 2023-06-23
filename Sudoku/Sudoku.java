import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import BackBone.Difficulty;
import Interfaces.Round;
import Interfaces.Menu;

public class Sudoku extends JFrame {
    private static final Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
    private JPanel panel;

    public Sudoku() {
        super("Sudoku");
        setSize(675, 750);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new Menu(font);
        add(panel);
        setVisible(true);
    }

    private Thread switchInterface = new Thread(() -> {
        while (true) {
            if (panel instanceof Menu) {
                Menu menuPanel = (Menu) panel;
                if (menuPanel.changeFrame()) {
                    Difficulty challenge = menuPanel.radioPanel.getDifficulty();
                    panel = new Round(challenge);
                    setContentPane(panel);
                    revalidate();
                    repaint();
                    pack();
                }
            } else if (panel instanceof Round) {
                Round roundPanel = (Round) panel;
                if (roundPanel.changeFrame()) {
                    panel = new Menu(font);
                    setContentPane(panel);
                    revalidate();
                    repaint();
                    pack();
                }
            }
            try {
                Thread.sleep(55);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    public static void main(String[] args) {
        Sudoku game = new Sudoku();
        game.switchInterface.start();
    }
}
