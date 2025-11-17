package com.kumar.interview.prep.interview_prep.dsa.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class IntervalsBasics {

    /**
     * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an
     * array of the non-overlapping intervals that cover all the intervals in the input.
     * <p>
     * <br/>
     * Input: intervals = [[1,3],[2,6],[8,10],[15,18]] <br/>
     * Output: [[1,6],[8,10],[15,18]]
     * </p>
     *
     * @param intervals
     *            intervals
     * @return merge intervals
     */
    public int[][] mergeIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> result = new ArrayList<>();
        int[] prev = null;
        for (int[] interval : intervals) {
            if (prev == null || prev[1] < interval[0]) {
                result.add(interval);
                prev = interval;
            } else {

                prev[0] = Math.min(prev[0], interval[0]);
                prev[1] = Math.max(prev[1], interval[1]);

            }

        }

        return result.toArray(new int[0][2]);

    }

    private static void print(int[][] intervals) {
        for (int[] in : intervals) {
            System.out.println(Arrays.toString(in));
        }
    }

    public void main(String[] args) {
        System.out.println("merge Intervals");
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
       print(mergeIntervals(intervals));

        int[][] intervals1 = {{1, 3}, {4, 6}, {8, 10}, {15, 18}};
        System.out.println("insert Intervals");
       print(insertInterval(intervals1,new int[] {5,7}));

    }


    /**
     * <p>
     * <br/>Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
     * <br/>Output: [[1,5],[6,9]]
     * </p>
     * @param intervals existing intervals
     * @param newInterval new interval
     * @return upsert interval
     */

    private int[][] insertInterval(int[][] intervals, int[] newInterval) {
        List<int[]>result=new ArrayList<>();

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));


        //add all interval before new intervals
        int i=0;
        while(i<intervals.length && intervals[i][1]<newInterval[0]){
            result.add(intervals[i]);
            i++;
        }

        //overlapping intervals
        while(i<intervals.length && intervals[i][0]<=newInterval[1]){
            newInterval[0]=Math.min(newInterval[0],intervals[i][0]);
            newInterval[1]=Math.max(newInterval[1],intervals[i][1]);
            i++;

        }
        result.add(newInterval);
        while(i<intervals.length){
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[0][2]);
    }
}
