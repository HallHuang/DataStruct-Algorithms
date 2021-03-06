package tree;

import java.util.Iterator;

/**
 * 最小优先队列
 * 该种堆的父结点的值不大于子结点的值
 * @param <T>
 */
public class MinPriorityQueue<T extends Comparable<T>> {
    //存储堆中的元素
    private T[] items;
    //记录堆中元素的个数
    private int N;

    public MinPriorityQueue(int capacity) {
        this.items = (T[]) new Comparable[capacity + 1];
        this.N = 0;
    }

    //获取队列中元素的个数
    public int size() {
        return N;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return N == 0;
    }

    //判断堆中索引i处的元素是否小于索引j处的元素
    private boolean less(int i, int j) {
        return items[i].compareTo(items[j]) < 0;
    }

    //交换堆中i索引和j索引处的值
    private void exch(int i, int j) {
        T tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }

    //往堆中插入一个元素
    public void insert(T t) {
        items[++N] = t;
        swim(N);
    }

    //删除堆中最小的元素,并返回这个最小元素
    public T delMin() {
        T min = items[1];
        exch(1, N);
        items[N] = null;
        sink(1, --N);
        return min;
    }

    //使用上浮算法，使索引k处的元素能在堆中处于一个正确的位置
    private void swim(int k) {
        //通过循环比较当前结点和其父结点的大小
        while (k > 1) {
            if (less(k, k / 2)) {
                exch(k, k / 2);
            }
            k = k / 2;
        }
    }

    //在1~range范围内进行下沉
    private void sink(int k, int range) {
        while (k <= range / 2) {
            //1.找到子结点中的较小值的索引
            int min;

            //考虑全部右子结点和没有右子结点两种情况
            if (2 * k + 1 <= range) {
                min = less(2 * k, 2 * k + 1) ? 2 * k : 2 * k + 1;
            } else {//最后一个分枝结点没有右子结点
                min = 2 * k;
            }

            //2.判断当前结点和较小值的大小
            if (less(k, min)) {
                break;
            }
            exch(k, min);
            k = min;
        }
    }

    public static void main(String[] args) {
        MinPriorityQueue<String> queue = new MinPriorityQueue<>(8);
        queue.insert("vivi");
        queue.insert("xuxux");
        queue.insert("jelly");
        queue.insert("android");
        queue.insert("oreo");
        queue.insert("kafu");
        queue.insert("elly");
        queue.insert("yield");

        System.out.println("--------使用delMin遍历-----------");
        String result;
        while ((result = queue.delMin()) != null) {
            System.out.println(result);
        }

    }
}
