package com.kumar.interview.prep.dsa.ebay_codesignal;

import java.util.HashMap;
import java.util.Map;

class DSU {
    Map<String, String> parent = new HashMap<>();

    public String find(String x) {
        parent.putIfAbsent(x, x);

        if (!parent.get(x).equals(x)) {
            parent.put(x, find(parent.get(x))); // path compression
        }
        return parent.get(x);
    }

    public boolean union(String a, String b) {
        String pa = find(a);
        String pb = find(b);

        if (pa.equals(pb))
            return false; // already connected

        parent.put(pa, pb);
        return true;
    }
}
