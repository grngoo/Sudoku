package InterfaceObjects;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.JLabel;

public class GridInputListener implements KeyListener {
    private JTextField textField;
    private int row, col;

    public GridInputListener(JTextField textField, int row, int col) {
        this.textField = textField;
        this.row = row;
        this.col = col;
    }

    @Override
    public void keyTyped(KeyEvent input) {
        char c = input.getKeyChar();
        if (!(Character.isDigit(c) && c >= '1' && c <= '9') || textField.getText().length() >= 1) {
            input.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent input) {
        GridPanel gridPanel = (GridPanel) textField.getParent();
        if (input.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                int num = Integer.valueOf(textField.getText());
                if (gridPanel.sudokuGrid.validMove(row, col, num)) {
                    JLabel label = gridPanel.createLabel(row, col);
                    label.setText(textField.getText());
                    int index = row * 9 + col;
                    gridPanel.remove(textField);
                    gridPanel.add(label, index);
                    gridPanel.revalidate();
                    gridPanel.repaint();
                    gridPanel.sudokuGrid.setBox(row, col, num);
                } else {
                    textField.setText("");
                }
            } catch (NumberFormatException e) {}
        }
    }

    @Override
    public void keyReleased(KeyEvent input) {}
}