package com.yangdi.algorithm;

import java.util.*;

public class Testing {

    public static void main(String[] args) {
        /*int[] result = {0, 1, 3, 0, 0, 0};
        int key = 2;

        int index = Arrays.binarySearch(result, 0, 2, key);
        index = index >= 0 ? index : Math.abs(index + 1);
        System.out.println(index);*/

        /*List<String> list = new ArrayList<>();
        list.add("zijzllb");
        list.add("r");
        list.add("adfkd");
        Collections.sort(list);
        System.out.println(list.toString());*/

        /*int[] nums = {1,4,3,5,8,9};
        for (int num : nums) {
            if (num > 8) {
                num = 10;
            }
        }

        for (int num : nums) {
            System.out.println(num);
        }*/

        /*Set<List<Integer>> set = new HashSet<>();
        List<Integer> list1 = Arrays.asList(-1,0,1);
        if (set.add(list1)) {
            System.out.println("add list1 successfully");
        } else {
            System.out.println("add list1 unsuccessfully");
        }

        List<Integer> list2 = Arrays.asList(-1,0,1);
        if (set.add(list2)) {
            System.out.println("add list2 successfully");
        } else {
            System.out.println("add list2 unsuccessfully");
        }

        System.out.println(set.toString());*/

        /*char ch1 = '2';
        int x = ch1 - '0';
        int y = Character.getNumericValue(ch1);

        if (x == y) {
            System.out.println("x=y= " + x);
        } else {
            System.out.println("x= " + x);
            System.out.println("y= " + y);
        }

        LinkedList<Integer> list = new LinkedList<>();
        list.add(3);
        list.addFirst(5);
        System.out.println(list.getFirst());
        int m = (int)Math.pow(2,4);
        sqrt((double)2);*/

        /*List<int[]> list = createPossibleArray("123");
        System.out.println(list.toString());*/

        /*String s2 = "qcpr", s1 = "eqdf";
        System.out.println(match(s1, s2) + "");*/

        /*int[] nums = new int[]{3,5,6,2,5,4,19};
        System.out.println(lengthOfLIS(nums));*/

        /** string to int*/
        /*String val = "0012";
        System.out.println(Integer.valueOf(val) + "");*/

        /**string and split*/

        //String queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334:";
        String queryIP = "/home//foo/";
        String[] v6s = queryIP.split("/");
        for (int i = 0; i < v6s.length; i++) {
            System.out.println("i = " + i + ": " + v6s[i]);
        }
        // String.split takes a regex, and '.' has a special meaning for regexes.
        /*String version = "1.001.034";
        String[] versions = version.split("\\.");
        for (String ver : versions) {
            System.out.println(ver);
        }*/

        /** string and substring */
        /*String s = "abc";
        String str = s.substring(1,1);
        System.out.println(str.length()+"?");*/

        /** string and indexOf */
        /*String prefix = "leet";
        String str = "leetcode";
        System.out.println("" + str.indexOf(prefix));*/
    }

    static List<int[]> createPossibleArray(String s) {
        int index = 0;
        int number = 0;

        while (index < s.length() && s.charAt(index) >= '1' && s.charAt(index) <= '9') {
            number = 10*number + (s.charAt(index)-'0');
            index++;
        }

        List<int[]> list = new ArrayList<>();
        if (number > 0) {
            list.add(new int[]{number});
        }
        if (number >= 10 && number < 100) {
            list.add(new int[]{number/10, number%10});
        }
        if (number >= 100 && number < 1000) {
            list.add(new int[]{number/10, number%10});
            list.add(new int[]{number/100, number%100});
            int n1 = number/100;
            int n2 = (number-n1*100)/10;
            int n3 = number - n1*100 - n2*10;
            list.add(new int[]{n1, n2, n3});
        }

        return list;
    }

    static boolean match(String s1, String s2) {
        int len = s1.length();
        if (len != s2.length()) {
            return false;
        }

        int interval = s1.charAt(0) - s2.charAt(0);
        interval = (interval + 26) % 26;
        for (int i = 1; i < len; i++) {
            int n = s1.charAt(i) - s2.charAt(i);
            n = (n + 26) % 26;
            /*if (interval != n && interval != n + 26 && interval + 26 != n) {
                return false;
            }*/
            if (n != interval) {
                return false;
            }
        }

        return true;
    }

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown;
            }
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) {
            ret.append(row);
        }

        return ret.toString();
    }

    public static int lengthOfLIS(int[] nums) {
        ArrayList<Integer> sub = new ArrayList<>(); // always increasing
        sub.add(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num > sub.get(sub.size()-1)) { // legal -> add num to array
                sub.add(num);
            } else { // illegal -> set num to a legal place
                int j = binarySearch(sub, num);
                sub.set(j, num);
            }
        }
        System.out.println(sub.toString());
        return sub.size();
    }

    // left bound
    private static int binarySearch(ArrayList<Integer> sub, int num) {
        int left = 0, right = sub.size()-1;

        while (left < right) {
            int mid = (left + right) / 2;
            if (sub.get(mid) == num) {
                return mid;
            }

            if (sub.get(mid) < num) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
