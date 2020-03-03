import javax.swing.*;
import java.awt.*;

public class First extends JPanel {
    private String[] array1 = {"Q", "W", "E", "R", "T", "Y", "U"};
    private String[] array2 = {"A", "S", "D", "F", "G", "H", "J", "H"};
    private DefaultListModel leftListModel;
    private DefaultListModel rightListModel;
    private JList rightList;
    private JList leftList;
    JPanel central;

    public First() {
        setLayout(new BorderLayout());
        central = new JPanel();
        central.setLayout(new BorderLayout());
        createLeftListModel();
        leftList = new JList(leftListModel);
        leftList.setPreferredSize(new Dimension(60,500));
        leftList.setSelectedIndex(0);
        createRightListModel();
        rightList = new JList(rightListModel);
        rightList.setPreferredSize(new Dimension(60,500));
        rightList.setSelectedIndex(0);
        add(leftList, BorderLayout.WEST);
        add(new JScrollPane(leftList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.WEST);
        add(rightList, BorderLayout.EAST);
        add(new JScrollPane(rightList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.EAST);
        JButton right = createRightButton();
        central.add(right, BorderLayout.NORTH);
        JButton left = createLeftButton();
        central.add(left, BorderLayout.SOUTH);
        this.add(central, BorderLayout.CENTER);
        setVisible(true);
    }

    private JButton createLeftButton() {
        JButton left = new JButton(new ImageIcon("images/left.png"));
        left.setSize(70,70);
        left.addActionListener(e -> {
            if (!rightList.isSelectionEmpty()) {
                leftListModel.addElement(rightList.getSelectedValue());
                rightListModel.remove(rightList.getSelectedIndex());
                if (!rightListModel.isEmpty())
                    rightList.setSelectedIndex(0);
            }
        });
        return left;
    }

    private JButton createRightButton() {
        JButton right = new JButton(new ImageIcon("images/right.png"));
        right.setSize(70,70);
        right.addActionListener(e -> {
            if (!leftList.isSelectionEmpty()) {
                rightListModel.addElement(leftList.getSelectedValue());
                leftListModel.remove(leftList.getSelectedIndex());
                if (!leftListModel.isEmpty())
                    leftList.setSelectedIndex(0);
            }
        });
        return right;
    }

    private void createRightListModel() {
        rightListModel = new DefaultListModel();
        for (String el : array2) {
            rightListModel.addElement(el);
        }
    }

    private void createLeftListModel() {
        leftListModel = new DefaultListModel();
        for (String el : array1) {
            leftListModel.addElement(el);
        }
    }
}
