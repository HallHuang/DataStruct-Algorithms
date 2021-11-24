package linear;

import java.util.Iterator;

/**
 * 符号表，基于单项链表(数据结点类Node(key, value, next))
 * 一端插入、删除，先进后出
 *
 * @param <Key>
 * @param <Value>
 */
public class SymbolTable<Key, Value> implements Iterable<Value> {

    private final Node head;
    private int N;

    SymbolTable() {
        this.head = new Node(null, null, null);
        this.N = 0;
    }

    //有序的数据结点类
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
        Node node = head.next;
        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
            node = node.next;
        }

        //head指向最近添加的元素
        Node oldFirst = head.next;
        head.next = new Node(key, value, oldFirst);
        N++;
    }

    public Value delete(Key key) {
        //键查重，若重则删除，否则返回null
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
        SymbolTable<String, String> st = new SymbolTable();
        st.put("one", "boyes");
        st.put("two", "canny");
        st.put("three", "sarry");
        st.put("four", "jack");
        st.put("five", "obama");
        for (String item : st) {
            System.out.println(item);
        }

        System.out.println("------------------------");
        System.out.println(st.get("three"));

        System.out.println("delete two: " + st.delete("two"));
        System.out.println("-----------after delete two-------------");
        for (String item : st) {
            System.out.println(item);
        }

        st.put("one", "girls");
        for (String item : st) {
            System.out.println(item);
        }
    }
}
