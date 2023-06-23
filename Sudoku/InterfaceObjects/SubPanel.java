package InterfaceObjects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BackBone.Difficulty;

public class SubPanel extends JPanel {
    private final Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 25);
    private final Insets padding = new Insets(5, 10, 5, 10);
    
    private GridBagConstraints constraints;
    private TimerLabel timerLabel;
    private JLabel difficultyLabel;
    public MenuButton menuButton;

    public SubPanel(Difficulty challenge) {
        setPreferredSize(new Dimension(500, 75));
        setBackground(new Color(0xe8e3e1));
        setBorder(BorderFactory.createLineBorder(Color.black, 1));
        setLayout(new GridBagLayout());

        constraints = new GridBagConstraints();

        addTimer();
        addDifficultyLabel(challenge);
        addMenuButton();
    }

    private void addTimer() {
        timerLabel = new TimerLabel();
        timerLabel.setFont(font);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.0;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = padding;
        add(timerLabel, constraints);
        timerLabel.startTimer();
    }

    private void addDifficultyLabel(Difficulty challenge) {
        difficultyLabel = new JLabel(String.valueOf(challenge));
        difficultyLabel.setFont(font);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = padding;
        add(difficultyLabel, constraints);
    }

    private void addMenuButton() {
        menuButton = new MenuButton();
        menuButton.setFont(font);
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.weightx = 0.0;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = padding;
        add(menuButton, constraints);
    }
}
