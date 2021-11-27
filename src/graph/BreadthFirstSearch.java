package graph;

import linear.Queue;

/**
 * 广度优先搜索
 *
 */
public class BreadthFirstSearch {
    private final boolean[] marked;
    private int count;
    private final Queue<Integer> waitSearch;

    public BreadthFirstSearch(Graph G, int s) {
        this.marked = new boolean[G.V()];
        this.count = 0;
        this.waitSearch = new Queue<>();
        bfs(G, s);
    }

    private void bfs(Graph G, int v) {
        marked[v] = true;
        waitSearch.enqueue(v);
        //循环体内元素入列和出列相继执行，入列元素多于出列元素
        while (!waitSearch.isEmpty()) {
            Integer wait = waitSearch.dequeue();
            //相邻结点全部入列
            for (Integer w : G.adj(wait)) {
                if (!marked[w]) {
                    waitSearch.enqueue(w);
                    marked[w] = true;
                    System.out.println("w = " + w);
                    count++;
                }
            }
        }
        count++;
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
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

        BreadthFirstSearch search = new BreadthFirstSearch(G, 0);
        System.out.println(search.count());
        System.out.println(search.marked(6));
        System.out.println(search.marked(5));
    }
}
