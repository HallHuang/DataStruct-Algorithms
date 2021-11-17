package graph;

import linear.Queue;

public class EdgeWeightedGraph {
    //顶点总数
    private final int V;
    //边的总数
    private int E;
    //某一顶点的邻接表(保存邻边)
    private Queue<WeighedEdge>[] adj;

    //创建一个含有V个顶点的空加权有向图
    public EdgeWeightedGraph(int V) {
        //初始化顶点数量
        this.V = V;
        //初始化边的数量
        this.E = 0;
        //初始化邻接表
        this.adj = new Queue[V];

        for (int i = 0; i < adj.length; i++) {
            adj[i] = new Queue<WeighedEdge>();
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

    //在加权无向图中添加一条边
    public void addEdge(WeighedEdge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].enqueue(e);
        adj[w].enqueue(e);
        E++;
    }

    //获取所有边
    public Queue<WeighedEdge> edges() {
        Queue<WeighedEdge> allEdges = new Queue<>();
        //遍历所有顶点，将该顶点的邻接边仅入列一次
        for (int v = 0; v < V; v++) {
            for (WeighedEdge edge : adj[v]) {
                if (edge.other(v) < v) {
                    allEdges.enqueue(edge); //通过两端顶点值比较，将边只入列一次
                }
            }
        }
        return allEdges;
    }

    public Queue<WeighedEdge> adj(int v) {
        return adj[v];
    }

}
