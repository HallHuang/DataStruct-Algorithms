package linear;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 顺序表
 * 基于数组
 * @param <T>
 */
public class SequentialList<T> implements Iterable<T> {

    private T[] array;
    private int N;

    public SequentialList(int capacity) {
        this.array = (T[]) new Object[capacity];    //容量有限
        this.N = 0;
    }

    public void clear() {
        if (N != 0) {
            for (int k = 0; k <= N - 1; k++) {
                array[k] = null;
            }
        }
        this.N = 0;
    }

    public boolean isEmpty() {
        return this.N == 0;
    }

    public int size() {
        return N;
    }

    public T get(int i) {
        return array[i];
    }

    //尾加
    public void insert(T t) {
        if (N == array.length) {
            reSize(2 * array.length);   //扩容
        }
        array[N++] = t;
    }

    private void reSize(int newSize) {
        //T[] temp = array;
        //array = (T[]) new Object[newSize];
        //System.arraycopy(temp, 0, array, 0, N);
        this.array = Arrays.copyOf(this.array, newSize);
    }

    public void insert(int i, T t) {

        if (i > N || i < 0) {
            return;
        }

        //i后元素后移一位，末尾索引由N-1变为N
        if (N == array.length) {
            reSize(2 * array.length);
        }
        T[] newArray = Arrays.copyOf(array, N);
        for (int k = i; k <= N - 1; k++) {
            array[k + 1] = newArray[k];
        }
        array[i] = t;
        N++;
    }


    public T remove(int i) {

        if (i > N - 1) {
            return null;
        }

        T eleRemove = array[i];
        //i后元素前移一位
        for (int k = i; k <= N - 2; k++) {
            array[k] = array[k + 1];
        }
        N--;

        //元素过少时减少数组容量
        if (N < array.length / 4) {
            reSize(array.length / 2);
        }

        return eleRemove;
    }

    public int indexOf(T t) {

        for (int i = 0; i <= N - 1; i++) {
            if (array[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new SeqIterator();
    }

    private class SeqIterator implements Iterator {
        private int cursor;

        public SeqIterator() {
            this.cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return cursor < N;
        }

        @Override
        public T next() {
            return array[cursor++]; //获取当前元素并使指针增1
        }
    }

    //测试
    public static void main(String[] args) {

        SequentialList<String> sl1 = new SequentialList<>(4);
        sl1.insert("hello");
        sl1.insert("world");
        sl1.insert("Bill");
        sl1.insert("Clinton");
        sl1.insert("Bush");
        sl1.insert(1, "obama");
        sl1.insert(6, "Biden");
        sl1.insert(3, "Reagan");
        sl1.insert(8, "Konnie");

        for (String s : sl1) {
            System.out.println(s);
        }

        System.out.println("index of Bill: " + sl1.indexOf("Bill"));
        System.out.println(sl1.get(1));
        System.out.println(sl1.remove(2));
        System.out.println(sl1.size());
        sl1.clear();
        System.out.println(sl1.size());

    }
}
