import javax.swing.*;
import java.awt.*;

public class Third extends JPanel {
    public Third() {
        setLayout(new GridLayout(4,1));
        ButtonGroup radioGroup = new ButtonGroup();
        for(int i = 0; i < 2; i++){
            JRadioButton btn = new JRadioButton(new ImageIcon("images/1.png"));
            btn.setPressedIcon(new ImageIcon("images/2.png"));
            btn.setRolloverIcon(new ImageIcon("images/3.png"));
            btn.setSelectedIcon(new ImageIcon("images/4.png"));
            radioGroup.add(btn);
            add(btn);
        }
    }
}
