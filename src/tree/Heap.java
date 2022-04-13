package tree;

/**
 * 堆：一颗完全二叉树的数组对象
 * 父结点的值不小于或不大于子结点的值，索引1处的值最大或最小
 * 本代码实现的是最大堆（最大优先队列MaxPriorityQueue）
 * @param <T>
 */
public class Heap<T extends Comparable<T>> {

    private final T[] items;
    private int N;

    public Heap(int capacity) {
        this.items = (T[]) new Comparable[capacity + 1];
        this.N = 0;
    }

    private boolean less(int i, int j) {
        return items[i].compareTo(items[j]) < 0;
    }

    private void exch(int i, int j) {
        T tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }

    //树的层序式数据插入
    public void insert(T t) {
        items[++N] = t;
        swim(N);
    }

    private void swim(int k) {
        //只考虑非根结点
        while (k > 1) {
            //如果父结点的值小于插入值，则进行交换，直到树中全部结点的父结点的值都大于子结点
            if (less(k / 2, k)) {
                exch(k / 2, k);
            }
            k = k / 2;
        }
    }

    public T delMax() {
        T max = items[1];
        //将堆数组中的最后一个元素和首元素进行交换来补空，之后对新首元素进行下沉操作
        exch(1, N);
        items[N] = null;
        N--;
        sink(1);
        return max;
    }

    private void sink(int k) {
        //只考虑非叶结点，找到根结点子树下的值最大对应的索引值，然后进行交换
        while (k <= N / 2) {
            int maxIndex;

            //k索引对应位置的左右两个子节点的值较大所对应的索引值，即为其子树中的最大值
            if (2 * k + 1 <= N) {//子结点索引值小于N即表示存在该子结点
                maxIndex = less(2 * k, 2 * k + 1) ? 2 * k + 1 : 2 * k;
            } else {
                maxIndex = 2 * k;
            }

            if (!less(k, maxIndex)) {
                break;
            }
            exch(k, maxIndex);
            k = maxIndex;    //继续往下迭代直到全部完成
        }
    }

    public static void main(String[] args) {
        Heap<String> hp = new Heap<>(10);
        hp.insert("green");
        hp.insert("door");
        hp.insert("excel");
        hp.insert("abby");
        hp.insert("baby");
        hp.insert("car");
        hp.insert("family");
        hp.insert("jelly");
        hp.insert("hall");
        hp.insert("internal");

        String result;
        while ((result = hp.delMax()) != null) {
            System.out.println(result);
        }
    }
}
