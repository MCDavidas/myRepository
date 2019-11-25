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
            add(root, null, curr);
    }

    private void add(Node v, Node prev, T curr)
    {
        if (v == null)
        {
            Node u = new Node(curr);
            if (prev.value.compareTo(curr) > 0)
                prev.left = u;
            else
                prev.right = u;
            u.parent = prev;
            return;
        }

        if (v.value.compareTo(curr) > 0)
            add(v.left, v, curr);
        else
            add(v.right, v, curr);
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

        leftRightRoot(v.left);
        leftRightRoot(v.right);
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

        leftRootRight(v.left);
        System.out.print(v.value + " ");
        leftRootRight(v.right);
    }
}
