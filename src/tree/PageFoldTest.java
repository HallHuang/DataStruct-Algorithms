package tree;

import linear.Queue;

public class PageFoldTest {

    private static class Node<T> {
        private T item;
        private Node left;
        private Node right;

        public Node(T item, Node left, Node right) {
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        printTree(createTree(2));
    }

    public static Node<String> createTree(int N) {

        Node<String> root = null;

        /**
         * i=0 : queue(root)
         * i=1: queue(root, root.left, root.righe)
         * i=2: queue(root, root.left, root.right, root.left.left, root.left.right, root.right.left, root.right.right)
         * .......
         */
        for (int i = 0; i < N; i++) {

            //第一次对折
            if (i == 0) {
                root = new Node<>("down", null, null);
                continue;
            }

            Queue<Node> queue = new Queue<>();
            queue.enqueue(root);    //root：全局变量

            while (!queue.isEmpty()) {
                Node deqNode = queue.dequeue();

                if (deqNode.left != null) {
                    queue.enqueue(deqNode.left);
                }
                if (deqNode.right != null) {
                    queue.enqueue(deqNode.right);
                }

                //叶结点上的左/右结点生成
                if (deqNode.left == null && deqNode.right == null) {
                    deqNode.left = new Node("down", null, null);
                    deqNode.right = new Node("up", null, null);
                }
            }
        }
        return root;
    }

    public static void printTree(Node x) {

        if (x == null) {
            return;
        }

        //左、 根、 右
        if (x.left != null) {
            printTree(x.left);
        }

        System.out.print(x.item + " ");

        if (x.right != null) {
            printTree(x.right);
        }
    }
}
