package com.kumar.interview.prep.interview_prep.dsa.arrays;

public class MaxProdSubarray {

    public int maxProduct(int[] nums) {
        int maxProd = Integer.MIN_VALUE;

        int currMax = 1;
        int currMin = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                maxProd = Math.max(maxProd, nums[i]); // if max prod if negative
                currMin = 1;
                currMax = 1;
                continue;
            }

            int temp = nums[i] * currMax;
            currMax = Math.max(temp, Math.max(nums[i] * currMin, nums[i]));
            currMin = Math.min(temp, Math.min(nums[i] * currMin, nums[i]));
            maxProd = Math.max(maxProd, currMax);
           // System.out.println("currMax=" + currMax + " currMin=" + currMin + " maxProd=" + maxProd + " i=" + i);

        }

        return maxProd;
    }

    void main() {
        System.out.println("maxProduct=" + maxProduct(new int[]{2, 3, -2, 4}));
        System.out.println("maxProduct=" + maxProduct(new int[]{-2,0,-1}));
    }
}
