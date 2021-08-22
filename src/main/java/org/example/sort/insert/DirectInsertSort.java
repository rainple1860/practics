package org.example.sort.insert;

import org.example.sort.AbsSort;

/**
 * @className: DirectInsertSort
 * @description: 直接插入排序
 * @author: rainple
 * @create: 2021-08-18 19:40
 **/
public class DirectInsertSort extends AbsSort {

    public DirectInsertSort(int size) {
        super(size);
    }

    public DirectInsertSort(int size, boolean check) {
        super(size, check);
    }

    @Override
    protected void sort(int[] data) {
        int len = data.length;
        int j;
        for (int i = 1; i < len; i++) {
            if (data[i] < data[i - 1]) {
                int tmp = data[i];
                for (j = i - 1; j >= 0 && tmp < data[j]; j--) {
                    data[j + 1] = data[j];
                }
                data[j+1] = tmp;
            }
        }


    }

    @Override
    protected String sortName() {
        return "直接插入排序";
    }
}
