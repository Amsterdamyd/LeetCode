package com.yangdi.leetcode.recursion;

public class Pow {

    public double myPow(double x, int n) {
        if (x == 0) {
            return Double.valueOf(0);
        }

        if (n >= 0) {
            return helper(x, n);
        } else {
            // When n is Integer.MIN_VALUE, (-n) is still negative, and equal to itself.
            return 1 / helper(x, -n);
        }
    }

    /**
     * Recursion solution (by myself)
     * Time Complexity: O(logN)
     * Space Complexity: O(logN)
     */
    double helper(double x, int n) {
        if (n == 0) {
            return Double.valueOf(1);
        }
        if (n == 1) {
            return x;
        }

        if (n % 2 == 0) {
            return helper(x * x, n / 2);
        } else {
            return x * helper(x * x, n / 2);
        }
    }

    /**
     * Iteration solution (by myself)
     * if n is Integer.MIN_VALUE,
     * Time Complexity: O(logN)
     * Space Complexity: O(1)
     */
    static double helper2(double x, int n) {
        double sum = 1;

        while (n >= 1 || n <= -1) {
            if (n % 2 == 0) {
                x = x * x;
                n = n / 2;
            } else {
                sum *= x;
                x = x * x;
                n = n / 2;
            }
        }

        return sum;
    }

    /**
     * from leetcode
     */
    public double myPow2(double x, int n) {
        long N = n; // transfer int into long to avoid -Integer.MIN_VALUE issue
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        return fastPow(x, N);
    }

    private double fastPow(double x, long n) {
        if (n == 0) {
            return 1.0;
        }

        double half = fastPow(x, n / 2);

        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    /**
     * from leetcode
     */
    public double myPow3(double x, int n) {
        long N = n; // transfer int into long to avoid -Integer.MIN_VALUE issue
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        double ans = 1;
        double current_product = x;

        for (long i = N; i > 0; i /= 2) {
            if ((i % 2) == 1) {
                ans = ans * current_product;
            }
            current_product = current_product * current_product;
        }

        return ans;
    }


    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE * -1);
        System.out.println(Long.MIN_VALUE * -1);
        System.out.println(Double.MIN_VALUE * -1);
        System.out.println(Short.MIN_VALUE * -1);
        System.out.println(Byte.MIN_VALUE * -1);
        System.out.println(1 / helper2(2, -2147483648));
    }
}
