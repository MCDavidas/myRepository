import java.awt.*;
import java.io.*;
import javax.swing.*;

public class Lab9 {
    public static void main(String[] args) throws FileNotFoundException {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new MyFrame();
                frame.setTitle("MyFrame");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });
    }
}