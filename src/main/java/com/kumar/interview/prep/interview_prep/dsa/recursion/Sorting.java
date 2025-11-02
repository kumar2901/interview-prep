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

    private void mergeSort(int[] nums, int l, int r) {
        if (l < r) {
            int mid = (l + r) / 2;
            mergeSort(nums, l, mid);
            mergeSort(nums, mid + 1, r);
            merge(nums, l, mid, r);
        }
    }

    private void merge(int[] nums, int l, int mid, int r) {
        // System.out.println("merge l=" + l + " mid=" + mid + " r=" + r);
        int l1 = mid - l + 1;
        int l2 = r - mid;

        int[] L = new int[l1];
        int[] R = new int[l2];

        for (int i = 0; i < l1; i++) {
            L[i] = nums[l + i];
        }

        for (int i = 0; i < l2; i++) {
            R[i] = nums[mid + i + 1];
        }

        int i = 0;
        int j = 0;
        int k = l;

        while (i < l1 && j < l2) {
            if (L[i] <= R[j]) {
                nums[k] = L[i];
                i++;
            } else {
                nums[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < l1) {
            nums[k] = L[i];
            i++;
            k++;
        }
        while (j < l2) {
            nums[k] = R[j];
            j++;
            k++;
        }
    }

    private void swap(int[] nums, int k, int i) {
        int temp = nums[k];
        nums[k] = nums[i];
        nums[i] = temp;
    }

    public void main(String[] args) {
        int[] nums = {1, 3, 54, 65, 43, 5, 7, 8};
        bubbleSort(nums, 0, 1);
        System.out.println("Bubble sort " + Arrays.toString(nums));

        int[] nums1 = {1, 3, 54, 65, 43, 5, 7, 8};
        selectionSort(nums1, 0, 1, 0);
        System.out.println("selection Sort " + Arrays.toString(nums1));

        int[] nums2 = {100, 80, 3, 56, 43, 8, 7, 4};
        mergeSort(nums2, 0, 7);
        System.out.println("merge sort result= " + Arrays.toString(nums2));
    }
}
