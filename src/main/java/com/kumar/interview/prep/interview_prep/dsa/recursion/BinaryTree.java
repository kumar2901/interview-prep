package com.kumar.interview.prep.interview_prep.dsa.recursion;

import com.kumar.interview.prep.interview_prep.dsa.model.TreeNode;

import java.util.concurrent.atomic.AtomicBoolean;

public class BinaryTree {

    /**
     * You are given:
     *
     * <br/>
     * Root of a binary tree
     *
     * <br/>
     * Two nodes: p and q
     *
     * <br/>
     * Return their LCA only if both nodes exist in the tree. <br/>
     * </>Otherwise return null.
     *
     * @param root
     *            root
     * @param p
     *            p
     * @param q
     *            q
     * @return lca
     */
    private TreeNode LCA2(TreeNode root, TreeNode p, TreeNode q) {

        AtomicBoolean isPAvailable = new AtomicBoolean(false);
        AtomicBoolean isQAvailable = new AtomicBoolean(false);

        TreeNode node = lca(root, p, q, isPAvailable, isQAvailable);
        if (isPAvailable.get() && isQAvailable.get()) {
            return node;
        }
        return null;

    }

    private TreeNode lca(TreeNode root, TreeNode p, TreeNode q, AtomicBoolean isPAvailable,
            AtomicBoolean isQAvailable) {
        if (root == null) {
            return null;
        }
        if (root == p) {
            isPAvailable.set(true);
            return root;
        }
        if (root == q) {
            isQAvailable.set(true);
            return root;
        }
        TreeNode left = lca(root.getLeft(), p, q, isPAvailable, isQAvailable);
        TreeNode right = lca(root.getRight(), p, q, isPAvailable, isQAvailable);
        if (left != null && right != null) {
            return root;
        }

        return left != null ? left : right;

    }
}
