package graph;

public class DirectedWeightedEdge {
    private final int v;
    private final int w;
    private final double weight;

    public DirectedWeightedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    //获取边的权重值
    public double weight() {
        return weight;
    }

    //获取有向边的起点
    public int from() {
        return v;
    }

    //获取有向边的终点
    public int to() {
        return w;
    }
}
