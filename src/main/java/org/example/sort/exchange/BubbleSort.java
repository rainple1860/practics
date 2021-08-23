package org.example.sort.exchange;

import org.example.sort.AbsSort;

/**
 * @className: BubbleSort
 * @description: 冒泡排序
 * @author: rainple
 * @create: 2021-08-19 20:12
 **/
public class BubbleSort extends AbsSort {

    public BubbleSort(int size) {
        super(size);
    }

    public BubbleSort(int size, boolean check) {
        super(size, check);
    }

    public BubbleSort(int size, boolean check, int times) {
        super(size, check, times);
    }

    @Override
    protected void sort(int[] data) {
        int len = data.length;
        for (int i = 0; i < len - 1; i++) {
            boolean isBubble = false;
            for (int j = len - 1; j > i; j--) {
                if (data[j] < data[j - 1]) {
                    int temp = data[j];
                    data[j] = data[j - 1];
                    data[j - 1] = temp;
                    isBubble = true;
                }
            }
            if (!isBubble) {
                break;
            }
        }
    }

    @Override
    protected String sortName() {
        return "冒泡排序";
    }
}
