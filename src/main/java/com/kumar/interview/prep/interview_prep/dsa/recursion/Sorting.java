package com.kumar.interview.prep.interview_prep.dsa.recursion;

import java.util.Arrays;

public class Sorting {

    private void bubble(int[] nums, int i, int j) {
        if (i >= nums.length) {
            return;
        }
        if (j >= nums.length) {
            i = i + 1;
            bubble(nums, i, i + 1);
            return;
        }
        if (nums[i] > nums[j]) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;

        }
        bubble(nums, i, j + 1);
    }

    public void main(String[] args) {
        int[] nums = {1, 3, 54, 65, 43, 5, 7, 8};
        bubble(nums, 0, 1);
        System.out.println(Arrays.toString(nums));
    }
}
