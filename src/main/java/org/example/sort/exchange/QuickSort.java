package org.example.sort.exchange;

import org.example.sort.AbsSort;

/**
 * @className: QuickSort
 * @description: 快速排序
 * @author: rainple
 * @create: 2021-08-19 20:38
 **/
public class QuickSort extends AbsSort {
    public QuickSort(int size) {
        super(size);
    }

    public QuickSort(int size, boolean check) {
        super(size, check);
    }

    public QuickSort(int size, boolean check, int times) {
        super(size, check, times);
    }

    @Override
    protected void sort(int[] data) {
        quickSort(data,0,data.length - 1);
    }

    private void quickSort(int[] data,int left,int right) {
        if (right - left <= 0) {
            return;
        }
        int pivot = data[left];
        int start = left;
        int end  = right;
        while (left < right) {
            while (left < right && pivot < data[right]) {
                right--;
            }
            data[left++] = data[right];
            while (left < right && pivot > data[left]) {
                left++;
            }
            data[right--] = data[left];
        }
        data[left] = pivot;
        quickSort(data,start,left - 1);
        quickSort(data,right + 1,end);

    }

    @Override
    protected String sortName() {
        return "快速排序";
    }
}
