import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Source {
    public static void main(String[] args) {
        ArrayList<Toy> container = new ArrayList<>();

        readToys(container, "input.txt");

        int leftBorder = 600;
        int rightBorder = -100;

        container.sort(new Comparator<Toy>() {
            @Override
            public int compare(Toy t1, Toy t2) {
                return t1.getPrice() - t2.getPrice();
            }
        });

        container.stream()
                .filter(p-> { return p.getMinYear() <= leftBorder && p.getMaxYear() >= rightBorder; })
                .forEach(System.out::println);

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new MyFrame();
                frame.setTitle("MyFrame");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

    public static void readToys(ArrayList<Toy> toys, String file) {
        try {
            Scanner scanner = new Scanner(new File(file));

            while (scanner.hasNext()) {
                toys.add(Toy.read(scanner));
            }
        } catch (FileNotFoundException e) {
            System.out.println(String.format("Can not find file %s\n", file));
        }
    }
}
