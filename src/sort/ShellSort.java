package sort;

import java.util.Arrays;

/**
 * 希尔排序：分组比较，间隔交换
 * 时间复杂度：O(n^s, 1<s<2)
 */
public class ShellSort {

    public static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void sort(Comparable[] a) {
        int h = 1;
        while (h < a.length / 2) {
            h = 2 * h + 1;
        }//初始间隔值为大于（a.length/2）的最小奇数

        //变间隔值迭代更新，从后往前按照索引间隔h进行比较/交换
        while (h >= 1) {
            for (int i = h; i < a.length; i++) {//i=h, h+1, h+2, ..., a.length-1
                for (int j = i; j >= h && greater(a[j - h], a[j]); j -= h) {
                    //(i, i-h),(i-h, i-2h),(i-2h, i-3h),...比较、交换
                    exch(a, j - h, j);
                }
            }
            h = h / 2;  //下一轮h的更新值
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {6, 7, 1, 9, 2, 5, 4, 3, 8, 0};
        ShellSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
