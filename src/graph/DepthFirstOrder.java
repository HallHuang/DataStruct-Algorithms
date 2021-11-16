package graph;

import linear.Stack;

public class DepthFirstOrder {

    private boolean[] marked;   //标记是否搜索过
    private Stack<Integer> reversePost; //从底往上保存深度搜索顶点

    public DepthFirstOrder(Digraph G) {
        this.marked = new boolean[G.V()];
        this.reversePost = new Stack<>();
        for (int i = 0; i < G.V(); i++) {
            if (!marked[i]) {
                dfs(G, i);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (Integer w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        reversePost.push(v);    //当没有子结点或者子结点已经被搜索过时元素入栈
    }

    public Stack<Integer> getReversePost() {
        return reversePost;
    }
}
