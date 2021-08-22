package org.example.sort.exchange;

import org.example.sort.AbsSort;

/**
 * @className: SelectSort
 * @description: 选择排序
 * @author: rainple
 * @create: 2021-08-19 22:49
 **/
public class SelectSort extends AbsSort {

    public SelectSort(int size) {
        super(size);
    }

    public SelectSort(int size, boolean check) {
        super(size, check);
    }

    @Override
    protected void sort(int[] data) {
        int len = data.length;
        for (int i = 0; i < len - 1; i++) {
            int min = data[i];
            int mi = i;
            for (int j = i + 1; j < len; j++) {
                if (data[j] < min) {
                    min = data[j];
                    mi = j;
                }
            }
            int temp = data[i];
            data[i] = min;
            data[mi] = temp;
        }
    }

    @Override
    protected String sortName() {
        return "选择排序";
    }
}
