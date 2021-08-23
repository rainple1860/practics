package org.example.sort;

/**
 * @className: MergeSort
 * @description: 归并排序
 * @author: rainple
 * @create: 2021-08-23 09:33
 **/
public class MergeSort extends AbsSort {
    public MergeSort(int size) {
        super(size);
    }

    public MergeSort(int size, boolean check) {
        super(size, check);
    }

    public MergeSort(int size, boolean check, int times) {
        super(size, check, times);
    }

    @Override
    protected void sort(int[] data) {
        int[] temp = new int[data.length];
        split(data,temp,0,data.length - 1);
    }

    private void split(int[] data,int[] temp,int left,int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            split(data,temp,left,mid);
            split(data,temp,mid + 1,right);
            merge(data,temp,left,mid,right);
        }
    }

    private void merge(int[] data,int[] tempData,int left,int mid,int right) {
        int l_pos = left;
        int r_pos = mid + 1;
        int temp_pos = left;
        while (l_pos <= mid && r_pos <= right) {
            if (data[l_pos] <= data[r_pos]) {
                tempData[temp_pos++] = data[l_pos++];
            } else {
                tempData[temp_pos++] = data[r_pos++];
            }
        }
        while (l_pos <= mid) {
            tempData[temp_pos++] = data[l_pos++];
        }
        while (r_pos <= right) {
            tempData[temp_pos++] = data[r_pos++];
        }
        System.arraycopy(tempData,left,data,left,right - left + 1);
    }

    @Override
    protected String sortName() {
        return "归并排序";
    }
}
