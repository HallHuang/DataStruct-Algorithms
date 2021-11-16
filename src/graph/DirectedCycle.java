package graph;

public class DirectedCycle {
    private boolean[] marked;   //标记是否搜索过
    private boolean[] kept; //标记是否作为判断顶点，无相邻顶点时为false
    private boolean hasCycle;   //是否有环
    private int joint;  //记录有环时的判断终止顶点

    public DirectedCycle(Digraph G) {
        this.marked = new boolean[G.V()];
        this.kept = new boolean[G.V()];
        this.hasCycle = false;

        //遍历所有顶点，避免遗漏相互独立图的环
        for (int i = 0; i < G.V(); i++) {
            if (!marked[i]) {
                dfs(G, i);
            }
        }

    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        kept[v] = true;
        for (Integer w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }

            if (kept[w]) {
                hasCycle = true;
                joint = w;
                return;
            }
        }
        kept[v] = false;    //当前结点没有相邻顶点时，不作为待判断点给予考虑
    }

    public boolean isHasCycle() {
        return hasCycle;
    }

    public int getJoint() {
        return joint;
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(6);
        G.addEdge(0, 1);
        G.addEdge(1, 2);
        G.addEdge(2, 3);
        G.addEdge(3, 4);
        G.addEdge(3, 5);
        G.addEdge(5, 2);
        G.addEdge(1, 5);

        DirectedCycle directedCycle = new DirectedCycle(G);
        System.out.println("是否有环：" + directedCycle.hasCycle);
        System.out.println("环的最终判断点：" + directedCycle.getJoint());
    }
}
