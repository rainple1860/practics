package org.example.construct.sequencies.list;

/**
 * @className: SingleLinkList
 * @description:
 * @author: rainple
 * @create: 2021-09-13 21:19
 **/
public class SingleLinkList<E> implements List<E>{

    private int size;
    private Node<E> root;
    private Node<E> tail;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(E e) {
        size++;
        if (root == null) {
            root = new Node<>(e);
            tail = root;
            return;
        }
        Node<E> n = new Node<>(e);
        Node<E> t = tail;
        t.next = n;
        tail = n;
    }

    @Override
    public E remove(int index) {
        int i = 0;
        Node<E> c = root;
        Node<E> p = c;
        while (i < index && c != null) {
            p = c;
            c = c.next;
            i++;
        }
        if (i < index || c == null) {
            return null;
        }
        size--;
        if (c == tail) {
            tail = p;
        }
        if (c == root) {
            root = root.next;
        }
        p.next = c.next;
        c.next = null;
        return c.data;
    }

    public void reverse() {
        if (root == null) {
            return;
        }
        Node<E> p = root;
        tail = root;
        Node<E> current = p.next;
        p.next = null;
        Node<E> child;
        while (current != null) {
            child = current.next;
            current.next = p;
            p = current;
            current = child;
        }
        root = p;
    }

    @Override
    public E removeFirst() {
        if (root == null) {
            return null;
        }
        Node<E> r = root;
        root = r.next;
        size--;
        return r.data;
    }

    @Override
    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public E get(int index) {
        Node<E> node = getNode(index);
        return node == null ? null : node.data;
    }

    private Node<E> getNode(int index) {
        Node<E> p = root;
        int i = 0;
        while (i < index && p != null) {
            i++;
            p = p.next;
        }
        return p;
    }

    @Override
    public E getLast() {
        return tail == null ? null : tail.data;
    }

    private int getIndex(Node<E> node) {
        Node<E> p = root;
        int i = -1;
        while (p != null && p != node) {
            p = p.next;
            i++;
        }
        return i;
    }

    private Node<E> getParent(Node<E> node) {
        int index = getIndex(node);
        if (index < 1) {
            return null;
        }
        return getNode(index - 1);

    }

    @Override
    public E getFirst() {
        return get(0);
    }

    @Override
    public String toString() {
        Node<E> p = root;
        StringBuilder builder = new StringBuilder("[");
        while (p != null) {
            builder.append(p.data).append(",");
            p = p.next;
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.append("]").toString();
    }

    private static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

}
