package graph;

import linear.Queue;

public class Graph {

    private final int V;    //顶点数
    private int E;  //边数
    private Queue<Integer>[] adj;   //顶点的相邻顶点的索引值组成的队列所组成的队列

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
        //相互入列
        adj[v].enqueue(w);
        adj[w].enqueue(v);
        E++;
    }

    public Queue<Integer> adj(int v) {
        return adj[v];
    }

    public static void main(String[] args) {

    }
}
