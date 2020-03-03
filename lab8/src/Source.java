import java.util.*;
import tree.*;

public class Source {
    public static void TestInt(Scanner in) {
        System.out.println("Menu:\n1) add\n2) find\n3) rootLeftRight\n4) leftRightRoot\n5) leftRootRight\n6) close");

        Tree<Integer> myTree = new Tree<>();

        int c = in.nextInt();
        int curr;
        while (c != 6)
        {
            switch(c)
            {
                case (1):
                    curr = in.nextInt();
                    myTree.add(curr);
                    break;
                case (2):
                    curr = in.nextInt();
                    System.out.println(myTree.find(curr));
                    break;
                case (3):
                    myTree.rootLeftRight();
                    System.out.println();
                    break;
                case (4):
                    myTree.leftRightRoot();
                    System.out.println();
                    break;
                case (5):
                    myTree.leftRootRight();
                    System.out.println();
                    break;
            }

            c = in.nextInt();
        }
    }

    public static void TestMyClass(Scanner in) {
        System.out.println("Menu:\n1) add\n2) find\n3) rootLeftRight\n4) leftRightRoot\n5) leftRootRight\n6) close");

        Tree<MyClass> myTree = new Tree<>();

        int c = in.nextInt();
        String curr;
        while (c != 6)
        {
            switch(c)
            {
                case (1):
                    curr = in.next();
                    myTree.add(new MyClass(curr));
                    break;
                case (2):
                    curr = in.next();
                    System.out.println(myTree.find(new MyClass(curr)));
                    break;
                case (3):
                    myTree.rootLeftRight();
                    System.out.println();
                    break;
                case (4):
                    myTree.leftRightRoot();
                    System.out.println();
                    break;
                case (5):
                    myTree.leftRootRight();
                    System.out.println();
                    break;
            }

            c = in.nextInt();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String c = in.next();

        if (c.equals("1"))
            TestInt(in);
        else
            TestMyClass(in);
    }
}