import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

import myFrame.*;

public class Lab9
{
    public static void main(String[] args) 
    {
        MyFrame frame = new MyFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(1000, 200, 500, 500);
        frame.isResizable();
        frame.setTitle(" Series ");
        frame.setVisible(true);

        /*
        PrintWriter out = new PrintWriter(new File("output.txt"));

        Liner a = new Liner(1., 0.5);
        Exponential b = new Exponential(1., 2.);

        a.write(out, 4);
        b.write(out, 4);

        out.close();
*/
    }
}
