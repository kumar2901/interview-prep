package com.kumar.interview.prep.dsa.arrays;

import java.util.Arrays;

public class VisaHackerRank {

    /**
     * <p>
     * Each investor is available on a range of days: [firstDay[i], lastDay[i]] You can schedule at most one meeting per
     * day Each investor can be met once
     * </p>
     *
     *
     * Goal: maximize number of meetings
     *
     * @return
     */
    private static int coutNumberOfMeetings(int[] firstDay, int[] lastDay) {

        int n = firstDay.length;
        int[][] investors = new int[n][2];
        for (int i = 0; i < n; i++) {
            investors[i][0] = firstDay[i];
            investors[i][1] = lastDay[i];
        }

        Arrays.sort(investors, (a, b) -> a[1] - b[1]);
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(investors[i]));
        }

        int currentDay = 1;
        int count = 0;
        for (int[] inv : investors) {
            int start = inv[0];
            int end = inv[1];

            int meetingDay = Math.max(currentDay, start);
            if (meetingDay <= end) {
                count++;
                currentDay = meetingDay + 1;
            }

        }
        return count;

    }

    public static int countBalancingElements(int[] arr) {

        int n = arr.length;
        long totalOddSum = 0, totalEvenSum = 0;

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                totalEvenSum += arr[i];
            } else {
                totalOddSum += arr[i];
            }

        }

        System.out.println("totalEvenSum=" + totalEvenSum + " totalOddSum=" + totalOddSum);

        long leftEven = 0;
        long leftOdd = 0;
        int result = 0;

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                totalEvenSum -= arr[i];
            } else {
                totalOddSum -= arr[i];
            }

            long newEven = leftEven + totalOddSum;
            long newOdd = leftOdd + totalEvenSum;
            if (newEven == newOdd) {
                result++;
            }

            if (i % 2 == 0) {
                leftEven += arr[i];
            } else {
                leftOdd += arr[i];
            }
        }

        return result;

    }

    static void main() {

        Point3D p1 = new Point3D(1, 2, 3);
        Point3D p2 = new Point3D(4, 5, 6);

        double d2 = p1.distance2D(p2);
        double d3 = p1.distance3D(p2);

        System.out.print("2D distance = ");
        p1.printDistance(d2);

        System.out.print("3D distance = ");
        p1.printDistance(d3);

        System.out.println(coutNumberOfMeetings(new int[]{1, 2, 3, 3, 3}, new int[]{2, 2, 3, 4, 4}));
        System.out.println(countBalancingElements(new int[]{5, 5, 2, 5, 8}));
        System.out.println(countBalancingElements(new int[]{2, 1, 6, 4}));

    }
}

class Point2D {
    int x;
    int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    double distance2D(Point2D p) {
        int dx = this.x - p.x;
        int dy = this.y - p.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}

class Point3D extends Point2D {
    int z;

    public Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    double distance3D(Point3D p) {
        int dx = this.x = p.x;
        int dy = this.y = p.y;
        int dz = this.z = p.z;

        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    void printDistance(double d) {
        System.out.println((int) Math.ceil(d));
    }
}
