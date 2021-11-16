package graph;

import linear.Queue;

public class Digraph {
    private final int V;    //顶点数
    private int E;  //有向边数
    private Queue<Integer>[] adj;   //各顶点(0,1,2,...,V-1)的相邻顶点（指向相邻顶点）组成的队列所组成的数组

    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = new Queue[V];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new Queue<>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    //将顶点v指向的顶点w作为v的相邻顶点存入队列
    public void addEdge(int v, int w) {
        adj[v].enqueue(w);
        E++;
    }

    public Queue<Integer> adj(int v) {
        return adj[v];
    }

    //将所有有向边的指向反转，通过遍历所有顶点的邻接表中的边实现
    public Digraph reverse() {
        Digraph r = new Digraph(V);
        for (int i = 0; i < V; i++) {
            for (Integer w : adj[i]) {
                r.addEdge(w, i);
            }
        }
        return r;
    }

    public static void main(String[] args) {
        Digraph dg = new Digraph(4);
        dg.addEdge(0, 1);
        dg.addEdge(1, 2);
        dg.addEdge(2, 1);
        dg.addEdge(2, 3);
        dg.addEdge(3, 1);
        dg.addEdge(3, 0);

        for (Integer v : dg.adj(3)) {
            System.out.print(v + ", ");
        }

        System.out.println();
        
        for (Integer v : dg.adj(2)) {
            System.out.print(v + ", ");
        }

    }
}
