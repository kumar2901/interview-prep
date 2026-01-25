package com.kumar.interview.prep.dsa.hash_map;

import java.util.HashMap;
import java.util.Map;

public class SparseVectorExample {
    static void main() {
        SparseVector v1 = new SparseVector(new int[]{0, 2, 0, 3, 0, 1});
        SparseVector v2 = new SparseVector(new int[]{1, 0, 0, 4, 0, 1});
        int ans = v1.dotProduct(v2);
        System.out.println(ans);
    }
}

class SparseVector {
    Map<Integer, Integer> map;
    public SparseVector(int[] nums) {
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                map.put(i, nums[i]);
            }
        }

    }

    public int dotProduct(SparseVector vec) {
        if (vec.map.size() > this.map.size()) {
            return dotProduct(vec.map, this.map);
        }
        return dotProduct(this.map, vec.map);
    }

    private int dotProduct(Map<Integer, Integer> mapA, Map<Integer, Integer> mapB) {
        int result = 0;
        for (Map.Entry<Integer, Integer> entry : mapA.entrySet()) {

            int key = entry.getKey();
            result += mapB.getOrDefault(key, 0) * entry.getValue();
        }

        return result;
    }
}
