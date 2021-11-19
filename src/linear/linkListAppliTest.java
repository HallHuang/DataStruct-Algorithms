package linear;

public class linkListAppliTest {

    private static class Node<T> {
        public T item;
        public Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    private static boolean isCircle(Node<String> first) {
        Node<String> fast = first;
        Node<String> slow = first;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast.equals(slow)) {
                return true;
            }
        }
        return false;
    }

    private static Node<String> getEntrance(Node<String> first) {
        Node<String> fast = first;
        Node<String> slow = first;
        Node<String> temp = null;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast.equals(slow)) {
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
