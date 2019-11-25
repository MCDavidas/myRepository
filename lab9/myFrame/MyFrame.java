package myFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import series.*;

class MyFrame extends JFrame 
{
    Series a = new Liner(1, 0.5);

    public MyFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(10, 10, 2, 2));
        container.add(label);
        container.add(inputFileName);
        container.add(labelStep);
        container.add(inputStep);
        container.add(labelN);
        container.add(inputN);
        container.add(labelFirst);
        container.add(inputFirst);

        ButtonGroup group = new ButtonGroup();
        group.add(radioLiner);
        group.add(radioExponential);
        radioExponential.addActionListener(new ButtonEventListener(new Exponential(s.n, s.step, s.first)));
        radioLiner.addActionListener(new ButtonEventListener(new Liner(s.n, s.step, s.first)));

        container.add(radioLiner);
        container.add(radioExponential);
        radioExponential.setSelected(true);
        button.addActionListener(new ButtonEventListener(getSeries(s.n, s.step, s.first)));
        container.add(button);
    }

    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;
    private JButton button = new JButton("OK");
    private JTextField inputFileName = new JTextField("", 1);
    private JLabel label = new JLabel("Enter file name");

    private JTextField inputStep = new JTextField("", 1);
    private JLabel labelStep = new JLabel("Enter step");

    private JTextField inputN = new JTextField("", 1);
    private JLabel labelN = new JLabel("Enter N");

    private JTextField inputFirst = new JTextField("", 1);
    private JLabel labelFirst = new JLabel("Enter first:");

    private JRadioButton radioLiner = new JRadioButton("Liner");
    private JRadioButton radioExponential = new JRadioButton("Exponential");

    private Series getSeries(int n, int _step, int _first) {
        return radioLiner.isSelected() ? new Liner(n, _first, _step) : new Exponential(n, _first, _step);
    }

    class ButtonEventListener implements ActionListener {
        private Series series;

        ButtonEventListener(Series _s) {
            series = _s;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            series.setFileName(inputFileName.getText());
            String fileName = inputFileName.getText();
            series.setStep(Integer.parseInt(inputStep.getText()));
            series.setN(Integer.parseInt(inputN.getText()));
            series.setFirst(Integer.parseInt(inputFirst.getText()));
            series = getSeries(series.n, series.step, series.first);
            series.setFileName(fileName);
            String message = " ";
            for (int i = 1; i <= series.n; i++) {
                message += series.countElem(i) + " ";
            }
            series.countSum();
            try {
                series.saveToFile(series.getFileName(), message);
                series.saveToFile(series.getFileName(), "  sum: " + series.getSum() + "\n");
            }
            catch (IOException exc){
                JOptionPane.showMessageDialog(null, "file not exists", "Output", JOptionPane.PLAIN_MESSAGE);
            }
            JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
