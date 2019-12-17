import javax.swing.*;
import java.util.Scanner;

public class Source {
    public static void main(String[] args) {
        JFrame frame;

        Scanner in = new Scanner(System.in);
        if (in.next().equals("1"))
            frame = new FirstFrame();
        else
            frame = new SecondFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100,100,500,300);
        frame.setResizable(false);
        frame.setTitle("lab10");
        frame.setVisible(true);
    }
}