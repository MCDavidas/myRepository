import java.util.*;
import tree.*;


public class Lab8
{
    public static void main(String[] args)
    {
        Tree<Integer> myTree = new Tree<Integer>();

        System.out.println("Menu:\n1) add\n2) find\n3) rootLeftRight\n4) leftRightRoot\n5) leftRootRight\n6) close");

        Scanner in = new Scanner(System.in);
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
}
