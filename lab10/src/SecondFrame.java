import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class SecondFrame extends JFrame {
    private JButton button1 = new JButton("Puppys");
    private JButton button2 = new JButton("Kittys");
    private JLabel label = new JLabel("Puppys or kittys?",2);

    public SecondFrame() {
        Random random = new Random(System.currentTimeMillis());
        JPanel panel = new JPanel();
        panel.setLayout(null);
        setContentPane(panel);

        button1.setSize(120, 30);
        button2.setSize(120, 30);
        button1.setLocation(120, 150);
        button2.setLocation(300, 150);
        panel.add(button1);
        panel.add(button2);

        panel.add(label);
        label.setSize(150, 30);
        label.setLocation(200, 50);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                button1.setLocation(random.nextInt(300), random.nextInt(200));
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Good answer!",
                        "", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }
}