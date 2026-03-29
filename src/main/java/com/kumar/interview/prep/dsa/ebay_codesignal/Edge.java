package com.kumar.interview.prep.dsa.ebay_codesignal;

class Edge implements Comparable<Edge> {
    String u;
    String v;
    int weight;

    Edge(String u, String v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "(" + u + ", " + v + ") -> " + weight;
    }

    @Override
    public int compareTo(Edge other) {
        return other.weight - this.weight; // DESC for MaxST
    }
}
