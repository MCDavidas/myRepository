package tree;

import java.util.*;

public class Tree<T extends Comparable<T>>
{
    private class Node
    {
        public T value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(T curr)
        {
            this.left = null;
            this.right = null;
            this.parent = null;
            value = curr;
        }
    }

    private Node root;

    public Tree()
    {
        root = null;
    }

    public void add(T curr)
    {
        if (root == null)
            root = new Node(curr);
        else
        {
            Node parent = root;
            Node v = root;

            while (v != null)
            {
                parent = v;
                if (parent.value.compareTo(curr) > 0)
                    v = parent.left;
                else
                    v = parent.right;
            }

            v = new Node(curr);
            v.parent = parent;

            if (parent.value.compareTo(curr) > 0)
                parent.left = v;
            else
                parent.right = v;
        }
    }

    public boolean find(T curr)
    {
        if (this.findNode(curr) == null)
            return false;

        return true;
    }

    private Node findNode(T curr)
    {
        Node v = root;

        while (v != null && v.value.compareTo(curr) != 0)
        {
            if (v.value.compareTo(curr) > 0)
                v = v.left;
            else
                v = v.right;
        }

        return v;
    }

    public void rootLeftRight()
    {
        rootLeftRight(root);
    }

    private void rootLeftRight(Node v)
    {
        if (v == null)
            return;

        System.out.print(v.value + " ");
        rootLeftRight(v.left);
        rootLeftRight(v.right);
    }

    public void leftRightRoot()
    {
        leftRightRoot(root);
    }

    private void leftRightRoot(Node v)
    {
        if (v == null)
            return;

        rootLeftRight(v.left);
        rootLeftRight(v.right);
        System.out.print(v.value + " ");
    }

    public void leftRootRight()
    {
        leftRootRight(root);
    }

    private void leftRootRight(Node v)
    {
        if (v == null)
            return;

        rootLeftRight(v.left);
        System.out.print(v.value + " ");
        rootLeftRight(v.right);
    }
}
