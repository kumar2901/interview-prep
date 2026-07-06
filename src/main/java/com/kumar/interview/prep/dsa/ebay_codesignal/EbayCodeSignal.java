package com.kumar.interview.prep.dsa.ebay_codesignal;

import java.util.Arrays;

public class EbayCodeSignal {

    /**
     * Applies a box blur effect to a grayscale image.
     *
     * <p>
     * The input image is represented as a 2D integer matrix where image[i][j] denotes the intensity (0 to 255) of the
     * pixel at row i and column j.
     * </p>
     *
     * <p>
     * For each pixel (i, j), the blur effect replaces its value with the average intensity of all pixels (k, l) such
     * that:
     * </p>
     *
     * <pre>
     *     abs(i - k) <= radius
     *     abs(j - l) <= radius
     * </pre>
     *
     * <p>
     * This defines a square neighborhood centered at (i, j) with side length (2 * radius + 1). If the neighborhood
     * extends beyond the image boundaries, only valid pixels within the image are considered.
     * </p>
     *
     * <p>
     * The average is computed using integer division (fractional parts are discarded).
     * </p>
     *
     * @param image
     *            a non-null m x n matrix representing a grayscale image
     * @param radius
     *            a non-negative integer specifying the blur radius
     * @return a new m x n matrix where each pixel contains the blurred value Time Complexity: O(m * n) when implemented
     *         using 2D prefix sums. Space Complexity: O(m * n) for the result and prefix matrix.
     */
    private static int[][] blurImage(int[][] image, int radius) {

        if (radius == 0) {
            return image;
        }

        int m = image.length;
        int n = image[0].length;

        int[][] result = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int avg = calculateAvg(image, radius, i, j, m, n);
                result[i][j] = avg;

            }
        }
        return result;
    }

    private static int calculateAvg(int[][] image, int radius, int i, int j, int m, int n) {
        int sum = 0;
        int count = 0;
        for (int r = i - radius; r <= i + radius; r++) {
            for (int c = j - radius; c <= j + radius; c++) {
                if (r >= 0 && r < m && c >= 0 && c < n) {
                    sum += image[r][c];
                    count++;
                }
            }
        }

        if (sum == 0 || count == 0) {
            return 0;
        }

        return sum / count;
    }

    /**
     * You are given two integer arrays:
     *
     * capacity[i] - the number of minutes battery i can power the phone recharge[i] - the number of minutes battery i
     * needs to fully recharge
     *
     * You are also given an integer T representing the total number of minutes the phone must run continuously.
     *
     * All batteries are initially fully charged.
     *
     * Batteries must be used in cyclic order: 0 → 1 → 2 → ... → n-1 → 0 → 1 → ...
     *
     * When a battery i is used: - The phone runs for capacity[i] minutes. - Immediately after it is depleted, it starts
     * recharging. - It becomes available again after recharge[i] minutes.
     *
     * When it is time to use the next battery in cyclic order: - If that battery is still recharging, the process fails
     * immediately and the function must return -1.
     *
     * The process continues until the total running time reaches or exceeds T.
     *
     * Return: - The total number of battery usages required to reach at least T minutes. - Return -1 if it is
     * impossible to keep the phone running due to recharge constraints.
     *
     * Notes: - Switching batteries takes no time. - The phone must run continuously without idle gaps. - All batteries
     * are fully charged at time 0. Example: arr1 = [12, 3, 5, 18] arr2 = [8, 1, 4, 9] x = 16 output: 3
     *
     * <br/>
     * input [12, 3, 5, 18], [8, 1, 4, 9], 16) # 3
     *
     *
     * <br/>
     * input ([12, 3, 5, 18], [8, 1, 4, 9], 46) # 5
     *
     * <br/>
     * input ([12, 3, 2], [6,1, 1], 20) # -1
     *
     *
     *
     * Explanation: capacity = [2, 5, 6] // Battery backup recharge = [12, 1, 4] // Charging time x = 16 // Need phone
     * for 16 minutes
     *
     *
     *
     * 1. remaining: 16
     *
     * ctime=2 AT:[14,0,0] count=1 i=1 use: min(2,16)=2 remaining=16-2=14
     *
     * 2. remaining: 14 ctime=2+5=7 count=2 used=min(5,14)=5 remaining=9 idx=2
     *
     *
     * 3. remainging=9 ctime=13 used=min(6,9)=6 remaining=9-6=3 count=3 idx=3%3=0 availableTime[14,8,17] 4: remainging=3
     * ctime=13+2=15
     *
     * cTime<14=-1
     *
     */

    public static int batteryAndRecharge(int[] capacity, int[] recharge, int T) {

        int n = capacity.length;

        long[] availableTime = new long[n];

        long currentTime = 0;
        int count = 0;
        int idx = 0;
        int remaining = T;

        while (remaining > 0) {

            System.out.println("currentTime=" + currentTime + " count=" + count + " idx=" + idx + " remaining="
                    + remaining + " AvailableTime=" + Arrays.toString(availableTime));
            if (currentTime < availableTime[idx]) {
                return -1;
            }
            int use = Math.min(capacity[idx], remaining);
            currentTime += use;
            remaining -= use;
            count++;

            if (use == capacity[idx]) {
                availableTime[idx] = currentTime + recharge[idx];
            }
            System.out.println("currentTime=" + currentTime + " count=" + count + " idx=" + idx + " remaining="
                    + remaining + " AvailableTime=" + Arrays.toString(availableTime));

            idx = (idx + 1) % n;
        }

        return count;

    }

    /**
     * Determines whether a singer can perform a given song based on their vocal range.
     *
     * <p>
     * A note consists of:
     * <ul>
     * <li>A pitch letter: C, D, E, F, G, A, B (ordered from lowest to highest)</li>
     * <li>An octave number: 0 through 7 (ordered from lowest to highest)</li>
     * </ul>
     *
     * <p>
     * Note ordering rules:
     * <ul>
     * <li>A note with a higher octave number is always higher than a note with a lower octave number.</li>
     * <li>If two notes have the same octave, their order is determined by pitch: C < D < E < F < G < A < B.</li>
     * </ul>
     *
     * <p>
     * The lowest possible note is C0 and the highest possible note is B7.
     *
     * <p>
     * A song is singable if and only if every note in the song lies within the singer's vocal range (inclusive).
     *
     * @param notes
     *            an array of notes representing the song (e.g., {"F4", "B4", "C5"})
     * @param lowest
     *            the lowest note the singer can sing (inclusive)
     * @param highest
     *            the highest note the singer can sing (inclusive)
     * @return true if all notes in the song are within the specified range; false otherwise
     */
    public static boolean isSingable(String[] notes, String lowest, String highest) {

        int l = getValue(lowest);
        int r = getValue(highest);
        System.out.println("lowest " + lowest + " = " + l + " highest " + highest + " =" + r);
        // lowestNote ≤ songNote ≤ highestNote
        for (String note : notes) {
            int val = getValue(note);
            if (val < l || val > r) { // out of range
                return false;
            }
        }

        return true;
    }
    static int[] range = new int[]{5, 6, 0, 1, 2, 3, 4};
    private static int getValue(String note) {

        int pitch = range[note.charAt(0) - 'A'];
        int octave = note.charAt(1) - '0';

        return octave * 7 + pitch;

    }

    static void main() {
        /*
         * int[][] image = {{100, 200, 100}, {200, 50, 200}, {100, 200, 100}};
         *
         * int radius = 1;
         *
         * int[][] blurred = blurImage(image, radius);
         *
         * for (int[] ints : blurred) { for (int j = 0; j < blurred[0].length; j++) { System.out.print(ints[j] + " "); }
         * System.out.println(); }
         */

        int[] capacity = {2, 5, 6};
        int[] recharge = {12, 1, 4};
        int x = 16;
        System.out.println(batteryAndRecharge(capacity, recharge, x));
        /*
         * String[] notes = new String[]{"C3", "E3", "G3", "C4", "E4", "G4", "C5"}; System.out.println(isSingable(notes,
         * "B2", "C5"));
         */

    }

}
