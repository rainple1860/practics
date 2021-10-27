package org.example.construct.sequencies.queue;

import org.example.construct.sequencies.queue.seq.SeqCycleQueue;

/**
 * @className: Test
 * @description:
 * @author: rainple
 * @create: 2021-09-13 20:30
 **/
public class Test {

    public static void main(String[] args) {
        Queue<Integer> queue = new SeqCycleQueue<>(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        System.out.println(queue);
        queue.offer(5);

    }

}
