package org.example.sort.insert;

import org.example.sort.AbsSort;

/**
 * @className: ShellSort
 * @description: 希尔排序
 * @author: rainple
 * @create: 2021-08-18 21:40
 **/
public class ShellSort extends AbsSort {

    public ShellSort(int size) {
        super(size);
    }

    public ShellSort(int size, boolean check) {
        super(size, check);
    }

    public ShellSort(int size, boolean check, int times) {
        super(size, check, times);
    }

    @Override
    protected void sort(int[] data) {
        int len = data.length;
        for (int step = len >> 1; step > 0; step = step >> 1) {
            for (int j = step; j < len; j++) {
                if (data[j] < data[j - step]) {
                    int tem = data[j];
                    int m;
                    for (m = j - step; m >= 0 && tem < data[m]; m -= step) {
                        data[m + step] = data[m];
                    }
                    data[m + step] = tem;
                }
            }
        }
    }

    @Override
    protected String sortName() {
        return "希尔排序";
    }
}
