package Interfaces;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import BackBone.Difficulty;
import InterfaceObjects.GridPanel;
import InterfaceObjects.SubPanel;

import javax.swing.BoxLayout;

public class Round extends JPanel{
    private GridPanel gridPanel;
    private SubPanel subPanel;
    private JPanel parent;
    private boolean swap = false;

    public Round(Difficulty challenge) {
        setPreferredSize(new Dimension(675, 750));
        setBackground(new Color(0xfbf7f5));
        
        parent = new JPanel();
        parent.setLayout(new BoxLayout(parent, BoxLayout.Y_AXIS));

        gridPanel = new GridPanel(challenge);
        subPanel = new SubPanel(challenge);

        parent.add(gridPanel);
        parent.add(subPanel);
        
        add(parent);
        setVisible(true);

        switchThread.start();
    }

    private Thread switchThread = new Thread(() -> {
        while (true) {
            if (gridPanel.sudokuGrid.gameOver() || subPanel.menuButton.checkPressed()) {
                swap = true;
                break;
            }
            try {
                Thread.sleep(55);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    public boolean changeFrame() {
        return swap;
    }
}