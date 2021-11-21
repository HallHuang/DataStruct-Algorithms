package sort;

import java.util.Arrays;

/**
 * 快速排序：首元中介，左右递归
 * 时间复杂度：O(n*log(n)~n^2)
 */
public class QuickSort {

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void sort(Comparable[] a) {
        int lo = 0;
        int hi = a.length - 1;
        sort(a, lo, hi);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int partition = partition(a, lo, hi);
        sort(a, lo, partition - 1);
        sort(a, partition + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        Comparable key = a[lo];
        int left = lo;
        int right = hi + 1;

        while (true) {
            //先从右往左遍历查找小于key的元素对应的索引值
            while (less(key, a[--right])) {
                if (right == lo) {
                    break;
                }
            }

            //之后从左往右遍历查找大于key的元素对应的索引
            while (less(a[++left], key)) {
                if (left == hi) {
                    break;
                }
            }

            if (left >= right) {
                break;
            }
            exch(a, left, right);
        }
        exch(a, lo, right);
        return right;
    }

    public static void main(String[] args) {
        Integer[] arr = {6, 7, 11, 1, 9, 2, 5, 10, 4, 3, 8, 0};
        QuickSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
