package graph;

import linear.Queue;
import tree.IndexMinPriorityQueue;

public class PrimMST {
    //索引代表顶点，数组存放顶点与最小生成树之间的权重最小的边
    private WeighedEdge[] edgeTo;
    //索引代表顶点，数组存放顶点与最小生成树之间的权重最小的边的权重
    private double[] distTo;
    //索引代表顶点，标记该顶点是否已经在最小生成树中
    private boolean[] marked;
    //存放当前最小树的顶点和其他顶点的有效横切边，关联索引即标识顶点
    private IndexMinPriorityQueue<Double> pq;

    public PrimMST(EdgeWeightedGraph G) {
        this.edgeTo = new WeighedEdge[G.V()];
        this.distTo = new double[G.V()];
        for (int i = 0; i < distTo.length; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        this.marked = new boolean[G.V()];
        pq = new IndexMinPriorityQueue<Double>(G.V());

        distTo[0] = 0.0;
        pq.insert(0, 0.0);

        while (!pq.isEmpty()) {
            visit(G, pq.delMin());
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (WeighedEdge e : G.adj(v)) {
            int w = e.other(v);
            if (marked[w]) {
                continue;   //顶点w已经在最小树中时不再考察
            }

            //判断边e的权重是否小于顶点w记录的与最小树的最小权重横切边的最小权重
            if (e.getWeight() < distTo[w]) {
                edgeTo[w] = e;  //记录最短边
                distTo[w] = e.getWeight();  //记录最小权重

                if (pq.contains(w)) {
                    pq.changeItem(w, e.getWeight());
                } else {
                    pq.insert(w, e.getWeight());
                }
            }
        }
    }

    public Queue<WeighedEdge> edges() {
        Queue<WeighedEdge> allEdges = new Queue<>();
        for (int i = 1; i < edgeTo.length; i++) {
            if (edgeTo[i] != null) {
                allEdges.enqueue(edgeTo[i]);
            }
        }
        return allEdges;
    }

    public static void main(String[] args) {
        WeighedEdge e1 = new WeighedEdge(0, 1, 10.11);
        WeighedEdge e2 = new WeighedEdge(1, 2, 2.83);
        WeighedEdge e3 = new WeighedEdge(2, 3, 7.59);
        WeighedEdge e4 = new WeighedEdge(3, 4, 6.43);
        WeighedEdge e5 = new WeighedEdge(4, 2, 8.47);
        WeighedEdge e6 = new WeighedEdge(4, 0, 9.52);
        WeighedEdge e7 = new WeighedEdge(4, 1, 10.22);

        EdgeWeightedGraph G = new EdgeWeightedGraph(7);
        G.addEdge(e1);
        G.addEdge(e2);
        G.addEdge(e3);
        G.addEdge(e4);
        G.addEdge(e5);
        G.addEdge(e6);
        G.addEdge(e7);

        PrimMST primMST = new PrimMST(G);
        Queue<WeighedEdge> edges = primMST.edges();
        for (WeighedEdge edge : edges) {
            System.out.print(edge.either() + "-" + edge.other(edge.either()) + " ");
            //1-2 2-3 3-4 4-0
        }


    }
}
