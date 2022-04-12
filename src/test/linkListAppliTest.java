package test;

/**
 * 基于快慢指针的链表环的判断与入口的查询
 */
public class linkListAppliTest {

    private static class Node<T> {
        public T item;
        public Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    //以first结点为起点，使用快慢指针判断是否有环
    private static boolean isCircle(Node<String> first) {
        Node<String> fast = first;
        Node<String> slow = first;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast.equals(slow)) {//如果移动中的快慢指针发生重合，则表示有环
                return true;
            }
        }
        return false;
    }

    /*
     * 当快慢指针相遇时，我们可以判断到链表中有环，这时重新设定一个新指针指向链表的起点，
     * 且步长与慢指针一样为1，则慢指针与“新”指针相遇的地方就是环的入口
     */
    private static Node<String> getEntrance(Node<String> first) {
        Node<String> fast = first;
        Node<String> slow = first;
        Node<String> temp = null;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast.equals(slow)) {//环存在
                temp = first;
                continue;
            }

            if (temp != null) {
                temp = temp.next;
                if (temp.equals(slow)) {
                    break;
                }
            }
        }
        return temp;
    }

    public static void main(String[] args) {
        Node<String> node1 = new Node("hall1", null);
        Node<String> node2 = new Node("hall2", null);
        Node<String> node3 = new Node("hall3", null);
        Node<String> node4 = new Node("hall4", null);
        Node<String> node5 = new Node("hall5", null);
        Node<String> node6 = new Node("hall6", null);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node2;

        boolean circle = isCircle(node1);
        System.out.println(circle + "");
        Node<String> entrance = getEntrance(node1);
        System.out.println(entrance.item);
    }
}
