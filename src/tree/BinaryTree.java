package tree;

import linear.Queue;

public class BinaryTree<Key extends Comparable<Key>, Value> {

    private Node root;
    private int N;

    BinaryTree() {
        this.root = null;
        this.N = 0;
    }

    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;

        public Node(Key key, Value value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    public Node put(Node x, Key key, Value value) {

        //触底时的初始化, 当x.left/right = null时
        if (x == null) {
            N++;
            return new Node(key, value, null, null);
        }

        int cmp = key.compareTo(x.key);     //key-x.key
        if (cmp > 0) {
            //若x.right为空，则创建包含参数数据的新的结点；否则，仅需进行递归迭代直到创建新结点
            x.right = put(x.right, key, value); //更新右分支树，直到新建结点填充某个结点x的左/右结点
        } else if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else {
            x.value = value;
        }
        return x;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    public Value get(Node x, Key key) {

        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            return get(x.right, key);
        } else if (cmp < 0) {
            return get(x.left, key);
        } else {
            return x.value;
        }
    }

    public void delete(Key key) {
        delete(root, key);
    }

    public Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            x.right = delete(x.right, key);  //右树更新,等待下层更新完成
        } else if (cmp < 0) {
            x.left = delete(x.left, key);    //左树更新,等待下层更新完成
        } else {
            N--;
            if (x.left == null) {
                return x.right;     //删除+重指向
            }

            if (x.right == null) {
                return x.left;      //删除+重指向
            }

            //查询右支键最小对应的结点minNode
            Node minNode = x.right;
            while (minNode.left != null) {
                minNode = minNode.left;
            }


            //删除minNode
            Node node = x.right;
            Node preNode = node;
            while (node.left != null) {
                if (node.left.left == null) {//叶结点:（）--（）
                    node.left = null;  //minNode现位置置空
                } else {
                    preNode = node;
                    node = node.left;
                }
            }
            //minNode存在右结点，则该右结点替代minNode位置
            if (minNode.right != null) {
                preNode.left = minNode.right;
            }


            //x ==> minNode
            minNode.left = x.left;
            minNode.right = x.right;
            if (x.equals(root)) {//如果删除的是根结点，则必须重设root,否则root空指针
                root = minNode;
            }
            x = minNode;
        }

        return x;   // x_father.left/right = x;
    }

    public int size() {
        return N;
    }

    public Key minKey() {
        return minKey(root);
    }

    public Key minKey(Node x) {
        while (x.left != null) {
            x = x.left;
        }
        return x.key;
    }

    public Key maxKey() {
        return maxKey(root);
    }

    public Key maxKey(Node x) {
        while (x.right != null) {
            x = x.right;
        }
        return x.key;
    }

    public Queue<Key> preErgodic() {
        Queue<Key> keys = new Queue<>();
        preErgodic(root, keys);
        return keys;
    }

    public void preErgodic(Node x, Queue<Key> keys) {

        if (x == null) {
            return;
        }

        //x.key ==> keys
        keys.enqueue(x.key);

        //x.left.{key} ==> keys
        if (x.left != null) {
            preErgodic(x.left, keys);
        }

        //x.right.{key} ==> keys
        if (x.right != null) {
            preErgodic(x.right, keys);
        }
    }

    public Queue<Key> midErgodic() {
        Queue<Key> keys = new Queue<>();
        midErgodic(root, keys);
        return keys;
    }

    public void midErgodic(Node x, Queue<Key> keys) {

        if (x == null) {
            return;
        }

        //先递归，左子树的键放入对列中
        if (x.left != null) {
            midErgodic(x.left, keys);
        }
        keys.enqueue(x.key);
        if (x.right != null) {
            midErgodic(x.right, keys);
        }
    }

    public Queue<Key> afterErgodic() {
        Queue<Key> keys = new Queue<>();
        afterErgodic(root, keys);
        return keys;
    }

    public void afterErgodic(Node x, Queue<Key> keys) {

        //先递归，左子树的键放入对列中
        if (x.left != null) {
            afterErgodic(x.left, keys);
        }

        if (x.right != null) {
            afterErgodic(x.right, keys);
        }

        keys.enqueue(x.key);
    }

    //从上到下，从左往右
    public Queue<Key> layerErgodic() {

        Queue<Node> nodes = new Queue<>();
        Queue<Key> keys = new Queue<>();

        nodes.enqueue(root);    //根结点入列

        //对结点队列进行遍历，出元与入元同时进行
        while (!nodes.isEmpty()) {
            Node node = nodes.dequeue();    //结点出列
            keys.enqueue(node.key);
            if (node.left != null) {
                nodes.enqueue(node.left);   //左结点入列
            }
            if (node.right != null) {
                nodes.enqueue(node.right);  //右结点入列
            }
        }
        return keys;
    }

    public int maxDepth() {
        return maxDepth(root);
    }

    public int maxDepth(Node x) {

        //递归终点
        if (x == null) {
            return 0;
        }

        int max = 0;
        int maxL = 0;
        int maxR = 0;

        if (x.left != null) {
            maxL = maxDepth(x.left);
        }

        if (x.right != null) {
            maxR = maxDepth(x.right);
        }

        max = maxL > maxR ? maxL + 1 : maxR + 1;

        return max;
    }

    public static void main(String[] args) {
        BinaryTree<Integer, String> bt = new BinaryTree<>();
        bt.put(20, "20");
        bt.put(18, "18");
        bt.put(12, "12");
        bt.put(8, "8");
        bt.put(34, "34");
        bt.put(28, "28");
        bt.put(33, "33");

        System.out.println("SIZE: " + bt.size());
        System.out.println(bt.get(28));
        System.out.println(bt.get(20));
        System.out.println(bt.get(33));

        System.out.println("---------------------");
        bt.delete(12);
        System.out.println("SIZE: " + bt.size());
        System.out.println("after delete: " + bt.get(12));
        bt.delete(20);
        System.out.println("SIZE: " + bt.size());
        System.out.println("after delete: " + bt.get(20));
        System.out.println("minkey:" + bt.minKey());
        System.out.println("maxkey:" + bt.maxKey());

        System.out.println("---------前序遍历------------");
        Queue<Integer> keys = bt.preErgodic();
        for (Integer item : keys) {
            System.out.println(item);
        }

        System.out.println("---------中序遍历------------");
        Queue<Integer> keys1 = bt.midErgodic();
        for (Integer item : keys1) {
            System.out.println(item);
        }

        System.out.println("---------层序遍历------------");
        Queue<Integer> keys2 = bt.layerErgodic();
        for (Integer item : keys2) {
            System.out.println(item);
        }

        bt.put(5, "5");
        System.out.println("深度：" + bt.maxDepth());

    }
}
