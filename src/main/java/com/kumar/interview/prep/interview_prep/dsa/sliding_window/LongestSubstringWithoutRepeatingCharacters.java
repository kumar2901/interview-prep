package com.kumar.interview.prep.interview_prep.dsa.sliding_window;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {

    public int findLongestSubstringWithoutRepeatingCharacters(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() < 2) {
            return s.length();
        }
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        int l = 0;
        int r = 0;
        int maxL = Integer.MIN_VALUE;
        while (r < s.length()) {
            char ch = chars[r];
            if (map.containsKey(ch)) {
                l = Math.max(l, map.get(ch) + 1);
            }
            maxL = Math.max(maxL, r - l + 1);
            map.put(ch, r);
            r = r + 1;

            // System.out.println("l=" + l + " r=" + r + " maxL=" + maxL + " ch=" + ch);

        }
        // System.out.println("l=" + l + " r=" + r + " maxL=" + maxL);
        return Math.max(maxL, r - l);
    }

    /**
     * Returns the length of the longest substring of the given string that contains at most {@code k} distinct
     * characters.
     *
     * <p>
     * A substring is a contiguous block of characters within the string. This method determines the maximum window size
     * such that the total number of unique characters in that window does not exceed {@code k}.
     * </p>
     *
     * <p>
     * For example:
     *
     * <pre>
     * s = "eceba", k = 2 → longest substring is "ece" with length 3
     * s = "aa",   k = 1 → longest substring is "aa"  with length 2
     * </pre>
     * </p>
     *
     * @param s
     *            the input string
     * @param k
     *            the maximum number of distinct characters allowed in the substring
     * @return the length of the longest substring containing at most {@code k} distinct characters
     * @throws IllegalArgumentException
     *             if {@code k} is less than 1
     */

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s.length() <= k) {
            return s.length();
        }

        int maxL = Integer.MIN_VALUE;
        Map<Character, Integer> map = new HashMap<>();
        int l = 0;
        int r = 0;
        while (r < s.length()) {
            char ch = s.charAt(r);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            while (map.size() > k) {
                ch = s.charAt(l);
                int count = map.get(ch);
                if (count > 1) {
                    map.put(ch, count - 1);
                } else {
                    map.remove(ch);
                }
                l++;

            }

            maxL = Math.max(maxL, r - l + 1);

            r++;
        }

        return maxL;

    }

    /**
     * Given a string {@code s} and an integer {@code k}, this problem asks for the length of the longest substring in
     * which every character appears at least {@code k} times.
     *
     * <p>
     * A substring is a contiguous sequence of characters within the original string. The goal is to determine the
     * maximum possible length of such a substring where each character's frequency in that substring is greater than or
     * equal to {@code k}.
     * </p>
     *
     * <p>
     * Examples:
     *
     * <pre>
     * s = "aaabb", k = 3  → longest valid substring is "aaa", length = 3
     * s = "ababbc", k = 2 → longest valid substring is "ababb", length = 5
     * </pre>
     * </p>
     *
     * @param s
     *            the input string
     * @param k
     *            the minimum number of repetitions required for each character in the substring
     * @return the length of the longest substring in which every character appears at least {@code k} times
     * @throws IllegalArgumentException
     *             if {@code k} is less than 1
     */
    private int lengthOfSubstringAtleastKRepeating(String s, int k) {
        if (s == null || s.length() < k) {
            return 0;
        }
        int maxL = 0;

        int[] freq = new int[26];

        int n = s.length();
        for (int i = 0; i < n; i++) {
            Arrays.fill(freq, 0);
            for (int j = i; j < n; j++) {
                char ch = s.charAt(j);
                freq[ch - 'a']++;
                if (isValid(i, j, k, freq)) {
                    maxL = Math.max(maxL, j - i + 1);
                }
            }
        }

        return maxL;
    }

    private boolean isValid(int l, int r, int k, int[] freq) {
        int countChars = 0;
        int countCharsAtleastK = 0;
        for (int f : freq) {
            if (f > 0) {
                countChars++;
            }
            if (f >= k) {
                countCharsAtleastK++;
            }
        }

        return countCharsAtleastK == countChars;// all character has atleast k freq
    }

    void main() {
        System.out.println(findLongestSubstringWithoutRepeatingCharacters("abcabcbb"));
        System.out.println(findLongestSubstringWithoutRepeatingCharacters("bbbbb"));
        System.out.println(findLongestSubstringWithoutRepeatingCharacters("pwwkew"));
        System.out.println("lengthOfLongestSubstringKDistinct= " + lengthOfLongestSubstringKDistinct("eceba", 2));
        System.out.println("lengthOfLongestSubstringKDistinct= " + lengthOfLongestSubstringKDistinct("aa", 1));
        System.out.println("lengthOfSubstringAtleastKRepeating= " + lengthOfSubstringAtleastKRepeating("aaabb", 3));
        System.out.println("lengthOfSubstringAtleastKRepeating= " + lengthOfSubstringAtleastKRepeating("ababbc", 2));
    }
}
