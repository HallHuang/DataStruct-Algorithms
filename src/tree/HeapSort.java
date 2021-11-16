package tree;

import java.util.Arrays;

public class HeapSort {

    private static boolean less(Comparable[] heap, int i, int j) {
        return heap[i].compareTo(heap[j]) < 0;
    }

    private static void exch(Comparable[] heap, int i, int j) {
        Comparable tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    //由源数组构造出堆
    private static void createHeap(Comparable[] source, Comparable[] heap) {
        System.arraycopy(source, 0, heap, 1, source.length);
        for (int i = (heap.length) / 2; i > 0; i--) {
            sink(heap, i, heap.length - 1); //从下层到上层，分枝结点逐个判断下沉，最终形成堆
        }
    }

    //按值从小到大进行排序，每次把首项（最大值）和最后一项进行交换
    private static void sort(Comparable[] source) {
        Comparable[] heap = new Comparable[source.length + 1];
        createHeap(source, heap);

        //待排序元素的最大索引值
        int N = heap.length - 1;
        while (N != 1) {
            exch(heap, 1, N);
            N--;
            sink(heap, 1, N);
        }
        System.arraycopy(heap, 1, source, 0, source.length);
    }

    private static void sink(Comparable[] heap, int target, int range) {
        //所有非叶子结点
        while (2 * target <= range) {
            int max;

            //存在右结点
            if (2 * target + 1 <= range) {
                //取两结点的值最大结点
                if (less(heap, 2 * target, 2 * target + 1)) {
                    max = 2 * target + 1;
                } else {
                    max = 2 * target;
                }
            } else {
                max = 2 * target;
            }

            if (!less(heap, target, max)) {
                break;
            }
            exch(heap, target, max);
            target = max;    //继续往下迭代直到全部完成
        }
    }

    public static void main(String[] args) {
        String[] arr = {"sale", "ghud", "idhhs", "wjdb", "ojh", "pjxhx", "gdf", "root"};
        HeapSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
