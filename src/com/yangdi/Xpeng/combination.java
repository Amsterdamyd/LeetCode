package com.yangdi.Xpeng;

import java.util.*;

/**
 * 23. Merge k Sorted Lists
 */
public class combination {

    public ListNode mergeKLists(ListNode[] nodes) {
        if (nodes.length == 0) {
            return null;
        }

        ListNode preHead = new ListNode(-1);
        ListNode pre = preHead;

        PriorityQueue<ListNode> queue = new PriorityQueue<>((a, b) -> a.val - b.val);

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                queue.add(nodes[i]);
            }
        }

        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            pre.next = node;
            pre = pre.next;

            node = node.next;
            if (node != null) {
                queue.add(node);
            }
        }

        return preHead.next;
    }

    public ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        ListNode preHead = new ListNode(-1);
        ListNode pre = preHead;

        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                pre.next = head1;
                pre = pre.next;
                head1 = head1.next;
            } else {
                pre.next = head2;
                pre = pre.next;
                head2 = head2.next;
            }
        }

        pre.next = (head1 == null) ? head2 : head1;

        return preHead.next;
    }

    public int compress(char[] chars) {
        int i = 0, j = 0;
        StringBuilder builder = new StringBuilder();

        while (i < chars.length) {
            builder.append(chars[i]);

            while (j < chars.length && chars[j] == chars[i]) {
                j++;
            }

            int dif = j - i;
            if (dif > 1) {
                builder.append(dif);
            }

            i = j;
        }

        for (int k = 0; k < builder.length(); k++) {
            chars[i] = builder.charAt(k);
        }

        return builder.length();
    }

    public List<List<Integer>> creatPermutations(int[] nums) {
        List<List<Integer>> total = new ArrayList<>();
        ArrayList<Integer> numbers = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (int num : nums) {
            numbers.add(num);
        }

        backtrack(total, numbers, list, nums.length);

        return total;
    }

    void backtrack(List<List<Integer>> total, ArrayList<Integer> numbers, ArrayList<Integer> list, int len) {
        if (list.size() == len) {
            total.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < numbers.size(); i++) {
            int num = numbers.get(i);
            list.add(num);
            numbers.remove(i);

            backtrack(total, numbers, list, len);

            list.remove(list.size() - 1);
            numbers.add(i, num);
        }
    }

    public void nextPermutation(int[] nums) {
        if (nums.length < 2) {
            return;
        }

        int i = nums.length - 1;
        while (i > 0 && nums[i - 1] >= nums[i]) {
            i--;
        }

        if (i > 0) {
            int j = i;
            for (; j < nums.length; j++) {
                if (nums[j] <= nums[i - 1]) {
                    break;
                }
            }
            swap(nums, i - 1, j - 1);
        }

        reverse(nums, i);
    }

    void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }

    void reverse(int[] nums, int start) {
        int end = nums.length - 1;

        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public List<List<Integer>> compute(List<Integer> nums) {
        List<List<Integer>> total = new ArrayList<>();
        Map<Integer, int[]> map = new HashMap<>();

        for (int i = 0; i < nums.size() - 1; i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                int sum = nums.get(i) + nums.get(j);
                if (!map.containsKey(sum)) {
                    map.put(sum, new int[]{nums.get(i), nums.get(j)});
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(map.get(sum)[0]);
                    list.add(map.get(sum)[1]);
                    list.add(nums.get(i));
                    list.add(nums.get(j));
                    total.add(list);
                }
            }
        }

        return total;
    }

    public boolean isValid(String str) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else {
                if (stack.isEmpty() || stack.peek() != map.get(ch)) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }

        return stack.isEmpty();
    }

    public List<Integer> SpiralTraverse(int[][] nums) {
        List<Integer> list = new ArrayList<>();
        int top = 0, bottom = nums.length - 1;
        int left = 0, right = nums[0].length - 1;
        int direction = 0;

        while (top <= bottom && left <= right) {
            if (direction == 0) {
                for (int i = left; i <= right; i++) {
                    list.add(nums[top][i]);
                }
                top++;
            } else if (direction == 1) {
                for (int i = top; i <= bottom; i++) {
                    list.add(nums[i][right]);
                }
                right--;
            } else if (direction == 2) {
                for (int i = right; i >= left; i--) {
                    list.add(nums[bottom][i]);
                }
                bottom--;
            } else if (direction == 3) {
                for (int i = bottom; i >= top; i--) {
                    list.add(nums[i][left]);
                }
                left++;
            }

            direction = (direction + 1) % 4;
        }

        return list;
    }

    public int rand10() {
        int number = 0;

        do {
            int row = 5; // rand7();
            int col = 8; // rand7();

            number = (row - 1) * 7 + col;

        } while (number > 40);

        return 1 + (number - 1) % 10;
    }

    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }

        boolean[] isComposite = new boolean[n];
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (!isComposite[i]) {
                for (int j = i * i; j < n; j += i) {
                    isComposite[j] = true;
                }
            }
        }

        int result = 0;
        for (int i = 2; i < n; i++) {
            if (!isComposite[i]) {
                result++;
            }
        }

        return result;
    }
}
