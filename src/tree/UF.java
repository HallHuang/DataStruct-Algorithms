package tree;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 并查集
 * 成员数组的索引值代表数据元素，其值可以是分组号或后继结点的索引表示值
 */
public class UF {

    private final int[] eleAndGroup;  //分组标识号数组 或者 索引代表的结点的父结点标识数组
    private int count;  //分组数，默认为元素个数
    private final int[] sz;   //UF_TREE_Weighted,每个根结点下子结点总数

    public UF(int N) {
        this.count = N;
        this.eleAndGroup = new int[N];
        for (int i = 0; i < eleAndGroup.length; i++) {
            eleAndGroup[i] = i;
        }

        this.sz = new int[N];
        Arrays.fill(sz, 1);
    }

    public int count() {
        return count;
    }

    //获取索引值p下的分组标识号或者后继结点
    public int find(int p) {
        //return eleAndGroup[p];

        /*-------UF_Tree-------*/
        while (true) {
            if (p == eleAndGroup[p]) {
                return p;
            }
            p = eleAndGroup[p];
        }
    }

    //判断两结点是否在同一组
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
//        if (connected(p, q)){
//            return;
//        }
//
//        int pGroup = find(p);
//        int qGroup = find(q);
//        for (int i = 0; i < eleAndGroup.length; i++) {
//            if (find(i) == pGroup){//遍历以使分组号相同
//                eleAndGroup[i] = qGroup;
//            }
//        }
//        this.count--;

        /*-------UF_Tree-------*/
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }

        //UF_Tree, 一组的终父结点的父结点设置为二组的终父结点
        //eleAndGroup[pRoot] = qRoot;
        //UF_Tree_Weighted,小树合并到大树上
        if (sz[pRoot] < sz[qRoot]) {
            eleAndGroup[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            eleAndGroup[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
        this.count--;
    }

    public static void main(String[] args) {
        UF uf = new UF(5);
        System.out.println("默认情况下，并查集中有：" + uf.count() + "个分组");

        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("请输入第一个要合并的元素：");
            int p = sc.nextInt();
            System.out.println("请输入第二个要合并的元素：");
            int q = sc.nextInt();

            //判断这两个元素是否已经在同一组了
            if (uf.connected(p, q)) {
                System.out.println(p + "元素和" + q + "元素已经在同一个组中了");
                continue;
            }

            uf.union(p, q);
            System.out.println("当前并查集中还有：" + uf.count() + "个分组");
        } while (true);
    }
}
