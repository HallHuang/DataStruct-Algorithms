package tree;

import java.util.Arrays;

/**
 * 使用堆构造原理进行一般数组的值排序（不直接使用功能类Heap-delMax()）
 * 从小到达的进行数组的堆全排序，包括兄弟结点
 * 堆排序的时间复杂度：O(n*log(n))
 */
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
    public static void sort(Comparable[] source) {
        Comparable[] heap = new Comparable[source.length + 1];
        createHeap(source, heap);

        //待排序元素的最大索引值
        int N = heap.length - 1;
        while (N != 1) {
            exch(heap, 1, N);   //最大值放在末尾
            N--;
            sink(heap, 1, N); //重排序，使索引1处的值当前最大
        }
        System.arraycopy(heap, 1, source, 0, source.length);
    }

    //是所有父结点的值均不小于子结点的值
    private static void sink(Comparable[] heap, int target, int range) {
        //所有非叶子结点
        while ( target <= range / 2) {
            int max;

            //存在右结点
            if (2 * target + 1 <= range) {
                //取两结点的值最大结点
                max = less(heap, 2 * target, 2 * target + 1) ? 2 * target + 1 : 2 * target;
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

        System.out.println("直接使用已定义好的Heap类进行排序");
        Heap<String> stringHeap = new Heap<>(arr.length + 1);
        for (String s : arr) {
            stringHeap.insert(s);
        }
        String result;
        while ((result = stringHeap.delMax())!= null) {
            System.out.println(result);
        }

        System.out.println("利用堆原理独立进行排序");
        HeapSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
