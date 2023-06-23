package Interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import InterfaceObjects.RadioPanel;

public class Menu extends JPanel {
    private Font font;
    private boolean shouldSwap = false;
    public RadioPanel radioPanel;

    public Menu(Font font) {
        this.font = font;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(0xfbf7f5));
        setPreferredSize(new Dimension(675, 750));
        addContentPanel();
        startSwitchThread();
    }

    private void addContentPanel() {
        JPanel contentPanel = createVerticalPanel();

        JLabel titleLabel = createLabel("Sudoku", 45f, JLabel.CENTER_ALIGNMENT);
        JPanel titlePanel = createHorizontalPanel();
        titlePanel.add(Box.createHorizontalGlue());
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createHorizontalGlue());

        contentPanel.add(Box.createVerticalGlue());
        contentPanel.add(titlePanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel difficultyLabel = createLabel("Select a Difficulty", 20f, JLabel.CENTER_ALIGNMENT);
        JPanel difficultyPanel = createHorizontalPanel();
        difficultyPanel.add(Box.createHorizontalGlue());
        difficultyPanel.add(difficultyLabel);
        difficultyPanel.add(Box.createHorizontalGlue());

        contentPanel.add(difficultyPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        radioPanel = new RadioPanel(font);
        JPanel radioPanelContainer = createHorizontalPanel();
        radioPanelContainer.add(Box.createHorizontalGlue());
        radioPanelContainer.add(radioPanel);
        radioPanelContainer.add(Box.createHorizontalGlue());

        contentPanel.add(radioPanelContainer);
        contentPanel.add(Box.createVerticalGlue());

        JPanel centerPanel = createVerticalPanel();
        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(contentPanel);
        centerPanel.add(Box.createVerticalGlue());

        add(centerPanel);
    }

    private JPanel createVerticalPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xfbf7f5));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    private JLabel createLabel(String text, float fontSize, float alignmentX) {
        JLabel label = new JLabel(text);
        Font labelFont = font.deriveFont(fontSize);
        label.setFont(labelFont);
        label.setAlignmentX(alignmentX);
        return label;
    }

    private JPanel createHorizontalPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xfbf7f5));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        return panel;
    }

    private void startSwitchThread() {
        Thread switchThread = new Thread(() -> {
            while (true) {
                if (radioPanel.getDifficulty() != null) {
                    shouldSwap = true;
                    break;
                }
                try {
                    Thread.sleep(55);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        switchThread.start();
    }

    public boolean changeFrame() {
        return shouldSwap;
    }
}