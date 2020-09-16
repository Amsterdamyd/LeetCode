package com.yangdi.leetcode.array;

public class IntegerPlusOne {
    public int[] plusOne (int[] digits) {
        int length = digits.length;
        int[] nums = new int[length];
        int moveNum = 0;

        int value = digits[length - 1] + 1;
        if (value >= 10) {
            nums[length - 1] = value%10;
            moveNum = value/10;
        } else {
            nums[length - 1] = value;
        }

        for(int i = length - 2; i >= 0; i--) {
            if (moveNum != 0) {
                int newValue = digits[i] + moveNum;

                if (newValue >= 10) {
                    nums[i] = newValue%10;
                    moveNum = newValue/10;
                } else {
                    nums[i] = newValue;
                    moveNum = 0;
                }
            } else {
                nums[i] = digits[i];
            }
        }

        if (moveNum != 0) {
            int[] newNums = new int[length + 1];
            newNums[0] = moveNum;

            for(int i=1, j=0; i < newNums.length && j < nums.length; i++,j++) {
                newNums[i] = nums[j];
            }

            return newNums;
        }

        return nums;
    }

    public static void main(String[] args) {
        int[] number = {9,9,9,9};
        //int[] number = {1,2,9,9};

        IntegerPlusOne integerArray = new IntegerPlusOne();
        int[] nums = integerArray.plusOne(number);
        for(int value : nums) {
            System.out.print(value+" ");
        }

        int[] num1 = {1,2,3};
        int[] num2 = new int[3];
        int[] num3 = new int[]{};

        System.out.println();

        System.out.println(num1);
        System.out.println(num2);
        System.out.println(num3);

        for(int x : num2) {
            System.out.println(x);
        }
    }
}
