package com.kumar.interview.prep.interview_prep.dsa.Tree.binary;

import com.kumar.interview.prep.interview_prep.dsa.model.TreeNode;

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

    void main() {
        TreeNode root = new TreeNode(1);

        TreeNode left = new TreeNode(2);
        left.setRight(new TreeNode(5));

        TreeNode right = new TreeNode(3);
        right.setRight(new TreeNode(4));

        root.setLeft(left);
        root.setRight(right);

        System.out.println(rightSideView(root));
    }
}
