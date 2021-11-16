package linear;

import java.util.Iterator;

public class TwoWayLinkList<T> implements Iterable<T> {
    private Node head;
    private Node last;
    private int N;

    TwoWayLinkList() {
        this.head = new Node(null, null, null);
        this.last = null;
        this.N = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    private class TwoWayIterator implements Iterator {
        private Node node;

        public TwoWayIterator() {
            this.node = head;
        }

        @Override
        public boolean hasNext() {
            return node.next != null;
        }

        @Override
        public Object next() {
            return node.next.item;
        }
    }

    private class Node {
        private Node pre;
        private Node next;
        private T item;

        public Node(Node preNode, T item, Node nextNode) {
            this.pre = preNode;
            this.item = item;
            this.next = nextNode;
        }
    }

    public void clear() {
        this.N = 0;
        head.next = last;
    }

    public int length() {
        return N;
    }

    public Node getNode(int index) {
        if (!isEmpty()) {
            if (index < N && index >= 0) {
                Node nextNode = head.next;
                for (int i = 0; i < index; i++) {
                    nextNode = nextNode.next;
                }
                return nextNode;
            }
            return null;
        }
        return null;
    }

    public T get(int index) {
        if (index < N && index >= 0) {
            Node nextNode = head.next;
            for (int i = 0; i < index; i++) {
                nextNode = nextNode.next;
            }
            return nextNode.item;
        }
        return null;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public T getFirst() {
        if (isEmpty()) {
            return null;
        }
        return head.next.item;
    }

    public T getLast() {
        if (isEmpty()) {
            return null;
        }
        return last.item;
    }

    public void insert(T t) {
        if (isEmpty()) {
            Node newNode = new Node(head, t, null);
            head.next = newNode;
            last = newNode;
        } else {
            Node newNode = new Node(last, t, null);
            last.next = newNode;
            last = newNode;
        }
        N++;
    }

    public void insert(int index, T t) {
        if (isEmpty()) {
            Node newNode = new Node(head, t, null);
            head.next = newNode;
            last = newNode;
        } else {
            Node preNode = getNode(index - 1);
            Node curNode = preNode.next;

            Node newNode = new Node(preNode, t, curNode);
            curNode.pre = newNode;
            preNode.next = newNode;
        }
        N++;
    }

    public T remove(int index) {
        if (index != N - 1) {
            Node curNode = getNode(index);
            Node preNode = curNode.pre;
            Node nextNode = curNode.next;
            preNode.next = nextNode;
            nextNode.pre = preNode;
            N--;
            return curNode.item;
        } else {
            Node oldLast = last;
            last = oldLast.pre;
            last.next = null;
            N--;
            return oldLast.item;
        }
    }

    public int indexOf(T t) {
        Node node = head.next;
        for (int index = 0; index < N; index++) {
            if (node.item.equals(t)) {
                return index;
            }
            node = node.next;
        }
        return -1;
    }

    public String toListString() {
        StringBuilder sb = new StringBuilder();
        Node node = head;
        do {
            node = node.next;
            sb.append(node.item.toString() + '\n');
        } while (node.next != null);
        return sb.toString();
    }

    public void reverse() {
        if (isEmpty()) {
            return;
        }
        last = head.next;
        reverse(head.next);
    }

    public T getMid() {
        Node fast = head.next;
        Node slow = head.next;

        while (true) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast.next == null || fast.next.next == null) {
                return slow.item;
            }
        }
    }

    public Node reverse(Node curNode) {
        if (curNode.next == null) {
            //head-->last
            head.next = curNode;
            return curNode;
            //下一步： last.next = preLast; preLast.next = null;
        }
        Node node = reverse(curNode.next);
        node.next = curNode;    //curNode.next.next = curNode
        curNode.next = null;
        return curNode;
    }

    public static void main(String[] args) {
        TwoWayLinkList<String> ll1 = new TwoWayLinkList<>();

        ll1.insert("abby");
        ll1.insert("folly");
        ll1.insert("confident");
        ll1.insert("sites");
        ll1.insert("vivo");
        ll1.insert("darling");
        ll1.insert("volly");
        ll1.insert("yews");
        ll1.insert(3, "hall");

        //ll1.reverse();

        ll1.insert("hwang");
        ll1.insert("hwangl");
        System.out.println(ll1.toListString());
        System.out.println(ll1.getMid());
        ll1.remove(2);
        System.out.println(ll1.toListString());
        System.out.println();
        ll1.reverse();
        System.out.println(ll1.toListString());
        ll1.insert("jinan");
        System.out.println("------------------------");
        ll1.reverse();
        ll1.remove(3);
        System.out.println(ll1.toListString());
        System.out.println("mid:" + ll1.getMid());


    }

}
