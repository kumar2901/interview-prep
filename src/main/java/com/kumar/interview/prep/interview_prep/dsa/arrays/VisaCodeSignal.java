package com.kumar.interview.prep.interview_prep.dsa.arrays;

import java.util.Arrays;

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

    public void main(String[] args) {
        System.out.println(scooterDistance(20, new int[]{7, 4, 14}));

    }
}
