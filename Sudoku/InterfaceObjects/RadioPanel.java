package InterfaceObjects;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import BackBone.Difficulty;

public class RadioPanel extends JPanel {
    private Font font;
    private ButtonGroup difficultyGroup;
    private Difficulty challenge;

    public RadioPanel(Font font) {
        this.font = font;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        difficultyGroup = new ButtonGroup();
        addToGroup();
    }

    private void addToGroup() {
        for (Difficulty challenge : Difficulty.values()){
            JRadioButton radioButton = new JRadioButton(String.valueOf(challenge));
            radioListener(radioButton);
            radioButton.setFont(font);
            difficultyGroup.add(radioButton);
            add(radioButton);
        }
    }

    private void radioListener(JRadioButton radioButton) {
        radioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButton source = (JRadioButton) e.getSource();
                if (source.isSelected()) {
                    challenge = Difficulty.valueOf(source.getText());
                }
            }
        });
    }

    public Difficulty getDifficulty() {
        return challenge;
    }
}
