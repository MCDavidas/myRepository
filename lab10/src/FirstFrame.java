import javax.swing.*;
import java.awt.event.*;

public class FirstFrame extends JFrame {
    private JButton button = new JButton("Button");
    private JTextField tField = new JTextField("", 1);

    public FirstFrame() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        button.setSize(100, 30);
        button.setLocation(200, 135);
        panel.add(button);
        setContentPane(panel);

        panel.add(tField);
        tField.setLocation(420, 250);
        tField.setSize(80, 30);

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent me) {
                tField.setText("X=" + me.getX() + " Y=" + me.getY());
            }
        });

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                button.setLocation(e.getX(), e.getY());
            }
        });

        button.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    if (button.getText().length() > 0) {
                        String str = button.getText();
                        button.setText(str.substring(0, str.length() - 1));
                    }
                } else
                    button.setText(button.getText() + e.getKeyChar());
            }
        });

        MouseAdapter mouseListener = new MouseDrag();
        button.addMouseListener(mouseListener);
        button.addMouseMotionListener(mouseListener);
    }

    class MouseDrag extends MouseAdapter {
        private int X, Y;

        public void mousePressed(MouseEvent e) {
            X = e.getX();
            Y = e.getY();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if ((e.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) != 0) {
                int dx = e.getX() - X;
                int dy = e.getY() - Y;
                button.setLocation(button.getX() + dx, button.getY() + dy);
            }
        }
    }
}