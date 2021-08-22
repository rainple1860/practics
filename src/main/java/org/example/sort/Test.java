package org.example.sort;

import org.example.sort.exchange.HeapSort;
import org.example.sort.exchange.QuickSort;
import org.example.sort.insert.ShellSort;

/**
 * @className: Test
 * @description:
 * @author: rainple
 * @create: 2021-08-18 20:01
 **/
public class Test {

    public static void main(String[] args) {
        int size = 1000000;
        int times = 20;
        Sortable sortable = new ShellSort(size,false,times);
        sortable.printData(false);
        sortable.sort();

        sortable = new QuickSort(size,false,times);
        sortable.printData(false);
        sortable.sort();

        sortable = new HeapSort(size,false,times);
        sortable.printData(false);
        sortable.sort();
    }
}
