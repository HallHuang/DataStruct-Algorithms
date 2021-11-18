package graph;

import linear.Queue;

public class EdgeWeightedDigraph {

    //顶点总数
    private final int V;
    //边的总数
    private int E;
    //邻接表,保存各顶点的指出加权边
    private Queue<DirectedWeightedEdge>[] adj;

    //创建一个含有V个顶点的空加权有向图
    public EdgeWeightedDigraph(int V) {
        //初始化顶点数量
        this.V = V;
        //初始化边的数量
        this.E = 0;
        //初始化邻接表
        this.adj = new Queue[V];

        for (int i = 0; i < adj.length; i++) {
            adj[i] = new Queue<>();
        }
    }

    //获取图中顶点的数量
    public int V() {
        return V;
    }

    //获取图中边的数量
    public int E() {
        return E;
    }

    public void addEdge(DirectedWeightedEdge e) {
        int v = e.from();
        adj[v].enqueue(e);  //保存顶点v指出的边，即该边的起点是v
        E++;
    }

    public Queue<DirectedWeightedEdge> adj(int v) {
        return adj[v];
    }

    public Queue<DirectedWeightedEdge> edges() {
        Queue<DirectedWeightedEdge> allEdges = new Queue<>();
        for (int v = 0; v < V; v++) {
            for (DirectedWeightedEdge edge : adj[v]) {
                allEdges.enqueue(edge);
            }
        }
        return allEdges;
    }
}
