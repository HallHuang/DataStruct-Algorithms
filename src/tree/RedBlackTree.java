package tree;

public class RedBlackTree<Key extends Comparable<Key>, Value> {
    private Node root;
    private int N;
    private static final boolean RED = true;
    public static final boolean BLACK = false;

    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private boolean color;

        public Node(Key key, Value value, Node left, Node right, boolean color) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.color = color;
        }
    }

    public int size() {
        return N;
    }

    public boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    //结点x的右结点是红色结点时
    //第2、3两步的执行顺序不能颠倒
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    //结点x.left和x.left.left的颜色均为红色
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    //当结点x的左右两结点都是红色,而x的颜色为黑色时
    private void flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }

    //在树x上完成数据插入操作，并返回添加元素后的新树
    private Node put(Node x, Key key, Value val) {

        if (x == null) {
            N++;
            return new Node(key, val, null, null, RED);
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.value = val;
        }

        //x树添加修改完成后对x结点处进行平衡化处理
        if (isRed(x.right) && !isRed(x.left)) {
            x = rotateLeft(x);
        }

        //进行右旋：当当前结点h的左子结点和左子结点的左子结点都为红色，需要右旋
        if (isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        }

        //颜色反转：当前结点的左子结点和右子结点都为红色时，需要颜色反转
        if (isRed(x.left) && isRed(x.right)) {
            flipColors(x);
        }
        return x;
    }

    //根据key，从树中找出对应的值
    public Value get(Key key) {
        return get(root, key);
    }

    //从指定的树x中，查找key对应的值
    public Value get(Node x, Key key) {
        while (x != null) {
            //比较x结点的键和key的大小
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return x.value;
            }
        }
        return null;
    }

    public Node getNode(Key key) {
        return getNode(root, key);
    }

    private Node getNode(Node x, Key key) {
        while (x != null) {
            //比较x结点的键和key的大小
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return x;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        RedBlackTree<String, String> rbt = new RedBlackTree<>();

        rbt.put("1", "green");
        rbt.put("2", "red");
        rbt.put("3", "white");
        rbt.put("4", "ruler");
        rbt.put("5", "pen");
        rbt.put("6", "box");

        System.out.println("------------------------");
        System.out.println(rbt.getNode("1").color);
        System.out.println(rbt.getNode("2").color);
        System.out.println(rbt.getNode("3").color);
        System.out.println(rbt.getNode("4").color);
        System.out.println(rbt.getNode("5").color);
        System.out.println(rbt.getNode("6").color);

    }
}
