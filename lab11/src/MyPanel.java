import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyPanel extends JFrame {
    private List<Line> lines;

    private MyCanvas drawPanel;
    private JButton colorChoose;
    private JFileChooser fileChooser = new JFileChooser();
    private JScrollPane scrollPane;

    public MyPanel() {
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
            setSize(700, 500);
            MyPanel.this.setLocationRelativeTo(null);
            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentHidden(ComponentEvent e) {
                    System.exit(0);
                }
            });
        });
    }

    @Override
    protected JRootPane createRootPane() {
        JRootPane pane = new JRootPane();
        JPanel panel = new JPanel();
        pane.setContentPane(panel);
        panel.setLayout(new GridBagLayout());
        JPanel radioPanel = createButtonPanel();
        panel.add(radioPanel, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTH,
                0, new Insets(0, 0, 0, 0), 0, 0));
        lines = new ArrayList<>();
        createDrawPanel();
        createScrollPane(panel);

        return pane;
    }

    private void createScrollPane(JPanel panel) {
        scrollPane = new JScrollPane(drawPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panel.add(scrollPane, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.SOUTH,
                1, new Insets(0, 0, 0, 0), 0, 0));
    }


    private void createDrawPanel() {
        drawPanel = new MyCanvas(lines);
        drawPanel.addPaintListener(e -> {
            SwingUtilities.invokeLater(() -> scrollPane.getHorizontalScrollBar().setValue((int) e.getX()));
            SwingUtilities.invokeLater(() -> scrollPane.getVerticalScrollBar().setValue((int) e.getY()));
        });
    }

    private JPanel createButtonPanel() {
        JPanel radioPanel = new JPanel(new GridLayout(1, 4));
        colorChoose = new JButton("Color");
        colorChoose.addActionListener(e -> {
            Color tempColor = JColorChooser.showDialog(this, "Choose a color", drawPanel.getColor());
            if (tempColor != null)
                drawPanel.setColor(tempColor);
        });
        JButton save = createSaveButton();
        radioPanel.add(save);
        radioPanel.add(colorChoose);
        return radioPanel;
    }

    private JButton createSaveButton() {
        JButton save = new JButton("Save");
        save.addActionListener(e -> {
            fileChooser.setDialogTitle("Сохранение файла");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION)
                try {
                    ImageIO.write(drawPanel.getImage(), "png", fileChooser.getSelectedFile());
                    JOptionPane.showMessageDialog(this,
                            "Файл '" + fileChooser.getSelectedFile() +
                                    " ) сохранен");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, ex, "Error!", JOptionPane.PLAIN_MESSAGE);
                }
        });
        return save;
    }
}


