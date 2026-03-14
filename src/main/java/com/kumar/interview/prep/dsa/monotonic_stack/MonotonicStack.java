package com.kumar.interview.prep.dsa.monotonic_stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MonotonicStack {

    /**
     * Problem: Daily Temperatures (Next Warmer Day)
     *
     * Given an integer array `temps` where `temps[i]` represents the temperature on day `i`, determine how many days
     * one has to wait after day `i` to encounter a warmer temperature.
     *
     * For each day `i`, find the smallest index `j` such that:
     *
     * j > i AND temps[j] > temps[i]
     *
     * If such a day exists, store the difference (j - i) in the result array. If there is no future day with a higher
     * temperature, store -1 for that position.
     *
     * Return an integer array `result` where:
     *
     * result[i] = number of days until a warmer temperature result[i] = -1 if no warmer temperature exists in the
     * future
     *
     * <p>
     * Example:
     *
     * Input: temps = [73, 74, 75, 71, 69, 72, 76, 73]
     *
     * Output: [1, 1, 4, 2, 1, 1, -1, -1]
     * </p>
     * Explanation: <br/>
     * Day 0 (73) -> warmer day at index 1 -> wait 1 day <br/>
     * Day 1 (74) -> warmer day at index 2 -> wait 1 day <br/>
     * Day 2 (75) -> warmer day at index 6 -> wait 4 days <br/>
     * Day 3 (71) -> warmer day at index 5 -> wait 2 days <br/>
     * Day 4 (69) -> warmer day at index 5 -> wait 1 day <br/>
     * Day 5 (72) -> warmer day at index 6 -> wait 1 day <br/>
     * Day 6 (76) -> no warmer day -> -1 <br/>
     * Day 7 (73) -> no warmer day -> -1
     *
     * <p>
     * Follow-up: Solve the problem in O(n) time using a monotonic stack.
     * </p>
     *
     *
     * <p>
     * <br/>
     * TC: O(n)-> Even though it has nested while loop but total number of stack operations is bounded by 2n
     * </p>
     */
    private int[] dailyTemperature(int[] nums) {

        Deque<Integer> stack = new ArrayDeque<>();
        // int idx = 0;
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);

        for (int i = 0; i < nums.length; i++) {
            System.out.println("i=" + i + " stack=" + stack);
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                int idx = stack.pop();
                result[idx] = i - idx;
            }
            stack.push(i);
        }

        System.out.println("result:" + Arrays.toString(result) + " stack=" + stack);

        return result;

    }

    void main() {
        System.out.println(Arrays.toString(dailyTemperature(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
    }
}
