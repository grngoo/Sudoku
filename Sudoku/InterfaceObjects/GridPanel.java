package InterfaceObjects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import BackBone.Difficulty;
import BackBone.GameGrid;

import javax.swing.JComponent;

public class GridPanel extends JPanel {
    private static final int SIZE = 9;
    private static final int BOX_SIZE = 75;
    private static final int FONT_SIZE = 25;
    private final Font font = new Font(Font.SANS_SERIF, Font.PLAIN, FONT_SIZE);

    public GameGrid sudokuGrid;

    public GridPanel(Difficulty challenge) {
        super(new GridLayout(SIZE, SIZE));
        sudokuGrid = new GameGrid(challenge);
        initialiseGrid();
    }

    private void initialiseGrid() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (sudokuGrid.getGrid()[i][j] == -1) {
                    JTextField textField = createTextField(i, j);
                    add(textField);
                } else {
                    JLabel label = createLabel(i, j);
                    add(label);
                }
            }
        }
    }

    public JTextField createTextField(int row, int col) {
        JTextField textField = new JTextField();
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setPreferredSize(new Dimension(BOX_SIZE, BOX_SIZE));
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textField.setFont(font);
        textField.addKeyListener(new GridInputListener(textField, row, col));
        addBorder(row, col, textField);
        return textField;
    }

    public JLabel createLabel(int row, int col) {
        JLabel label = new JLabel(String.valueOf(sudokuGrid.getGrid()[row][col]));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(BOX_SIZE, BOX_SIZE));
        label.setFont(font);
        addBorder(row, col, label);
        return label;
    }

    private void addBorder(int row, int col, JComponent jcomp) {
        if ((row == 2 || row == 5) && (col == 2 || col == 5)) {
            jcomp.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.black));
        } else if (row == 2 || row == 5) {
            jcomp.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 1, Color.black));
        } else if (col == 2 || col == 5) {
            jcomp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 3, Color.black));
        } else {
            jcomp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        }
    }
}