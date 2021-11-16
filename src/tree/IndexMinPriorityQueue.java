package tree;

public class IndexMinPriorityQueue<T extends Comparable<T>> {
    private final T[] items;  //添加数据数组
    private final int[] pq;   //关联索引数组，pq[堆索引] = 关联索引
    private final int[] qp;   //堆索引数组，qp[关联索引] = 堆索引
    private int N;

    public IndexMinPriorityQueue(int capacity) {
        //原始待处理数组
        this.items = (T[]) new Comparable[capacity + 1];
        //原始数组转化成堆后，从小到大各项值的关联索引组成的数组
        this.pq = new int[capacity + 1];
        //原始待处理数组各项在堆中的索引值所组成的数组
        this.qp = new int[capacity + 1];
        this.N = 0;

        for (int i = 0; i < qp.length; i++) {
            qp[i] = -1;
        }
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    //用于插入/删除元素后，原最小堆的重新调整
    private boolean less(int i, int j) {
        return items[pq[i]].compareTo(items[pq[j]]) < 0;
    }

    private void exch(int i, int j) {
        //交换关联索引值
        int tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;

        //qp[i]默认所有元素起始值都是-1, qp[原始索引值] = 堆索引值， pq[堆索引值] = 原始索引值，
        //qp[pq[堆索引值]] = 堆索引值 != -1
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    //由关联索引值判断是否已经添加过
    public boolean contains(int k) {
        return qp[k] != -1;
    }

    //最小值对应的关联索引值
    public int minIndex() {
        return pq[1];
    }

    //在原始items数组中i位置插入一个元素t
    public void insert(int i, T t) {
        if (contains(i)) {
            return;
        }
        //长度自增1
        N++;
        //items[i]=t
        items[i] = t;
        //将新添加元素放在堆的最后,其堆索引号为N，之后在进行上浮操作
        pq[N] = i;
        //qp
        qp[i] = N;
        //对新添加在堆末尾的元素进行上浮调整
        swim(N);
    }

    private void swim(int k) {
        while (k > 1) {
            if (less(k, k / 2)) {
                exch(k, k / 2);
            } else {
                break;
            }
            k = k / 2;
        }
    }

    private void sink(int k) {
        int min = 0;
        while (2 * k <= N) {
            if (2 * k + 1 <= N) {
                if (less(2 * k, 2 * k + 1)) {
                    min = 2 * k;
                } else {
                    min = 2 * k + 1;
                }
            } else {
                min = 2 * k;
            }

            if (less(k, min)) {
                break;
            }
            exch(k, min);
            k = min;
        }

    }

    //删除并返回关联索引值
    public int delMin() {
        int minIndex = pq[1];
        exch(1, N--);
        sink(1);
        qp[minIndex] = -1;
        items[minIndex] = null;
        //pq[N+1] = -1;
        return minIndex;
    }

    //
    public void delete(int i) {
        int k = qp[i];  //关联索引--》堆索引
        System.out.println("K=" + k);
        exch(k, N);     //将待删除元素和堆中最后元素进行交换
        N--;
        swim(k);
        sink(k);
        items[i] = null;
        qp[i] = -1;
    }

    public void changeItem(int i, T t) {
        items[i] = t;
        int k = qp[i];
        sink(k);
        swim(k);
    }

    public static void main(String[] args) {
        IndexMinPriorityQueue<String> impq = new IndexMinPriorityQueue<>(20);
        impq.insert(0, "happ");
        impq.insert(1, "jelly");
        impq.insert(2, "vivo");
        impq.insert(3, "green");
        impq.insert(4, "lorry");
        impq.insert(5, "abby");
        impq.insert(6, "salary");


        impq.delete(1);

        System.out.println("----------------------");

        impq.delete(4);
        while (!impq.isEmpty()) {
            System.out.println(impq.delMin());
        }//imp1=null
        System.out.println("----------------------");

    }


}
