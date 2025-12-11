package com.kumar.interview.prep.interview_prep.dsa.sliding_window;

public class ContainerWithMostWater {

    private int findContainerWithMostWater(int[] height) {
        int l = 0, r = height.length - 1;
        int maxArea = 0;
        while (l <= r) {
            int h = Math.min(height[l], height[r]);
            maxArea = Math.max(maxArea, h * (r - l));
            // System.out.println("l="+l+" r="+r+" maxArea="+maxArea+" h="+h);
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return maxArea;

    }

    void main() {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("ContainerWithMostWater= " + findContainerWithMostWater(height));
    }

}
