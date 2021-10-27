package org.example.construct.sequencies.queue.seq;

import org.example.construct.sequencies.queue.Queue;

import java.util.Arrays;

/**
 * @className: SeqCycleQueue
 * @description: 顺序结构的循环队列
 * @author: rainple
 * @create: 2021-09-13 20:03
 **/
public class SeqCycleQueue<E> implements Queue<E> {

    private static final int INCREASE = 50;
    private Object[] data;
    //1: 满,0为空，
    private int flag;
    //头部指针
    private int front;
    //尾部指针
    private int rear;
    //数组长度
    private int capacity;

    public SeqCycleQueue(int size) {
        data = new Object[size];
        this.capacity = size;
    }

    @Override
    public void clear() {
        Arrays.fill(data, null);
    }

    @Override
    public boolean isEmpty() {
        return flag == 0;
    }

    @Override
    public int length() {
        if (isEmpty()) {
            return 0;
        }
        return rear > front ? rear - front : front - rear;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return element(front);
    }

    @SuppressWarnings("unchecked")
    private E element (int index) {
        return (E) data[index];
    }

    @Override
    public boolean offer(E element) {
        if (flag == 1) {//已满
            System.out.println("队列已满");
            return false;
        }
        data[rear] = element;
        rear = (rear + 1) % capacity;
        if (rear == front) {
            flag = 1;
        }
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {//空队列
            System.out.println("队列已空");
            return null;
        }
        E element = element(front);
        front = (front + 1) % capacity;
        if (front == rear) {
            flag = 0;
        } else {
            flag = 2;
        }
        return element;
    }
}
