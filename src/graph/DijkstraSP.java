package graph;

import linear.Queue;
import tree.IndexMinPriorityQueue;

public class DijkstraSP {
    //索引代表顶点，值表示从顶点s到当前顶点的最短路径上的最后一条边
    private DirectedWeightedEdge[] edgeTo;
    //索引代表顶点，值从顶点s(起点)到当前顶点的最短路径的总权重（逐个叠加）
    private double[] distTo;
    //存放树中顶点与非树中顶点之间的有效横切边
    private IndexMinPriorityQueue<Double> pq;

    //根据一副加权有向图G和顶点s，创建一个计算顶点为s的最短路径树对象
    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        //初始化edgeTo
        this.edgeTo = new DirectedWeightedEdge[G.V()];
        //初始化distTo
        this.distTo = new double[G.V()];
        for (int i = 0; i < distTo.length; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        //初始化pq
        this.pq = new IndexMinPriorityQueue<>(G.V());

        //找到图G中以顶点s为起点的最短路径树，默认让顶点s进入到最短路径树中
        distTo[s] = 0.0;
        pq.insert(s, 0.0);

        //遍历pq
        while (!pq.isEmpty()) {
            relax(G, pq.delMin());
        }
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedWeightedEdge edge : G.adj(v)) {
            int w = edge.to();  //边的终点

            if (distTo[v] + edge.weight() < distTo[w]) {
                edgeTo[w] = edge;
                distTo[w] = distTo[v] + edge.weight();

                if (pq.contains(w)) {
                    pq.changeItem(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    //判断从顶点s到顶点v是否可达
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Queue<DirectedWeightedEdge> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }

        Queue<DirectedWeightedEdge> allEdges = new Queue<>();
        while (true) {
            DirectedWeightedEdge edge = edgeTo[v];
            if (edge == null) {//递归到起点，循环结束
                break;
            }

            allEdges.enqueue(edge);
            v = edge.from();
        }
        return allEdges;
    }

    public static void main(String[] args) {
        DirectedWeightedEdge dwe1 = new DirectedWeightedEdge(0, 1, 2.83);
        DirectedWeightedEdge dwe2 = new DirectedWeightedEdge(0, 4, 1.75);
        DirectedWeightedEdge dwe3 = new DirectedWeightedEdge(1, 0, 3.83);
        DirectedWeightedEdge dwe4 = new DirectedWeightedEdge(4, 3, 8.25);
        DirectedWeightedEdge dwe5 = new DirectedWeightedEdge(4, 5, 2.75);
        DirectedWeightedEdge dwe6 = new DirectedWeightedEdge(1, 2, 3.40);
        DirectedWeightedEdge dwe7 = new DirectedWeightedEdge(1, 4, 4.59);
        DirectedWeightedEdge dwe8 = new DirectedWeightedEdge(1, 5, 1.13);
        DirectedWeightedEdge dwe9 = new DirectedWeightedEdge(2, 3, 5.83);
        DirectedWeightedEdge dwe10 = new DirectedWeightedEdge(5, 2, 1.53);
        DirectedWeightedEdge dwe11 = new DirectedWeightedEdge(5, 3, 5.93);
        DirectedWeightedEdge dwe12 = new DirectedWeightedEdge(3, 5, 0.33);

        EdgeWeightedDigraph G = new EdgeWeightedDigraph(12);
        G.addEdge(dwe1);
        G.addEdge(dwe2);
        G.addEdge(dwe3);
        G.addEdge(dwe4);
        G.addEdge(dwe5);
        G.addEdge(dwe6);
        G.addEdge(dwe7);
        G.addEdge(dwe8);
        G.addEdge(dwe9);
        G.addEdge(dwe10);
        G.addEdge(dwe11);
        G.addEdge(dwe12);

        DijkstraSP dijkstraSP = new DijkstraSP(G, 0);
        Queue<DirectedWeightedEdge> directedWeightedEdges = dijkstraSP.pathTo(5);
        for (DirectedWeightedEdge edge : directedWeightedEdges) {
            System.out.print(edge.from() + "-" + edge.to() + " ");
        }

    }

}
