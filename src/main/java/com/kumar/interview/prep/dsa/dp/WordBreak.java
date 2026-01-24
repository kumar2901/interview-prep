package com.kumar.interview.prep.dsa.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {

    /**
     * Determines whether the input string {@code s} can be segmented into a sequence of one or more valid words
     * contained in the dictionary {@code wordDict}.
     *
     * <p>
     * The dictionary is represented as a list of non-empty strings. A valid segmentation means the entire string can be
     * broken into contiguous substrings, each of which must exist in the dictionary. The order and frequency of
     * dictionary words can vary, and dictionary words may be reused multiple times.
     * </p>
     *
     * <p>
     * Examples:
     *
     * <pre>
     * s = "leetcode", wordDict = ["leet", "code"]
     * → true  (because "leet code")
     *
     * s = "applepenapple", wordDict = ["apple", "pen"]
     * → true  (because "apple pen apple")
     *
     * s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
     * → false  (no valid segmentation)
     * </pre>
     * </p>
     *
     * @param s
     *            the input string to be segmented
     * @param wordDict
     *            the list of valid words available for segmentation
     * @return {@code true} if the entire string can be segmented using words from the dictionary, otherwise
     *         {@code false}
     */
    public boolean wordBreak(String s, List<String> wordDict) {

        Set<String> set = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[n] = true;
        int l = 0;
        for (String word : set) {
            l = Math.max(l, word.length());
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= l && (i + j) <= n; j++) {
                String substr = s.substring(i, j + i);
                if (set.contains(substr) && dp[i + j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        // System.out.println(Arrays.toString(dp));
        return dp[0];
    }

    void main() {
        System.out.println("Can wordBreak= " + wordBreak("leetcode", Arrays.asList("leet", "code")));
        System.out.println(
                "Can wordBreak= " + wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
        System.out.println("Can wordBreak= " + wordBreak("applepenapple", Arrays.asList("apple", "pen")));
    }

}
