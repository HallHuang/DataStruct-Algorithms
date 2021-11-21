package sort;

import java.util.Arrays;

/**
 * 选择排序：往前插入，逐个比较
 * 时间复杂度：O(n^2-n) = O(n^2)
 */
public class InsertionSort {

    private static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void sort(Comparable[] a) {
        for (int i = 1; i <= a.length - 1; i++) {
            for (int j = i; j >= 1; j--) {
                if (greater(a[j - 1], a[j])) {
                    exch(a, j - 1, j);
                } else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {6, 7, 1, 9, 2, 5, 4, 3, 8, 0};
        InsertionSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
