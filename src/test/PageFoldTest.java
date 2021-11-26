package test;

import linear.Queue;

/**
 * 折痕问题
 * 树的逐层生成扩展+中序打印结点数据
 */
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
        printTree(createTree(4));
    }

    /**
     * i=1 : queue(root)
     * i=2: queue(root, root.left, root.right)
     * i=3: queue(root, root.left, root.right, root.left.left, root.left.right, root.right.left, root.right.right)
     * .......
     * 返回生长完成后的整棵树，即要返回root
     */
    public static Node<String> createTree(int N) {

        Node<String> root = new Node<>("down", null, null);
        ;
        double count = 0;
        double layer = 1;
        Queue<Node> queue = new Queue<>();
        queue.enqueue(root);    //root：全局变量

        //先生长再入列
        while (!queue.isEmpty()) {
            Node deqNode = queue.dequeue();

            //叶结点上的左/右结点生成
            if (deqNode.left == null && deqNode.right == null) {
                deqNode.left = new Node("down", null, null);
                deqNode.right = new Node("up", null, null);
                count += 2;
                if (count == Math.pow(2, layer)) {
                    layer++;
                    if (layer == N) {
                        break;
                    }
                    count = 0;
                }
            }

            //将所有新创建子结点入列，以待进行每个叶结点的双子结点创建
            queue.enqueue(deqNode.left);
            queue.enqueue(deqNode.right);
        }
        return root;
    }

    //中序遍历进行打印
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
