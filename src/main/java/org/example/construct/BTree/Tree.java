package org.example.construct.BTree;

import java.util.*;

/**
 * @className: Tree
 * @description:
 * @author: rainple
 * @create: 2021-06-30 14:11
 **/
public class Tree<T extends Comparable<T>> {

    private Node<T> root;
    private int size;

    public void add(T data) {
        Node<T> n = new Node<>(data);
        if (root == null) {
            root = n;
            size++;
            return;
        }
        Node<T> c = root;
        while (true) {
            if (c.data.compareTo(data) > 0) {
                if (c.left == null) {
                    c.left = n;
                    break;
                } else {
                    c = c.left;
                }
            } else {
                if (c.right == null) {
                    c.right = n;
                    break;
                } else  {
                    c = c.right;
                }
            }
        }
        size++;
    }

    public int size() {
        return size;
    }

    public void print(T target) {
        print(root,target);
    }


    private Node<T> print(Node<T> node,T target) {
        if (node != null) {
            if (target.equals(node.data)) {
                return node;
            }
            Node<T> n = print(node.left, target);
            if (n != null) {
                System.out.println("当前节点：" + node.data);
                return n;
            }
            n = print(node.right, target);
            if (n != null) {
                System.out.println("当前节点：" + node.data);
                return n;
            }
        }
        return null;
    }

    //非递归中序遍历
    public void inOrder() {
        Stack<Node<T>> stack = new Stack<>();
        Node<T> p = root;
        List<T> data = new ArrayList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                Node<T> pop = stack.pop();
                data.add(pop.data);
                p = pop.right;
            }
        }
        System.out.println("非诋毁中序遍历结果：" + data.toString());
    }

    //前序遍历
    public void preOrder() {
        Stack<Node<T>> stack = new Stack<>();
        Node<T> p = root;
        List<T> data = new ArrayList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                data.add(p.data);
                stack.push(p);
                p = p.left;
            } else {
                Node<T> pop = stack.pop();
                p = pop.right;
            }
        }
        System.out.println("非递归前序遍历结果：" + data.toString());
    }

    public void postOrder() {
        Stack<Node<T>> stack = new Stack<>();
        Node<T> p = root;
        List<T> data = new ArrayList<>();
        Node<T> prev = null;
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            if (!stack.isEmpty()) {
                Node<T> node = stack.peek();
                if (node.right == null || node.right == prev) {
                    node = stack.pop();
                    prev = node;
                    data.add(node.data);
                    p = null;
                } else {
                    p = node.right;
                }
            }
        }
        System.out.println("非递归后序遍历结果：" + data.toString());
    }

    public void lineOrder() {
        Node<T> node = this.root;
        Queue<Node<T>> queue = new ArrayDeque<>();
        List<T> data = new ArrayList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            data.add(node.data);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        System.out.println("按行遍历结果：" + data.toString());
    }

    public int getDepth() {
        return doDepth(root);
    }

    private int doDepth(Node<T> node) {
        if (node == null) {
            return 0;
        }
        int left = doDepth(node.left);
        int right = doDepth(node.right);
        return 1 + Math.max(left,right);
    }

    public void postOrderIter() {
        List<T> data = new ArrayList<>();
        doPostOrderIter(root,data);
        System.out.println("递归后续遍历结果：" + data.toString());
    }

    public void preOrderIter() {
        List<T> data = new ArrayList<>();
        doPreOrderIter(root,data);
        System.out.println("递归前序遍历结果：" + data.toString());
    }

    public void inOrderIter() {
        List<T> data = new ArrayList<>();
        doInOrderIter(root,data);
        System.out.println("递归中序遍历结果：" + data.toString());
    }

    private void doPostOrderIter(Node<T> node, List<T> data) {
        if (node != null) {
            doPostOrderIter(node.left,data);
            doPostOrderIter(node.right,data);
            data.add(node.data);
        }
    }

    private void doPreOrderIter(Node<T> node, List<T> data) {
        if (node != null) {
            data.add(node.data);
            doPreOrderIter(node.left,data);
            doPreOrderIter(node.right,data);
        }
    }

    private void doInOrderIter(Node<T> node, List<T> data) {
        if (node != null) {
            doInOrderIter(node.left,data);
            data.add(node.data);
            doInOrderIter(node.right,data);
        }
    }

    private static class Node<T> {
        private T data;
        private Node<T> left;
        private Node<T> right;

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node<T> left, Node<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

}
