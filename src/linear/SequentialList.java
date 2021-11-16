package linear;

import java.util.Iterator;

public class SequentialList<T> {

    private T[] array;
    private int N;

    public SequentialList(int capacity) {
        this.array = (T[]) new Object[capacity];
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

    public T get(int i) {
        return array[i];
    }

    //尾加
    public void insert(T t) {

        if (N == array.length) {
            reSize(2 * array.length);
        }
        array[N++] = t;
    }

    private void reSize(int newSize) {
        T[] temp = array;
        array = (T[]) new Object[newSize];
        for (int k = 0; k < N; k++) {
            array[k] = temp[k];
        }
    }

    public void insert(int i, T t) {

        if (N == array.length) {
            reSize(2 * array.length);
        }
        //i后元素后移一位
        for (int k = i + 1; k <= N; k++) {
            array[k] = array[k - 1];
        }
        array[i] = t;
        N++;
    }


    public T remove(int i) {
        T eleRemove = array[i];
        //i后元素前移一位
        for (int k = i; k <= N - 2; k++) {
            array[k] = array[k + 1];
        }
        N--;

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
            return array[cursor++];
        }
    }

    public static void main(String[] args) {

        SequentialList<String> sl1 = new SequentialList<>(4);
        sl1.insert("hello");
        sl1.insert("world");
        sl1.insert("Bill");
        sl1.insert("Clinton");

        Iterator<String> iterator = sl1.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
