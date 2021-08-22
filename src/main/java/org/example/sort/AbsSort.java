package org.example.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @className: AbsSort
 * @description:
 * @author: rainple
 * @create: 2021-08-18 20:04
 **/
public abstract class AbsSort implements Sortable{

    private int size;
    protected boolean print = false;
    private boolean check;
    private int times = 1;

    public AbsSort(int size) {
        this.size = size;
    }

    public AbsSort(int size,boolean check) {
        this.size = size;
        this.check = check;
    }

    public AbsSort(int size, boolean check, int times) {
        this.size = size;
        this.check = check;
        this.times = times;
    }

    @Override
    public void sort() {
        long total = 0;
        long min = Long.MAX_VALUE;
        for (int i = 0; i < times; i++) {
            int[] data = prepareData();
            if (print) {
                System.out.println("排序前：" + Arrays.toString(data));
            }
            long start = System.currentTimeMillis();
            sort(data);
            long end = System.currentTimeMillis();
            if (check) {
                check(data);
            }
            if (print) {
                System.out.println("排序后：" + Arrays.toString(data));
            }
            long spend = end - start;
            if (spend < min) {
                min = spend;
            }
            total += spend;
        }
        System.out.println("名称\t\t数据量\t\t次数\t平均耗费时间(毫秒)\t最短耗时（毫秒）\t总耗时（毫秒）");
        System.out.println(sortName() + "\t\t" + size + "\t\t" + times + "\t\t" + total / times + "\t\t\t\t\t" + min + "\t\t\t\t" + total);
    }

    @Override
    public void printData(boolean print) {
        this.print = print;
    }

    protected abstract void sort(int[] data);

    private void check(int[] data) {
        int len = data.length;
        for (int i = 0; i < len - 1; i++) {
            if (data[i] > data[i + 1]) {
                System.out.println("排序有误");
                return;
            }
        }
        System.out.println("排序正确");
    }

    protected abstract String sortName();

    private int[] prepareData() {
        int len = size * 10;
        int[] data = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            data[i] = random.nextInt(len);
        }
        return data;
    }

}
