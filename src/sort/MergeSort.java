package sort;

import java.util.Arrays;

/**
 * 归并排序：不断二分，邻组比较
 * 时间复杂度：O(n*log(n))
 */
public class MergeSort {
    private static Comparable[] assist;

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void sort(Comparable[] a) {
        assist = new Comparable[a.length];
        int lo = 0;
        int hi = a.length - 1;
        sort(a, lo, hi);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {//分裂为单元素数组时停止递归
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);   //前半组递归排序
        sort(a, mid + 1, hi);   //后半组递归排序
        merge(a, lo, mid, hi);  //从单元素数组返回后进行多元素数组内部的排序、合并
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo;
        int p1 = lo;
        int p2 = mid + 1;

        while (p1 <= mid && p2 <= hi) {
            if (less(a[p2], a[p1])) {
                assist[i++] = a[p2++];
            } else {
                assist[i++] = a[p1++];
            }
        }

        //只有一个指针完全遍历，另一个指针没完全遍历需要继续遍历填充assit数组
        while (p1 <= mid) {//没完全遍历
            assist[i++] = a[p1++];
        }

        while (p2 <= hi) {//没完全遍历
            assist[i++] = a[p2++];
        }

        if (hi + 1 - lo >= 0) System.arraycopy(assist, lo, a, lo, hi + 1 - lo);
    }

    public static void main(String[] args) {
        Integer[] arr = {6, 7, 11, 1, 9, 2, 5, 10, 4, 3, 8, 0};
        MergeSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
