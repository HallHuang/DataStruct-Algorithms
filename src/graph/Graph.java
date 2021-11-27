package graph;

import linear.Queue;

/**
 * 无向图
 * 队列数组adj：索引值代表顶点，数组元素adj[i]保存对应顶点的相邻顶点
 */
public class Graph {

    private final int V;    //顶点数
    private int E;  //边数
    private Queue<Integer>[] adj;   //顶点的相邻顶点的索引值组成的队列所组成的数组

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = new Queue[V];    //adj[0],adj[1],adj[2],...
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new Queue<Integer>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        //相互入列，两个顶点可添加多条平行边
        adj[v].enqueue(w);
        adj[w].enqueue(v);
        E++;
    }

    public Queue<Integer> adj(int v) {
        return adj[v];
    }
}
