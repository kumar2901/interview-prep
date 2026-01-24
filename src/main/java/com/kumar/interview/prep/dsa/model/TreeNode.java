package com.kumar.interview.prep.dsa.model;

import lombok.Data;

@Data
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

}
