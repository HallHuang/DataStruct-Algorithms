package graph;

public class WeighedEdge implements Comparable<WeighedEdge> {

    private final int v;    //顶点一
    private final int w;    //顶点二
    private final double weight;    //该边的权重

    public WeighedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public int either() {
        return v;
    }

    public int other(int vertex) {
        if (vertex == v) {
            return w;
        } else {
            return v;
        }
    }

    @Override
    public int compareTo(WeighedEdge that) {
        return Double.compare(this.getWeight(), that.getWeight());   //记录比较权重的结果
    }
}
