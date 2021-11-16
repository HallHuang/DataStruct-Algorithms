package linear;

import java.util.Iterator;

public class SymbolTable<Key, Value> implements Iterable<Value> {

    private Node head;
    private int N;

    SymbolTable() {
        this.head = new Node(null, null, null);
        this.N = 0;
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

    public class Node {
        private Key key;
        private Value value;
        private Node next;

        Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public void put(Key key, Value value) {
        //键查重，若重则替换，否则添加
        Node node = head;
        while (node.next != null) {
            if (node.next.key.equals(key)) {
                node.next.value = value;
                return;
            }
            node = node.next;
        }

        Node oldFirst = head.next;
        Node newNode = new Node(key, value, oldFirst);
        head.next = newNode;
        N++;
    }

    public Value delete(Key key) {
        //键查重，若重则删除，否则返回null
        Node node = head;
        while (node.next != null) {
            if (node.next.key.equals(key)) {
                node.next = node.next.next;
                N--;
                return node.next.value;
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
        st.delete("two");
        for (String item : st) {
            System.out.println(item);
        }
        st.put("one", "girls");
        for (String item : st) {
            System.out.println(item);
        }
    }

}
