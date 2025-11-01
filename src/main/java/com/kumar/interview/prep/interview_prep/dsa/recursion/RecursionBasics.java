package com.kumar.interview.prep.interview_prep.dsa.recursion;


import java.util.ArrayList;
import java.util.List;

public class RecursionBasics {

        public int factorial(int n) {

            if (n == 1 || n == 2) {
                return n;
            }

            return n * factorial(n - 1);
        }

        public int sumOfDigits(int n) {
            if (n == 0 || n % 10 == n) {
                return n;
            }
            return n % 10 + sumOfDigits(n / 10);
        }

        public int reverseNumber(int num, int sum) {
            if (num < 1) {
                return sum;
            }
            int r = num % 10;
            return reverseNumber(num / 10, sum * 10 + r);

        }

        public boolean isPalindrome(String str, int l, int r) {
            if (l >= r) {
                return true;
            }
            if (str.charAt(l) != str.charAt(r)) {
                return false;
            }
            return isPalindrome(str, l + 1, r - 1);

        }

        private int countZero(int n, int count) {
            if (n == 0) {
                return count;
            }
            int r = n % 10;
            if (r == 0) {
                count++;
            }
            return countZero(n / 10, count);
        }

        private boolean isSorted(int[] arr, int index) {
            if (index == arr.length - 1) {
                return true;
            }
            if (arr[index] > arr[index + 1]) {
                return false;
            }
            return isSorted(arr, index + 1);
        }

        /**
         * 23
         * {a,b,c}x{d,e,f}
         *
         * @param digits
         * @return
         */

        public List<String> letterCombinations(String digits) {
            List<String> result = new ArrayList<>();
            if (digits == null || digits.isEmpty()) {
                return result;
            }
            String[] mapping = getDigitMapping();
            backtrack(result, digits, new StringBuilder(), 0, mapping);
            return result;
        }

        private void backtrack(List<String> result, String digits, StringBuilder sb, int i, String[] mapping) {
            if (i == digits.length()) {
                result.add(sb.toString());
                return;
            }

            int d = digits.charAt(i) - '0';
            if (d < 2 || d >= mapping.length) { //skip non required characters
                backtrack(result, digits, sb, i + 1, mapping);
                return;
            }

            String letters = mapping[d];


            for (int k = 0; k < letters.length(); k++) {
                sb.append(letters.charAt(k));
                backtrack(result, digits, sb, i + 1, mapping);
                sb.deleteCharAt(sb.length() - 1);
            }
        }

        private static String[] getDigitMapping() {
            return new String[]{
                    "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
            };
        }

        public static int numRollsToTarget(int n, int k, int target) {
            if (target < 1 || n * k < target) {
                return 0;
            }
            return countWays(n, k, target);
        }

        private static int countWays(int n, int k, int target) {

            if (target == 0) {
                return 1;
            }

            if (n == 0 || target < 0) {
                return 0;
            }
            int ways = 0;
            for (int i = 1; i <= k && i <= target; i++) {
                ways += countWays(n - 1, k, target - i);
                if (ways >= 1000000007) {
                    ways = ways % 1000000007;
                }
            }

            return ways;
        }


        void main(String[] args) {
            System.out.println(letterCombinations("234"));
            System.out.println(letterCombinations("23014"));
            System.out.println(numRollsToTarget(1, 6, 4));
            System.out.println(numRollsToTarget(2, 6, 10));
            System.out.println(numRollsToTarget(3, 6, 14));
            System.out.println(numRollsToTarget(6, 6, 20));
            System.out.println(numRollsToTarget(30, 6, 14972));
        }
    }

