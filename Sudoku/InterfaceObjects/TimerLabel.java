package InterfaceObjects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

class TimerLabel extends JLabel {
    private int minutes, seconds;
    private Timer timer;
    private String time = "00:00";

    public TimerLabel() {
        setText(time);
    }

    public String getTime() {
        return time;
    }

    public void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                if (seconds >= 60) {
                    seconds = 0;
                    minutes++;
                }
                time = String.format("%02d:%02d", minutes, seconds);
                setText(time);
            }
        });
        timer.start();
    }

    public void stopTimer() {
        if (timer != null) {
            timer.stop();
        }
    }
}
