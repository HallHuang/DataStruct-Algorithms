package graph;

import linear.Queue;
import tree.MinPriorityQueue;
import tree.UF;

public class KruskalMST {

    private Queue<WeightedEdge> mst;
    private UF uf;
    private MinPriorityQueue<WeightedEdge> pq;

    public KruskalMST(EdgeWeightedGraph G) {
        //保存最小生成树的所有边
        this.mst = new Queue<>();
        //索引代表顶点，使用uf.connect(v,w)可以判断顶点v和顶点w是否在同一颗树中，
        // 使用uf.union(v,w)可以把顶点v所在的树和顶点w所在的树合并
        this.uf = new UF(G.V());
        //存储图中所有的边，使用最小优先队列，对边按照权重进行排序
        this.pq = new MinPriorityQueue<>(G.E());

        for (WeightedEdge e : G.edges()) {
            pq.insert(e);   //所有加权边入列，按权重进行自动排序
        }

        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            WeightedEdge e = pq.delMin();    //权重最小的边
            int v = e.either();
            int w = e.other(v);
            if (uf.connected(v, w)) {
                continue;
            }
            uf.union(v, w);
            mst.enqueue(e);
        }
    }

    public Queue<WeightedEdge> edges() {
        return mst;
    }
}
