package org.example.sort.exchange;

import org.example.sort.AbsSort;

/**
 * @className: HeapSort
 * @description: 堆排序
 * @author: rainple
 * @create: 2021-08-22 21:20
 **/
public class HeapSort extends AbsSort {


    public HeapSort(int size) {
        super(size);
    }

    public HeapSort(int size, boolean check) {
        super(size, check);
    }

    public HeapSort(int size, boolean check, int times) {
        super(size, check, times);
    }

    @Override
    protected void sort(int[] data) {
        buildHeap(data);
        heapSort(data);
    }

    private void buildHeap(int[] data) {
        int len = data.length;
        for (int i = (len >> 1) - 1; i >= 0; i--) {
            adjust(data,len,i);
        }
    }

    private void heapSort(int[] data) {
        int len = data.length;
        while (len > 0) {
            int temp = data[0];
            data[0] = data[len - 1];
            data[len - 1] = temp;
            len--;
            adjust(data,len,0);
        }

    }

    private void adjust(int[] data,int len,int i) {
        int largest = i;
        int l = i << 1;
        int r = (i << 1) + 1;
        if (l < len && data[l] > data[largest]) {
            largest = l;
        }
        if (r < len && data[r] > data[largest]) {
            largest = r;
        }
        if (largest != i) {
            int temp = data[i];
            data[i] = data[largest];
            data[largest] = temp;
            adjust(data,len,largest);
        }
    }

    @Override
    protected String sortName() {
        return "堆排序";
    }
}
