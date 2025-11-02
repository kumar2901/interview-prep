package com.kumar.interview.prep.interview_prep.dsa.recursion;

public class BinarySearch {

    /*
     * Search in rotated sorted array may contains duplicates
     */
    public int rotatedBinarySearch(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) {
            return mid;
        }
        if (arr[left] < arr[mid]) {
            if (target >= arr[left] && target <= arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        } else if (arr[left] > arr[mid]) {
            if (target >= arr[mid] && target <= arr[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        } else { // difficult part handling duplicates
            left++;

        }
        return rotatedBinarySearch(arr, target, left, right);
    }
}
