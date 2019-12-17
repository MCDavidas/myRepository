import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
    private final JTextField[] text = new JTextField[4];
    private final JButton button = new JButton("Go");
    private final JTextField fileField = new JTextField("output.txt");

    public MyFrame() {
        setSize(600, 400);
        //setLocationByPlatform(true);

        Container container = this.getContentPane();
        GridLayout layout = new GridLayout(4, 4);
        layout.
        container.setLayout(layout);

        for (int i=0; i<4; i++){
            text[i] = new JTextField();
            container.add(text[i]);
        }

        container.add(new JLabel("File path:"));
        container.add(fileField);

        button.addActionListener(new MyEvent());
        container.add(button);
    }

    class MyEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

        }
    }
}