package com.yangdi.leetcode.google;

import java.util.HashMap;
import java.util.Map;

public class OddEvenJump {

    public int oddEvenJumps(int[] A) {
        JumpNodeResult[] results= new JumpNodeResult[A.length];

        // dynamic programming (We get the result of i by the results of ones behind it)
        for (int i = A.length - 1; i > -1; i--) {
            JumpNodeResult ithResult = new JumpNodeResult(A[i]);

            if (i == A.length - 1) {
                ithResult.oddJumpResult = true;
                ithResult.evenJumpResult = true;
            } else {
                // Calculate i's next odd jump index and even jump index
                Map<String, Integer> map = findJumpIndex(i, A);

                if (map != null) {
                    if (map.containsKey("odd")) {
                        int nextIndex = map.get("odd");
                        JumpNodeResult nextResult = results[nextIndex];

                        if (nextResult.evenJumpResult != null && nextResult.evenJumpResult) {
                            ithResult.oddJumpResult = true;
                        } else {
                            ithResult.oddJumpResult = false;
                        }
                    }

                    if (map.containsKey("even")) {
                        int nextIndex = map.get("even");
                        JumpNodeResult nextResult = results[nextIndex];

                        if (nextResult.oddJumpResult != null && nextResult.oddJumpResult) {
                            ithResult.evenJumpResult = true;
                        } else {
                            ithResult.evenJumpResult = false;
                        }
                    }
                }
            }

            results[i] = ithResult;
        }

        int beginOddJumpNumber = 0;
        for (JumpNodeResult result : results) {
            if (result.oddJumpResult != null && result.oddJumpResult) {
                beginOddJumpNumber++;
            }
        }

        return beginOddJumpNumber;
    }

    Map<String, Integer> findJumpIndex(int currentIndex, int[] A) {
        int begin = currentIndex + 1;
        int end = A.length - 1;
        if (begin > end) {
            return null;
        }

        Map<String, Integer> map = new HashMap<>();

        int smallestValueIndex = -1;
        int smallestValue = -1;
        int largestValueIndex = -1;
        int largestValue = -1;

        for (int j = begin; j <= end; j++) {
            // odd jump
            if (A[currentIndex] <= A[j]) {
                if (smallestValue == -1) {
                    smallestValue = A[j];
                    smallestValueIndex = j;
                } else if (A[j] < smallestValue) {
                    smallestValue = A[j];
                    smallestValueIndex = j;
                }
            }
            // even jump
            if(A[currentIndex] >= A[j]) {
                if (largestValue == -1) {
                    largestValue = A[j];
                    largestValueIndex = j;
                } else if (A[j] > largestValue) {
                    largestValue = A[j];
                    largestValueIndex = j;
                }
            }
        }

        if (smallestValueIndex != -1) {
            map.put("odd", smallestValueIndex);
        }

        if (largestValueIndex != -1) {
            map.put("even", largestValueIndex);
        }

        return map;
    }

    public static void main(String[] args) {
        //int[] A = {10,13,12,14,15};  //2
        //int[] A = {2,3,1,1,4};  //3
        int[] A = {5,1,3,4,2};  //3
        OddEvenJump jump = new OddEvenJump();
        System.out.println(jump.oddEvenJumps(A) + "");
    }
}
