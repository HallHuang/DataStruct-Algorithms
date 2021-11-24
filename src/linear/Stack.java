package linear;

import java.util.Iterator;

/**
 * 基于单向链表的栈的实现
 * 辅助变量只有head:数据结点、N:元素个数
 * 一端：首部入栈、首部出栈，先进后出
 */
public class Stack<T> implements Iterable<T> {

    private final Node head;
    private int N;

    public Stack() {
        this.head = new Node(null, null);
        this.N = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator {

        private Node node;

        public StackIterator() {
            this.node = head;  //引入全局首空结点
        }

        @Override
        public boolean hasNext() {
            return node.next != null;
        }

        @Override
        public Object next() {
            node = node.next;   //迭代过程
            return node.item;   //不能写成 node.next.item;
        }
    }

    public class Node {
        public T item;
        public Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(T t) {
        //当前首结点
        Node oldNode = head.next;
        //包含新数据的新结点，并指向当前首结点，head指向新结点
        head.next = new Node(t, oldNode);
        N++;
    }

    public T pop() {
        Node popNode = head.next;
        if (popNode == null) {
            return null;
        }
        head.next = popNode.next;
        N--;
        return popNode.item;
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("abby");
        stack.push("bell");
        stack.push("dell");
        stack.push("tailer");
        stack.push("lorry");
        stack.push("green");

        for (String s : stack) {
            System.out.println(s);
        }
    }
}
