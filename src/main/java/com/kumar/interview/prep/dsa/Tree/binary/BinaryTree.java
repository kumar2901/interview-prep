package com.kumar.interview.prep.dsa.Tree.binary;

import com.kumar.interview.prep.dsa.model.TreeNode;

import java.util.*;

public class BinaryTree {

    /**
     * You are given a root of a binary tree that has n number of nodes. You have to return the right-side view in the
     * form of a list. A right-side view of a binary tree is the data of the nodes that are visible when the tree is
     * viewed from the right side.
     *
     * @param root
     *            root
     * @return right side view
     */
    private List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        Deque<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {

            int size = queue.size();
            int count = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                count++;

                if (count == size) {
                    result.add(node.getVal());
                }
                if (node.getLeft() != null) {
                    queue.offer(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue.offer(node.getRight());
                }

            }

        }

        return result;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null || p.getVal() != q.getVal()) {
            return false;
        }

        boolean left = isSameTree(p.getLeft(), q.getLeft());
        boolean right = isSameTree(p.getRight(), q.getRight());

        return left && right;

    }

    public TreeNode invertTree(TreeNode root) {
        if (null == root) {
            return null;
        }
        TreeNode temp = root.getLeft();
        root.setLeft(root.getRight());
        root.setRight(temp);

        invertTree(root.getLeft());
        invertTree(root.getRight());

        return root;
    }

    private void print(TreeNode root) {

        if (root == null) {
            return;
        }
        System.out.print(root.getVal() + " ");
        print(root.getLeft());
        print(root.getRight());
    }

    void main() {
        TreeNode root = new TreeNode(1);

        TreeNode left = new TreeNode(2);
        left.setRight(new TreeNode(5));

        TreeNode right = new TreeNode(3);
        right.setRight(new TreeNode(4));

        root.setLeft(left);
        root.setRight(right);

        System.out.println("rightSideView " + rightSideView(root));

        TreeNode root1 = new TreeNode(1);

        TreeNode left1 = new TreeNode(2);
        left.setRight(new TreeNode(5));

        TreeNode right1 = new TreeNode(3);

        root1.setLeft(left1);
        root1.setRight(right1);
        System.out.println("isSameTree=" + isSameTree(root, root1));

        TreeNode invertRoot = invertTree(root1);
        System.out.println("Invert Tree ");
        print(invertRoot);
        System.out.println("\nInvert tree complete");

    }
}
