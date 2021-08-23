package org.example.sort;

/**
 * @className: BukectSort
 * @description: 基数排序
 * @author: rainple
 * @create: 2021-08-23 10:56
 **/
public class RadixSort extends AbsSort {

    private int[][] buckets;
    private int maxRadix;

    public RadixSort(int size) {
        super(size);
        initBucket(size);
    }

    private void initBucket(int size) {
        buckets = new int[10][size];
        maxRadix = ((size * 10) + "").length();
    }

    public RadixSort(int size, boolean check) {
        super(size, check);
        initBucket(size);
    }

    public RadixSort(int size, boolean check, int times) {
        super(size, check, times);
        initBucket(size);
    }

    @Override
    protected void sort(int[] data) {
        int bLen = buckets.length;
        int[] bucketCapacity = new int[bLen];
        for (int i = 0; i < maxRadix; i++) {
            for (int datum : data) {
                int r = getRadix(datum, i);
                buckets[r][bucketCapacity[r]++] = datum;
            }
            int index = 0;
            for (int j = 0; j < bLen; j++) {
                if (bucketCapacity[j] != 0) {
                    int bl = bucketCapacity[j];
                    for (int k = 0; k < bl; k++) {
                        data[index++] = buckets[j][k];
                    }
                    bucketCapacity[j] = 0;
                }
            }
        }
    }

    private static final int[] R = {1,10,100,1000,10000,100000,1000000,10000000,100000000,1000000000};

    private int getRadix(int data,int position) {
        return (data / R[position]) % 10;
    }

    @Override
    protected String sortName() {
        return "基数排序";
    }
}
