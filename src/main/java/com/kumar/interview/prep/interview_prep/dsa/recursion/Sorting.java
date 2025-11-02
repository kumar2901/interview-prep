package com.kumar.interview.prep.interview_prep.dsa.recursion;

import java.util.Arrays;

public class Sorting {

    private void bubbleSort(int[] nums, int i, int j) {
        if (i >= nums.length) {
            return;
        }
        if (j >= nums.length) {
            i = i + 1;
            bubbleSort(nums, i, i + 1);
            return;
        }
        if (nums[i] > nums[j]) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;

        }
        bubbleSort(nums, i, j + 1);
    }

    private void selectionSort(int[] nums, int i, int j, int minIndex) {

        if (i >= nums.length) {
            return;
        }
        if (j >= nums.length) {
            int temp = nums[minIndex];
            nums[minIndex] = nums[i];
            nums[i] = temp;

            i = i + 1;
            selectionSort(nums, i, i + 1, i);
            return;
        }
        if (nums[minIndex] > nums[j]) {
            minIndex = j;

        }
        selectionSort(nums, i, j + 1, minIndex);
    }

    public void main(String[] args) {
        int[] nums = {1, 3, 54, 65, 43, 5, 7, 8};
        bubbleSort(nums, 0, 1);
        System.out.println(Arrays.toString(nums));

        int[] nums1 = {1, 3, 54, 65, 43, 5, 7, 8};
        selectionSort(nums1, 0, 1, 0);
        System.out.println(Arrays.toString(nums1));
    }
}
