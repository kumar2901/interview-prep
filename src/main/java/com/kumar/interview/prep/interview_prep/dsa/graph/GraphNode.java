package com.kumar.interview.prep.interview_prep.dsa.graph;

public class GraphNode {

    int v;
    int weight;

    GraphNode(int v, int weight) {
        this.v = v;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "(" + v + "," + weight + ")";
    }
}
