package org.example.construct.sequencies.list;

/**
 * @className: CircleSingleLinkList
 * @description: 循环单链列表
 * @author: rainple
 * @create: 2021-09-14 15:02
 **/
public class CircleSingleLinkList<E> implements List<E> {

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
        Node<E> node = new Node<>(e);
        if (root == null) {
            root = tail = node;
            root.next = tail;
            tail.prev = root;
            return;
        }
        node.next = root;
        node.prev = tail;
        tail.next = node;
        tail = node;
        root.prev = node;
    }

    //将当前的包含字母的链表拆分为分别只包含小写和只包含大写的链表
    public void split(CircleSingleLinkList<Character> upper,CircleSingleLinkList<Character> lower) {
        Node<E> c = root;
        if (c == null) {
            return;
        }
        do {
            Character character = (Character) c.data;
            if (character >= 'A' && character <= 'Z') {
                upper.add(character);
            } else {
                lower.add(character);
            }
            c = c.next;
        } while (c != root);
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public E get(int index) {
        int i = 0;
        Node<E> c = root;
        while (i < index) {
            c = c.next;
            if (c == root) {
                break;
            }
            i++;
        }
        if (c != root) {
            return c.data;
        }
        return null;
    }

    private boolean isEmpty() {
        return root == null;
    }

    public E getEle(E e) {
        if (isEmpty()) {
            return null;
        }
        if (root.data == e) {
            return root.data;
        }
        Node<E> c = root.next;
        while (c != root) {
            if (c.data == e) {
                return c.data;
            }
            c = c.next;
        }
        return null;
    }

    @Override
    public E getLast() {
        return tail == null ? null : tail.data;
    }

    @Override
    public E getFirst() {
        return root == null ? null : root.data;
    }

    @Override
    public String toString() {
        if (root == null) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder();
        Node<E> c = root;
        do {
            builder.append(c.data).append(" ");
            c = c.next;
        }while (c != root);
        return builder.toString();
    }

    private static class Node<E> {
        private E data;
        private Node<E> prev;
        private Node<E> next;

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
