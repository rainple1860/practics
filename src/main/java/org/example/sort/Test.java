package org.example.sort;

import org.example.sort.exchange.QuickSort;
import org.example.sort.insert.ShellSort;
import org.example.sort.select.HeapSort;

/**
 * @className: Test
 * @description:
 * @author: rainple
 * @create: 2021-08-18 20:01
 **/
public class Test {

    public static void main(String[] args) {
        int size = 1000000;
        int times = 10;
        Sortable sortable = new RadixSort(size,false,times);
        sortable.printData(false);
        sortable.sort();

        sortable = new QuickSort(size,false,times);
        sortable.printData(false);
        sortable.sort();

        sortable = new HeapSort(size,false,times);
        sortable.printData(false);
        sortable.sort();

        sortable = new MergeSort(size,false,times);
        sortable.printData(false);
        sortable.sort();

        sortable = new ShellSort(size,false,times);
        sortable.printData(false);
        sortable.sort();
    }
}
