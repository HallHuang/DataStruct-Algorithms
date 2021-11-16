package tree;

public class Heap<T extends Comparable<T>> {

    private T[] items;
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

    public void insert(T t) {
        items[++N] = t;
        swim(N);
    }

    private void swim(int k) {
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
        exch(1, N);
        items[N] = null;
        N--;
        sink(1);
        return max;
    }

    private void sink(int k) {
        //循环中止条件是到达叶子结点（其索引二倍值一定大于N）
        while (2 * k <= N) {//分枝结点的子结点值判断
            int max;

            //存在右结点
            if (2 * k + 1 <= N) {
                //取两结点的值最大结点
                if (less(2 * k, 2 * k + 1)) {
                    max = 2 * k + 1;
                } else {
                    max = 2 * k;
                }
            } else {
                max = 2 * k;
            }

            if (!less(k, max)) {
                break;
            }
            exch(k, max);
            k = max;    //继续往下迭代直到全部完成
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

        String result = null;
        while ((result = hp.delMax()) != null) {
            System.out.println(result);
        }
    }
}
