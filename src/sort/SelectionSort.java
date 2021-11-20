package sort;

import java.util.Arrays;

/**
 * 选择排序：逐个比较，较小置首
 * 时间复杂度：O((n^2)/2+(n/2)-1) = O(n^2)
 */
public class SelectionSort {

    public static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (greater(a[minIndex], a[j])) {
                    minIndex = j;
                }
            }
            exch(a, i, minIndex);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {6, 7, 1, 9, 2, 5, 4, 3, 8};
        SelectionSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
