package linear;

public class JosephTest {

    private static class Node<T> {
        public T item;
        public Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Node<Integer> first = null;
        Node<Integer> node = null;

        //创建一个长度为41的循环列表
        for (int i = 1; i <= 41; i++) {
            if (i == 1) {
                first = new Node<>(i, null);
                node = first;
                continue;
            }
            Node<Integer> newNode = new Node<>(i, null);
            node.next = newNode;
            node = newNode;

            if (i == 41) {
                node.next = first;
            }
        }

        int count = 0;
        //定义当前结点和前一结点
        Node<Integer> curr = first;
        Node<Integer> pre = null;

        //模拟循环计数，当只剩下一个元素时停止计数
        while (!curr.equals(curr.next)) {
            count++;
            System.out.print(count + ", ");
            if (count == 3) {
                pre.next = curr.next;
                System.out.println(curr.item);
                count = 0;
                curr = curr.next;
                //pre="2", cur="4;....
            } else {
                pre = curr;
                curr = curr.next;
            }
        }
        System.out.println(curr.item);
    }

}
