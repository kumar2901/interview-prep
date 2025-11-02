package com.kumar.interview.prep.interview_prep.dsa.recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubsetsAndSubsequence {

    /*
    78. Subsets

    [1,2]
    step1: s(0,[])

          s(0,<1>)   s(0,<>)

     step 2: s(1,<1,2>) s(1,<1>) s(1,<2>) s(1,<>)
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        subsets(0, nums, new ArrayList<>(), result);

        return result;
    }

    /*
    Without duplicate
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
     * @param i index
     * @param nums input array
     * @param path list along the path
     * @param result result
     */
    private void subsetsWithDuplicate(int i, int[] nums, List<Integer> path, List<List<Integer>> result) {
        if (i >= nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        path.add(nums[i]);
        subsetsWithDuplicate(i + 1, nums, path, result);
        path.removeLast();
        int next=i+1;
        while(next<nums.length && nums[next]==nums[i]){
            next++;
        }
        subsetsWithDuplicate(next, nums, path, result);
    }

    private List<String> subsequence(String str) {
        List<String>result=new ArrayList<>();
        if(null==str || str.isEmpty()){
            return result;
        }
        subsequence(str,0,new StringBuilder(),result);
        return result;
    }

    private  void subsequence(String str, int i, StringBuilder path, List<String> result) {
        if(i>=str.length()){
            result.add(path.toString());
            return;
        }
        path.append(str.charAt(i));
        subsequence(str,i+1,path,result);
        path.deleteCharAt(path.length()-1);
        subsequence(str,i+1,path,result);
    }

    public  void main(String[] args) {
        System.out.println(subsets(new int[] {1,2,3}));
        System.out.println(subsets(new int[] {1,2}));
        List<List<Integer>> result=new ArrayList<>();
        int[] nums = {1, 2, 2};
        subsetsWithDuplicate(0, nums,new ArrayList<>(),result);
        System.out.println(result);
        System.out.println(subsequence("abc"));
    }


}
