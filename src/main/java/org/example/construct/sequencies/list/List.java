package org.example.construct.sequencies.list;

/**
 * @InterfaceName: List
 * @description: 列表
 * @author: rainple
 * @create: 2021-09-13 21:20
 **/
public interface List<E> {

    int size();
    void add(E e);
    E remove(int index);
    E removeFirst();
    E removeLast();
    E get(int index);
    E getLast();
    E getFirst();

}
