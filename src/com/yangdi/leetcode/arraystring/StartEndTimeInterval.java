package com.yangdi.leetcode.arraystring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DoorDash面试题
 * Input - ("mon 10:00 am", mon 11:00 am)
 * Output - [11005, 11010, 11015...11100]
 * Output starts with 1 if the day is monday, 2 if tuesday and so on till 7 for sunday
 * Append 5 min interval times to that till the end time
 * So here it is 10:05 as first case, so its written as 11005
 * 2nd is 10:10 so its written as 11010
 * ...
 * ...
 * Stop at 11100
 */
public class StartEndTimeInterval {
    class Time {
        int day;
        int hour;
        int min;
        boolean am;

        public Time(int day, int hour, int min, boolean am) {
            this.day = day;
            this.hour = hour;
            this.min = min;
            this.am = am;
        }

        void add(int mins) {
            hour += (min + mins) / 60;
            min = (min + mins) % 60;
            if (hour >= 13) { // afternoon or midnight
                am = !am;
                hour %= 12;
                if (am) { // pm->am, it's the other day
                    day = (day + 1) % 7;
                }
            }
        }

        int getNumeric() {
            return day * 10000 + hour * 100 + min;
        }

        boolean equals(Time time2) {
            return (day == time2.day) && (hour == time2.hour) && (min == time2.min) && (am == time2.am);
        }
    }

    int interval = 5;
    Map<String, Integer> mapDays = new HashMap<>();
    void mapDays() {
        mapDays.put("mon", 1);
        mapDays.put("tue", 2);
        mapDays.put("wed", 3);
        mapDays.put("thu", 4);
        mapDays.put("fri", 5);
        mapDays.put("sat", 6);
        mapDays.put("sun", 7);
    }

    Time parseTime(String str) {
        String[] strs = str.split(" ");
        String[] hourMin = strs[1].split(":");

        return new Time(mapDays.get(strs[0]),
                Integer.parseInt(hourMin[0]),
                Integer.parseInt(hourMin[1]),
                ("am".equals(strs[2])? true : false));
    }

    public List<Integer> createIntervals(String start, String end) {
        mapDays();
        List<Integer> results = new ArrayList<>();

        Time startTime = parseTime(start);
        Time endTime = parseTime(end);

        while (!startTime.equals(endTime)) {
            startTime.add(interval);
            results.add(startTime.getNumeric());
        }

        return results;
    }

    public static void main(String[] args) {
        StartEndTimeInterval startEndTimeInterval = new StartEndTimeInterval();
        for(Integer inter : startEndTimeInterval.createIntervals("mon 10:00 pm", "tue 11:00 pm")) {
            System.out.println("->" + inter);
        }
    }
}
