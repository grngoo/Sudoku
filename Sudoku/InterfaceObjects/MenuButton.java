package InterfaceObjects;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MenuButton extends JButton {
    private boolean pressed;

    public MenuButton() {
        super("MENU");
        pressed = false;
        setBackground(new Color(0xd2d4c8));
        addListener();
    }


    private void addListener() {
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pressed = true;
            }
        });
    }

    public boolean checkPressed(){
        return pressed;
    }
}
