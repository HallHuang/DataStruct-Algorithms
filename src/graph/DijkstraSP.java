package graph;

import linear.Queue;
import tree.IndexMinPriorityQueue;

public class DijkstraSP {
    //索引代表顶点，值表示从顶点s到当前顶点的最短路径上的最后一条边
    private DirectedWeightedEdge[] edgeTo;
    //索引代表顶点，值从顶点s(起点)到当前顶点的最短路径的总权重
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

        //找到图G中以顶点s为起点的最短路径树
        //默认让顶点s进入到最短路径树中
        distTo[s] = 0.0;
        pq.insert(s, 0.0);

        //遍历pq
        while (!pq.isEmpty()) {
            relax(G, pq.delMin());
        }

    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedWeightedEdge edge : G.adj(v)) {
            int w = edge.to();

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

}
