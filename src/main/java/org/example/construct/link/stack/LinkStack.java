package org.example.construct.link.stack;

/**
 * @className: LinkStack
 * @description: 链栈
 * @author: rainple
 * @create: 2021-09-16 19:42
 **/
public class LinkStack<E> {

    private Elem<E> top;
    private int size;

    public void push(E e) {
        Elem<E> elem = new Elem<>(e);
        size++;
        elem.next = top;
        top = elem;
    }

    public E pop() {
        if (top == null) {
            return null;
        }
        size--;
        Elem<E> newTop = top.next;
        top.next = null;
        E data = top.data;
        top = newTop;
        return data;
    }

    private int size() {
        return size;
    }

    private static class Elem<E> {
        private E data;
        private Elem<E> next;

        public Elem(E data) {
            this.data = data;
        }
    }

}
