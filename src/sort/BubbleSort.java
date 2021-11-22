package sort;

import java.util.Arrays;

/**
 * 冒泡排序：相邻比较，较大置后
 * 时间复杂度：O(n^2-n) = O(n^2)
 */
public class BubbleSort {

    public static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void sort(Comparable[] a) {
        //从前往后进行相邻比较/交换，将最大值放在数组末尾
        for (int i = a.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (greater(a[j], a[j + 1])) {
                    exch(a, j, j + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {6, 7, 1, 9, 2, 5, 4, 3, 8, 0};
        BubbleSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
