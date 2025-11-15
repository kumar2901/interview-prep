package com.kumar.interview.prep.interview_prep.dsa.model;

import lombok.Data;

@Data
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }

}
