package com.yangdi.leetcode.arraystring;

import java.util.ArrayList;
import java.util.List;

public class FindPivotIndex {

    public static void main(String[] args) {
        int nums[] = {1, 7, 3, 6, 5, 6};
        //int nums[] = {1, 2, 3};
        //int nums[] = {-1,-1,-1,-1,-1,0};
        //int nums[] = {-1,-1,0,0,-1,0};

        FindPivotIndex pivIndex = new FindPivotIndex();
        System.out.println(pivIndex.pivotIndex(nums));
    }

    /**
     * It can not handle the negative arrays
     *
     * @param nums
     * @return
     */
    public int pivotIndex1(int[] nums) {
        int leftIndex = 0;
        int rightIndex = nums.length - 1;

        int leftSum = 0;
        int rightSum = 0;

        while (leftIndex <= rightIndex) {
            if (leftSum == rightSum) {
                if (leftIndex == rightIndex) {
                    return leftIndex;
                } else {
                    if (nums[leftIndex] <= nums[rightIndex]) {
                        leftSum += nums[leftIndex];
                        leftIndex++;
                    } else {
                        rightSum += nums[rightIndex];
                        rightIndex--;
                    }
                }
            } else if (leftSum < rightSum) {
                leftSum += nums[leftIndex];
                leftIndex++;
            } else {
                rightSum += nums[rightIndex];
                rightIndex--;
            }
        }

        return -1;
    }

    /**
     * It can not handle the negative arrays
     *
     * @param nums
     * @return
     */
    public int pivotIndex2(int[] nums) {
        if (null == nums || nums.length <= 2) {
            return -1;
        }

        //int index = 0;
        int index = (nums.length - 1) / 2;
        List<Integer> indexList = new ArrayList<Integer>();

        while (index >= 0 && index < nums.length) {
            if (indexList.contains(index)) {
                return -1;
            }

            int leftSum = 0;
            int rightSum = 0;

            if (index != 0) {
                for (int i = 0; i < index; i++) {
                    leftSum += nums[i];
                }
            }

            if (index != nums.length - 1) {
                for (int i = index + 1; i < nums.length; i++) {
                    rightSum += nums[i];
                }
            }

            indexList.add(index);

            if (leftSum < rightSum) {
                if (nums[index] > 0) {
                    index++;
                } else if (nums[index] < 0) {
                    index--;
                } else {
                    if (nums[index + 1] > 0 && nums[index - 1] > 0) {
                        index++;
                    } else if (nums[index + 1] < 0 && nums[index - 1] < 0) {
                        index--;
                    } else if (nums[index + 1] > 0 && nums[index - 1] < 0) {
                        index--;//both can
                    } else {
                        return -1;
                    }
                }
            } else if (leftSum > rightSum) {
                if (nums[index] > 0) {
                    index--;
                } else if (nums[index] < 0) {
                    index++;
                } else {
                    if (nums[index + 1] > 0 && nums[index - 1] > 0) {
                        index--;
                    } else if (nums[index + 1] < 0 && nums[index - 1] < 0) {
                        index++;
                    } else if (nums[index + 1] < 0 && nums[index - 1] > 0) {
                        index--;//both can
                    } else {
                        return -1;
                    }
                }
            } else {
                return index;
            }
        }

        return -1;
    }

    public int pivotIndex(int[] nums) {
        int sum = 0;
        for (int value : nums) {
            sum += value;
        }

        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum == leftSum + nums[i] + leftSum) {
                return i;
            } else {
                leftSum += nums[i];
            }
        }

        return -1;
    }
}
