import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import series.*;

public class MyFrame extends JFrame {
    private Series series = new Liner(0, 0,0);

    private JRadioButton radioLiner = new JRadioButton("Liner");
    private JRadioButton radioExponential = new JRadioButton("Exponential");
    private JFileChooser chooser = new JFileChooser();
    private JTextField[] text = new JTextField[3];
    private JButton button = new JButton("Go");
    private JTextField fileField = new JTextField("output.txt");

    public MyFrame() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        setSize(screenWidth / 3, screenHeight / 3);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(5, 3 ));
        ButtonGroup group = new ButtonGroup();

        container.add(new JLabel("First"));
        container.add(new JLabel("q"));
        container.add(new JLabel("n"));

        for (int i=0; i<3; i++){
            text[i] = new JTextField();
            container.add(text[i]);
        }

        radioLiner.setSelected(true);
        group.add(radioLiner);
        group.add(radioExponential);
        container.add(new JPanel());
        container.add(radioLiner);
        container.add(radioExponential);

        container.add(new JPanel());
        container.add(new JLabel("File path:"));
        container.add(fileField);

        button.addActionListener(new MyEvent());
        container.add(button);
    }

    class MyEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                if (radioLiner.isSelected()) {
                    series = new Liner(0, 0,0);
                }
                else {
                    series = new Exponential(0, 0, 0);
                }

                double x = Double.parseDouble(text[0].getText());
                double y = Double.parseDouble(text[1].getText());
                int n = Integer.parseInt(text[2].getText());

                series.setFirstElem(x);
                series.setQ(y);
                series.setN(n);

                try {
                    PrintWriter out = new PrintWriter(fileField.getText());
                    series.save(out);
                    out.close();
                }
                catch (FileNotFoundException e) {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "File not found!");
                }

                JOptionPane.showMessageDialog(null, series.toString());
            }
            catch (NumberFormatException e) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Wrong input!");
            }
        }
    }
}
