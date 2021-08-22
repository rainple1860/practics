package org.example.graph;

/**
 * @className: UndiTablegraph
 * @description: 无向邻接表图
 * @author: rainple
 * @create: 2021-08-11 23:03
 **/
public class UndiTableGraph {

    private int[][] g;

    public UndiTableGraph(int verb) {
        g = new int[verb][verb];
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g.length; j++) {
                g[i][j] = 0;
            }
        }
    }

    public void link(int i,int j) {
        g[i][j] = 1;
    }

}
