package linear;

import java.util.Iterator;

/**
 * 队列，基于单向列表实现
 * 辅助成员变量有head、last:数据结点、N:元素个数
 * 两端：尾部入列、首部出列，先进先出
 * @param <T>
 */
public class Queue<T> implements Iterable<T> {

    private Node head;
    private Node last;
    private int N;

    public Queue() {
        this.head = new Node(null, null);
        this.last = head;
        this.N = 0;
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

    //新添加元素放在链表尾部
    public void enqueue(T t) {
        Node newNode = new Node(t, null);
        if (isEmpty()) {
            last = newNode;
            head.next = last;
        } else {
            Node oldLast = last;
            last = newNode;
            oldLast.next = last;
        }
        N++;
    }

    //弹出head.next
    public T dequeue() {
        if (isEmpty()) {
            return null;
        } else {
            Node deqNode = head.next;
            head.next = deqNode.next;
            N--;
            return deqNode.item;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator {

        private Node n;

        public QueueIterator() {
            this.n = head;
        }

        @Override
        public boolean hasNext() {
            return n.next != null;
        }

        @Override
        public Object next() {
            n = n.next;
            return n.item;
        }
    }

    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        queue.enqueue("hall");
        queue.enqueue("quiter");
        queue.enqueue("cart");
        queue.enqueue("google");
        queue.enqueue("apple");
        queue.enqueue("hepp");

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }
}
