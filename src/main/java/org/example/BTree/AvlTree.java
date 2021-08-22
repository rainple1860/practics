package org.example.BTree;

import java.util.Stack;

/**
 * @className: BTree
 * @description:
 * @author: rainple
 * @create: 2021-08-07 21:15
 **/
public class AvlTree<T extends Comparable<T>> {

    private Node<T> root;
    private int size;

    public void add(T data) {
        if (root == null) {
            root = new Node<>(data,null);
            size++;
            return;
        }
        Node<T> c = root;
        while (true) {
            if (c.data.compareTo(data) > 0) {
                if (c.left == null) {
                    c.left = new Node<>(data,c);
                    break;
                } else {
                    c = c.left;
                }
            } else {
                if (c.right == null) {
                    c.right = new Node<>(data,c);
                    break;
                } else  {
                    c = c.right;
                }
            }
        }
        size++;
        rebuild(c);
    }

    private void rebuild(Node<T> root) {
        if (root == null || root.parent == null) {
            return;
        }
        int factor = getHeightFactor(root.parent);
        if (factor == 2) {
            Node<T> g = root.parent.parent;
            if (root.parent.right == root) {
                leftRotate(root.parent);
            }
            rightRotate(g);

        } else if (factor == -2) {
            Node<T> g = root.parent.parent;
            if (root.parent.left == root) {
                rightRotate(root.parent);
            }
            leftRotate(g);
        }
    }

    private int getHeightFactor(Node<T> root) {
        if (root == null) {
            return 0;
        }
        int l = getHeight(root.left);
        int r = getHeight(root.right);
        return l - r;
    }

    private int getHeight(Node<T> root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(root.left),getHeight(root.right));
    }

    private void rightRotate(Node<T> root) {
        if (root == null) {
            return;
        }
        Node<T> g = root.parent;
        Node<T> l = root.left;
        resetChild(g,root,l);
        root.parent = l;
        root.left = l.right;
        l.right = root;
    }

    private void leftRotate(Node<T> root) {
        if (root == null) {
            return;
        }
        Node<T> r = root.right;
        resetChild(root.parent,root,r);
        root.parent = r;
        root.right = r.left;
        r.left = root;
    }

    private void resetChild(Node<T> parent,Node<T> oldNode,Node<T> newNode) {
        if (parent == null) {
            root = newNode;
            root.parent = null;
            return;
        }
        if (parent.left == oldNode) {
            parent.left = newNode;
        } else if (parent.right == oldNode) {
            parent.right = newNode;
        } else {
            return;
        }
        newNode.parent = parent;
    }

    private static class Node<T> {
        private T data;
        private Node<T> left;
        private Node<T> right;
        private Node<T> parent;

        public Node(T data,Node<T> parent) {
            this.data = data;
            this.parent = parent;
        }

        public Node(T data, Node<T> left, Node<T> right,Node<T> parent) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    public void prevOrder() {
        Node<T> p = root;
        Stack<Node<T>> stack = new Stack<>();
        stack.push(p);
        while (p != null && !stack.isEmpty()) {
            p = stack.pop();
            visit(p);
            if (p.right != null) {
                stack.push(p.right);
            }
            if (p.left != null) {
                stack.push(p.left);
            }
        }
    }

    public void inOrder() {
        Node<T> p = root;
        Stack<Node<T>> stack = new Stack<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                visit(p);
                p = p.right;
            }
        }
    }

    public void postOrder() {
        Node<T> p = root;
        Stack<Node<T>> stack = new Stack<>();
        Node<T> visited = null;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.peek();
                if (p.right != null && visited != p.right) {
                    p = p.right;
                } else {
                    p = stack.pop();
                    visit(p);
                    visited = p;
                    p = null;
                }
            }
        }
    }


    private void visit(Node<T> node) {
        System.out.println("遍历节点为："+node.data);
    }

}
