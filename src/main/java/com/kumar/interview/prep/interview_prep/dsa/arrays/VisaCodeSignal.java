package com.kumar.interview.prep.interview_prep.dsa.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VisaCodeSignal {

    /**
     * Q3. E-Scooter Distance on a Line[Easy] You start at position 0 and are given an endpoint and positions of
     * e-scooters along the line. Each e-scooter can travel exactly 10 units. A person walks until they find an
     * available scooter. If they take one, they must travel exactly 10 points with it. You need to calculate how much
     * total distance the person traveled using scooters. <br/>
     * Input: endpoint = 20 scooters = [7, 4, 14]
     *
     * <p/>
     * output: 16
     *
     * @param endPoint
     * @param scooters
     * @return
     */
    private int scooterDistance(int endPoint, int[] scooters) {
        Arrays.sort(scooters);

        int position = 0;
        int totalRide = 0;

        while (position < endPoint) {

            int scooter = -1;
            for (int s : scooters) {
                if (s >= position) {
                    scooter = s;
                    break;

                }
            }
            System.out.println("position=" + position + " scooter=" + scooter);
            if (scooter == -1 || scooter >= endPoint) {
                break;
            }

            int rideEnd = scooter + 10;
            if (rideEnd >= endPoint) {
                totalRide = totalRide + (endPoint - scooter);
                break;
            } else {
                totalRide += 10;
                position = rideEnd;
            }

        }

        return totalRide;
    }

    /**
     * <p>
     * Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth
     * characters and is fully (left and right) justified. You should pack your words in a greedy approach; that is,
     * pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly
     * maxWidth characters. Extra spaces between words should be distributed as evenly as possible. If the number of
     * spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces
     * than the slots on the right. For the last line of text, it should be left-justified, and no extra space is
     * inserted between words
     * </p>
     * <p>
     * Example 1:
     *
     * Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16 Output: [ "This is
     * an", "example of text", "justification. " ]
     * </p>
     * <p>
     *
     * Example 2: Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16 Output: [ "What must
     * be", "acknowledgment ", "shall be " ]
     * </p>
     * </p>
     *
     * @param words
     *            words
     * @param maxWidth
     *            maxWidth
     * @return justified texts
     */
    public List<String> textJustification(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0, n = words.length;

        while (i < n) {

            int j = i;
            int l = 0;
            while (j < n && l < maxWidth) {
                if ((l + words[j].length() + j - i) > maxWidth) {
                    break;
                }

                l = l + words[j].length();
                j = j + 1;
            }

            int numWords = j - i;
            int spaces = maxWidth - l;
            // System.out.println("l="+l+" spaces="+spaces);
            StringBuilder sb = new StringBuilder();
            if (j == n || numWords == 1) {

                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) {
                        sb.append(" ");
                    }

                }

                while (sb.length() < maxWidth) {
                    sb.append(" ");
                }

            } else {

                int evenSpace = spaces / (numWords - 1);
                int extra = spaces % (numWords - 1);
                // System.out.println("i="+i+" j="+j+" evenSpace="+evenSpace+" extraSpace="+extra+"
                // totalSpace="+spaces);
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) {
                        int spacesToAdd = evenSpace + (extra > 0 ? 1 : 0);
                        while (spacesToAdd-- > 0) {
                            sb.append(" ");
                        }
                        if (extra > 0) {
                            extra--;
                        }

                    }

                }

            }
            i = j;
            result.add(sb.toString());
        }

        return result;

    }

    public void main(String[] args) {
        System.out.println(scooterDistance(20, new int[]{7, 4, 14}));

        int num = 1 << 2;
        int num1 = 2 << 2;
        System.out.println(num + " " + num1);

    }
}
