package com.kumar.interview.prep.interview_prep.dsa.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubsetsAndSubsequence {

    /*
     * 78. Subsets
     *
     * [1,2] step1: s(0,[])
     *
     * s(0,<1>) s(0,<>)
     *
     * step 2: s(1,<1,2>) s(1,<1>) s(1,<2>) s(1,<>)
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        subsets(0, nums, new ArrayList<>(), result);

        return result;
    }

    /*
     * Without duplicate
     */

    private void subsets(int i, int[] nums, List<Integer> path, List<List<Integer>> result) {
        if (i >= nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        path.add(nums[i]);
        subsets(i + 1, nums, path, result);
        path.removeLast();
        subsets(i + 1, nums, path, result);
    }

    /**
     * pre-requisite call after sorting nums
     *
     * @param i
     *            index
     * @param nums
     *            input array
     * @param path
     *            list along the path
     * @param result
     *            result
     */
    private void subsetsWithDuplicate(int i, int[] nums, List<Integer> path, List<List<Integer>> result) {
        if (i >= nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        path.add(nums[i]);
        subsetsWithDuplicate(i + 1, nums, path, result);
        path.removeLast();
        int next = i + 1;
        while (next < nums.length && nums[next] == nums[i]) {
            next++;
        }
        subsetsWithDuplicate(next, nums, path, result);
    }

    private List<String> subsequence(String str) {
        List<String> result = new ArrayList<>();
        if (null == str || str.isEmpty()) {
            return result;
        }
        subsequence(str, 0, new StringBuilder(), result);
        return result;
    }

    private void subsequence(String str, int i, StringBuilder path, List<String> result) {
        if (i >= str.length()) {
            result.add(path.toString());
            return;
        }
        path.append(str.charAt(i));
        subsequence(str, i + 1, path, result);
        path.deleteCharAt(path.length() - 1);
        subsequence(str, i + 1, path, result);
    }

    /**
     * Subsets Sum Equal to Target
     *
     * @param nums
     */
    public List<List<Integer>> subsetsSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        subsetsEqualToTarget(nums, 0, 0, new ArrayList<>(), target, result);
        return result;
    }

    private void subsetsEqualToTarget(int[] nums, int i, int sum, ArrayList<Integer> path, int target,
            List<List<Integer>> result) {
        if (i >= nums.length) {
            if (sum == target) {
                result.add(new ArrayList<>(path));
            }
            return;
        }
        path.add(nums[i]);
        subsetsEqualToTarget(nums, i + 1, sum + nums[i], path, target, result);
        path.removeLast();
        subsetsEqualToTarget(nums, i + 1, sum, path, target, result);
    }

    /**
     * 39. Combination Sum
     * <p>
     * Example. 1 <br/>
     * Input: candidates = [2,3,6,7], target = 7 <br/>
     * Output: [[2,2,3],[7]] </>
     * <p>
     * Example. 2 <br/>
     * Input: candidates = [2,3,5], target = 8 <br/>
     * Output: [[2,2,2,2],[2,3,3],[3,5]]
     * </p>
     *
     * @param candidates
     *            candidates
     * @param target
     *            target
     * @return list of all unique combinations of candidates where the chosen numbers sum to target
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

        combinationsWithRepetition(candidates, 0, 0, new ArrayList<>(), result, target);
        return result;

    }

    private void combinationsWithRepetition(int[] candidates, int start, int pathSum, ArrayList<Integer> path,
            List<List<Integer>> result, int target) {

        if (pathSum == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        if (pathSum > target) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (pathSum + candidates[i] > target) {
                break;
            }

            path.add(candidates[i]);
            pathSum = pathSum + candidates[i];
            combinationsWithRepetition(candidates, i, pathSum, path, result, target);
            path.removeLast();
            pathSum = pathSum - candidates[i];

        }
    }

    /**
     * 40. Combination Sum II
     * <p>
     * Example 1: Input: candidates = [10,1,2,7,6,1,5], target = 8 Output: [ [1,1,6], [1,2,5], [1,7], [2,6] ]
     * <p/>
     * <p>
     * Example 2: Input: candidates = [2,5,2,1,2], target = 5 Output: [ [1,2,2], [5] ]
     * </p>
     *
     * @param candidates
     *            input
     * @param start
     *            start index
     * @param pathSum
     *            sum elements along the path
     * @param path
     *            elements along the path
     * @param result
     *            result
     * @param target
     *            target value
     */
    private void combinationsWithoutRepetition(int[] candidates, int start, int pathSum, ArrayList<Integer> path,
            List<List<Integer>> result, int target) {

        if (pathSum == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        if (pathSum > target) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {

            // pruning
            if (pathSum + candidates[i] > target)
                break;

            // skip duplicates
            if (i > start && candidates[i] == candidates[i - 1])
                continue;

            path.add(candidates[i]);
            pathSum = pathSum + candidates[i];
            combinationsWithoutRepetition(candidates, i + 1, pathSum, path, result, target);
            path.removeLast();
            pathSum = pathSum - candidates[i];

        }
    }

    /**
     * Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
     *
     * Only numbers 1 through 9 are used. Each number is used at most once.
     * <p>
     * Example 1 Input: k = 3, n = 7 Output: [[1,2,4]]
     * <p/>
     * <p>
     * Example 2:
     *
     * Input: k = 3, n = 9 Output: [[1,2,6],[1,3,5],[2,3,4]]
     * </p>
     *
     * @param k
     *            size
     * @param n
     *            target
     * @return result
     */
    public List<List<Integer>> combinationSum3(int k, int n) {

        List<List<Integer>> result = new ArrayList<>();

        combinationsIII(1, 0, new ArrayList<>(), result, k, n);

        return result;

    }

    /**
     * 216. Combination Sum III
     *
     * @param start
     *            start
     * @param pathSum
     *            pathSum
     * @param path
     *            path
     * @param result
     *            result
     * @param k
     *            size
     * @param target
     *            target
     */
    private void combinationsIII(int start, int pathSum, List<Integer> path, List<List<Integer>> result, int k,
            int target) {

        if (path.size() > k) {
            return;
        }
        if (path.size() == k && pathSum == target) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i <= 9; i++) {
            if (pathSum + i > target) {
                break;
            }
            pathSum = pathSum + i;
            path.add(i);
            combinationsIII(i + 1, pathSum, path, result, k, target);
            pathSum = pathSum - i;
            path.removeLast();
        }
    }

    public List<List<Integer>> nonDecreasingSubSequence(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        nonDecreasingSubsequence(nums, 0, new ArrayList<>(), result);
        return result;
    }

    /**
     * 491. Non-decreasing Subsequences
     * <p/>
     * Example 1:
     *
     * Input: nums = [4,6,7,7] Output: [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
     * <p/>
     * Example 2:
     *
     * Input: nums = [4,4,3,2,1] Output: [[4,4]]
     *
     * @param nums
     *            input
     * @param start
     *            start index
     * @param path
     *            path
     * @param result
     *            result
     */
    private void nonDecreasingSubsequence(int[] nums, int start, List<Integer> path, List<List<Integer>> result) {
        if (path.size() > 1) {
            result.add(new ArrayList<>(path));
        }
        Set<Integer> set = new HashSet<>();
        for (int i = start; i < nums.length; i++) {
            if (!path.isEmpty() && nums[i] < path.getLast()) {
                continue;
            }
            if (set.contains(nums[i])) {
                continue;
            }
            path.add(nums[i]);
            set.add(nums[i]);
            nonDecreasingSubsequence(nums, i + 1, path, result);
            path.removeLast();
        }
    }

    public void main(String[] args) {
        System.out.println(subsets(new int[]{1, 2, 3}));
        System.out.println(subsets(new int[]{1, 2}));

        List<List<Integer>> result = new ArrayList<>();
        int[] nums = {1, 2, 2};
        subsetsWithDuplicate(0, nums, new ArrayList<>(), result);
        System.out.println(result);

        System.out.println(subsequence("abc"));

        // subsets sum
        int[] nums1 = {1, 2, 4, 5, 7, 9};
        System.out.println(subsetsSum(nums1, 9));

        // combination sum
        System.out.println("combination sum = " + combinationSum(new int[]{2, 3, 6, 7}, 7));

        System.out.println("combination sum3 = " + combinationSum3(3, 9));
        System.out.println("non decreasing subsequence = " + nonDecreasingSubSequence(new int[]{4, 6, 7, 7}));

    }

}
