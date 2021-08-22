package org.example.graph;

/**
 * @className: Test
 * @description:
 * @author: rainple
 * @create: 2021-08-11 23:07
 **/
public class Test {

    public static void main(String[] args) {
        UndiTableGraph graph = new UndiTableGraph(5);
        graph.link(0,1);
        graph.link(1,3);
        graph.link(4,1);
    }
}
