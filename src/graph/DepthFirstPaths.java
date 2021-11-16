package graph;

import linear.Stack;

public class DepthFirstPaths {

    private boolean[] marked;
    private int s;
    private int[] edgeTo;

    public DepthFirstPaths(Graph G, int s) {
        this.marked = new boolean[G.V()];
        this.s = s;
        this.edgeTo = new int[G.V()];
        dfp(G, s);
    }

    private void dfp(Graph G, int v) {
        marked[v] = true;
        for (Integer w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;  //v -> w
                dfp(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Stack<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }

        Stack<Integer> path = new Stack<>();
        while (v != s) {
            path.push(v);
            v = edgeTo[v];
        }
        path.push(v);
        return path;
    }

    public static void main(String[] args) {
        Graph G = new Graph(12);
        G.addEdge(0, 1);
        G.addEdge(0, 4);
        G.addEdge(1, 2);
        G.addEdge(2, 3);
        G.addEdge(2, 5);
        G.addEdge(2, 4);
        G.addEdge(3, 4);
        G.addEdge(6, 7);
        G.addEdge(6, 9);
        G.addEdge(7, 9);
        G.addEdge(7, 8);
        G.addEdge(8, 10);

        DepthFirstPaths paths = new DepthFirstPaths(G, 0);
        Stack<Integer> path4 = paths.pathTo(5);
        StringBuilder sb = new StringBuilder();
        for (Integer v : path4) {
            sb.append(v + "-");
        }
        System.out.println(sb.substring(0, sb.length() - 1));

    }
}
