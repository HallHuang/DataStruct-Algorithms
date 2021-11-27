package graph;

import linear.Queue;

/**
 * 深度优先搜索
 *
 */
public class DepthFirstSearch {
    private final boolean[] marked; //各顶点已被搜索过的标记数组
    private int count;  //与起点s相同的顶点数
    private Queue<Integer> queue;

    //构造器设置顶点s为起点并进行搜索
    public DepthFirstSearch(Graph G, int s) {
        this.marked = new boolean[G.V()];
        this.count = 0;
        queue = new Queue<>();
        dfs(G, s);
    }

    //深度优先搜索全部互通结点
    private void dfs(Graph G, int v) {
        marked[v] = true;
        queue.enqueue(v);
        for (Integer w : G.adj(v)) {//对相邻顶点进行遍历处理
            if (!marked(w)) {
                dfs(G, w);  //逐层向前递归搜索触底，结束节点是全部相通结点都已被标记为true,此时开始逐层向后执行
                System.out.println("w = " + w);
            }
        }
        count++;
        System.out.println("count = " + count);
    }

    //返回搜索路径上各顶点
    public Queue<Integer> queue() {
        return queue;
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        Graph G = new Graph(12);
        G.addEdge(0, 4);
        G.addEdge(0, 1);
        G.addEdge(1, 2);
        G.addEdge(2, 3);
        G.addEdge(2, 5);
        G.addEdge(2, 4);
        G.addEdge(3, 4);
        G.addEdge(6, 7);
        G.addEdge(6, 9);
        G.addEdge(7, 9);
        G.addEdge(7, 8);
        G.addEdge(8, 10);

        DepthFirstSearch search = new DepthFirstSearch(G, 0);
        System.out.println(search.count());
        System.out.println(search.marked(6));
        System.out.println(search.marked(5));
        System.out.println("-----------------");
        Queue<Integer> queue = search.queue();
        for (Integer integer : queue) {
            System.out.print(integer + " ");
        }
        System.out.println("");

        Graph graphRoad = new Graph(20);
        graphRoad.addEdge(0, 1);
        graphRoad.addEdge(6, 9);
        graphRoad.addEdge(3, 8);
        graphRoad.addEdge(5, 11);
        graphRoad.addEdge(2, 12);
        graphRoad.addEdge(6, 10);
        graphRoad.addEdge(4, 8);

        //以9为起点进行搜索，全部搜到的顶点标记为true,即为与9相通的全部顶点
        DepthFirstSearch srRoad = new DepthFirstSearch(graphRoad, 9);
        System.out.println("8顶点是否与9顶点相同：" + srRoad.marked(8));
        System.out.println("10顶点是否与9顶点相同：" + srRoad.marked(10));

    }
}
