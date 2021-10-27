package org.example.construct.sequencies.queue;

/**
 * @InterfaceName: Queue
 * @description: 队列
 * @author: rainple
 * @create: 2021-09-13 19:56
 **/
public interface Queue<E>  {

    //清空队列
    void clear();
    //队列是否为空
    boolean isEmpty();
    //队列元素个数
    int length();
    //查看队列头部元素
    E peek();
    //向队列中添加一个元素
    boolean offer(E element);
    //从队列拉去一个元素
    E poll();

}
