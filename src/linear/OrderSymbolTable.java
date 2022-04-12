package linear;

import java.util.Iterator;

/**
 * 有序符号表，按照键值从小到大进行元素排序
 * @param <Key>
 * @param <Value>
 */
public class OrderSymbolTable<Key extends Comparable<Key>, Value> implements Iterable<Value> {

    private final Node head;
    private int N;

    OrderSymbolTable() {
        this.head = new Node(null, null, null);
        this.N = 0;
    }

    public class Node {
        private final Key key;
        private Value value;
        private Node next;

        Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public int size() {
        return N;
    }

    public void put(Key key, Value value) {
        //键查重，若重则替换，否则添加
        Node pre = head;
        Node cur = head.next;

        //查找合适位置
        while (cur != null && cur.key.compareTo(key) < 0) {
            pre = cur;
            cur = cur.next;
        }

        //键重替换值
        if (cur != null && cur.key.compareTo(key) == 0) {
            cur.value = value;
            return;
        }

        //新结点的前驱、后驱分别是pre、cur
        pre.next = new Node(key, value, cur);
        N++;
    }

    public Value delete(Key key) {
        Node node = head;
        while (node.next != null) {
            if (node.next.key.equals(key)) {
                Node nextNode = node.next;
                node.next = nextNode.next;
                N--;
                return nextNode.value;
            }
            node = node.next;
        }
        return null;
    }

    public Value get(Key key) {
        Node node = head;
        while (node.next != null) {
            node = node.next;
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public Iterator<Value> iterator() {
        return new SymbolIterator();
    }

    private class SymbolIterator implements Iterator {
        private Node node;

        SymbolIterator() {
            this.node = head;
        }

        @Override
        public boolean hasNext() {
            return node.next != null;
        }

        @Override
        public Object next() {
            node = node.next;
            return node.value;
        }
    }

    public static void main(String[] args) {
        OrderSymbolTable<Integer, String> st = new OrderSymbolTable();
        st.put(5, "obama");
        st.put(3, "sarry");
        st.put(1, "boyes");
        st.put(4, "jack");
        st.put(2, "canny");
        st.put(0, "jinan");
        for (String item : st) {
            System.out.println(item);
        }

        System.out.println("-----------put 替换-------------");
        st.put(1, "beyonds");
        for (String item : st) {
            System.out.println(item);
        }

        System.out.println("-----------put 添加-------------");
        st.put(11, "rust");
        for (String item : st) {
            System.out.println(item);
        }

        st.put(10, "excwl");
        for (String item : st) {
            System.out.println(item);
        }
    }
}
