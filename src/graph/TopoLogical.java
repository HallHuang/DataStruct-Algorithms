package graph;

import linear.Stack;

public class TopoLogical {
    private Stack<Integer> order;   //拓扑排序堆栈

    public TopoLogical(Digraph G) {
        DirectedCycle cycle = new DirectedCycle(G);
        if (!cycle.isHasCycle()) {
            DepthFirstOrder dfo = new DepthFirstOrder(G);
            order = dfo.getReversePost();
        }
    }

    public boolean hasCycle() {
        return order == null;
    }

    public Stack<Integer> getOrder() {
        return order;
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(6);
        G.addEdge(0, 2);
        G.addEdge(0, 3);
        G.addEdge(1, 3);
        G.addEdge(2, 4);
        G.addEdge(3, 4);
        G.addEdge(4, 5);

        TopoLogical tll = new TopoLogical(G);
        Stack<Integer> order = tll.getOrder();
        StringBuilder sb = new StringBuilder();
        for (Integer item : order) {
            sb.append(item + " -> ");
        }
        int index = sb.lastIndexOf(" -> ");
        String substring = sb.substring(0, index);
        System.out.println(substring);
    }
}
