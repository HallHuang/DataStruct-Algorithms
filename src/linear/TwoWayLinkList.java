package linear;

import java.util.Iterator;

/**
 * 双向链表
 * @param <T>
 */
public class TwoWayLinkList<T> implements Iterable<T> {
    private final Node head;
    private Node last;
    private int N;

    TwoWayLinkList() {
        this.head = new Node(null, null, null);
        this.last = null;
        this.N = 0;
    }

    private class Node {
        private Node pre;
        private Node next;
        private final T item;

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
        if (isEmpty()) {//初始化last
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
        if (!isEmpty()) {
            Node preNode = getNode(index - 1);
            Node curNode = preNode.next;
            Node newNode = new Node(preNode, t, curNode);
            curNode.pre = newNode;
            preNode.next = newNode;
            N++;
        } else if (index == 0) {//isEmpty() && index == 0
            Node newNode = new Node(head, t, null);
            head.next = newNode;
            last = newNode;
            N++;
        } else { // isEmpty() && index != 0
            return;
        }
    }

    public T remove(int index) {
        if (index < 0 || index > N - 1) {
            return null;
        } else if (index != N - 1) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node node = head;
        do {
            node = node.next;
            sb.append(node.item.toString()).append(", ");
        } while (node.next != null);
        return sb.toString().substring(0, sb.length() - 2);
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

    //倒序排列
    public void reverse() {
        if (isEmpty()) {
            return;
        }
        last = head.next;
        reverse(head.next);
    }

    public Node reverse(Node curNode) {
        if (curNode.next == null) {
            //head-->last
            head.next = curNode;
            curNode.pre = head;
            return curNode;
            //下一步： last.next = preLast; preLast.next = null;
        }
        Node node = reverse(curNode.next);  //递归到底，满足返回条件
        node.next = curNode;
        curNode.pre = node;
        curNode.next = null;
        return curNode;
    }

    @Override
    public Iterator<T> iterator() {
        return new TwoWayIterator();
    }

    private class TwoWayIterator implements Iterator {
        private final Node node;

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
        ll1.insert("hwang");
        ll1.insert("hwangl");
//        System.out.println(ll1);
//        System.out.println(ll1.getMid());
//        ll1.remove(2);
//        System.out.println(ll1);
        ll1.reverse();
        System.out.println("---------reverse-----------");
        System.out.println(ll1);
        System.out.println(ll1.getNode(3).pre.item);
    }
}
