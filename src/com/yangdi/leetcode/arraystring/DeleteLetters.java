package com.yangdi.leetcode.arraystring;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Microsoft online test
 * complete 3 tasks in 90 minutes
 */
public class DeleteLetters {

    /**
     * Question #1
     */
    public int solutions1(String S) {
        if (S.length() == 0 || S.length() == 1) {
            return 0;
        }

        Map<Character, Integer> occurrence = new HashMap<>();
        for (char ch : S.toCharArray()) {
            occurrence.put(ch, occurrence.getOrDefault(ch, 0) + 1);
        }

        List<Integer> occurrences = new ArrayList<>(occurrence.values());
        Collections.sort(occurrences, (a,b) -> Integer.compare(b,a));
        int operations = 0;

        for (int i = 1; i < occurrences.size(); i++) {
            int number = occurrences.get(i);
            if (number == occurrences.get(i-1) && number > 1) {
                operations++;
                occurrences.set(i, number-1);
            } else if (occurrences.get(i-1) == 1 || occurrences.get(i-1) == 0) {
                operations += number;
                occurrences.set(i, 0);
            }
        }

        return operations;
    }

    public static void main(String[] args) {
        DeleteLetters deleteLetters = new DeleteLetters();
        String s1 = "aaaaaaabbbb";
        String s2 = "ccaaffddecee";
        String s3 = "eeee";
        String s4 = "example";
        System.out.println(deleteLetters.solutions1(s1));
    }

    /**
     * Question #3
     */
    public int solution3(int[] A) {
        Map<Integer, Integer> occurTimes = new HashMap<>();

        for (int a : A) {
            int times = occurTimes.getOrDefault(a, 0);
            occurTimes.put(a, times + 1);
        }

        final List<Integer> eligibleIntegers = occurTimes.entrySet().stream()
                .filter(entry -> Objects.equals(entry.getKey(), entry.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        if (eligibleIntegers.isEmpty()) {
            return 0;
        }

        return Collections.max(eligibleIntegers);
    }

    /**
     * Question #2
     */
    private static final String EXECUTABLE_SIGN = "x";
    private static long MAX_FILE_SIZE = 14 * Double.valueOf(Math.pow(2, 20)).longValue();
    private static final String OWNER = "admin";
    private static final List<String> MONTHS = Arrays.asList("Jan", "Fen", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
    private static final int OWNER_END_INDEX = 6;
    private static final int PERM_END_INDEX = 10;
    private static final int DATE_END_INDEX = 22;
    private static final int SIZE_END_INDEX = 33;

    private static final int DAY_END_INDEX = 2;
    private static final int MONTH_END_INDEX = 6;

    private static final String NO_FILES = "NO_FILES";

    static class File implements Comparable {
        public String owner;
        public String permission;
        public String date;
        public int day;
        public String month;
        public int year;
        public long size;
        public String name;

        public File(String owner, String permission, String date, long size, String name) {
            this.owner = owner;
            this.permission = permission;
            this.date = date;
            this.size = size;
            this.name = name;

            parseDate(date);
        }

        private void parseDate(String date) {
            String day = date.substring(0, DAY_END_INDEX).trim();
            String month = date.substring(DAY_END_INDEX, MONTH_END_INDEX).trim();
            String year = date.substring(MONTH_END_INDEX).trim();

            this.day = Integer.parseInt(day);
            this.month = month;
            this.year = Integer.parseInt(year);
        }

        @Override
        public int compareTo(Object o) {
            File file2 = (File) o;

            if (this.year > file2.year) {
                return 1;
            } else if (this.year < file2.year) {
                return 0;
            } else {
                if (MONTHS.indexOf(this.month) > MONTHS.indexOf(file2.month)) {
                    return 1;
                } else if (MONTHS.indexOf(this.month) < MONTHS.indexOf(file2.month)) {
                    return 0;
                } else {
                    if (this.day > file2.day) {
                        return 1;
                    }

                    return 0;
                }
            }
        }
    }

    public String solution2(String s) {
        String[] fileContent = s.split("\n");

        List<File> files = new ArrayList<>();

        for (String file: fileContent) {
            String owner = file.substring(0, OWNER_END_INDEX).trim();
            String permission = file.substring(OWNER_END_INDEX, PERM_END_INDEX).trim();
            String date = file.substring(PERM_END_INDEX, DATE_END_INDEX).trim();
            String size = file.substring(DATE_END_INDEX, SIZE_END_INDEX).trim();
            String name = file.substring(SIZE_END_INDEX).trim();

            final long fileSize = Long.parseLong(size);

            if (isValidFile(owner, permission, fileSize)) {
                files.add(new File(owner, permission, date, fileSize, name));
            }
        }

        if (files.isEmpty()) {
            return NO_FILES;
        }

        File file = Collections.min(files);

        return file.date;
    }

    private boolean isValidFile(String owner, String permission, long size) {
        return EXECUTABLE_SIGN.equals(permission.substring(permission.length() - 1))
                && size < MAX_FILE_SIZE
                && OWNER.equals(owner);
    }
}
