package com.yangdi.leetcode.arraystring;

import java.util.*;

/**
 * 1348. Tweet Counts Per Frequency
 */
public class TweetCounts {

    int chunkMinute;
    int chunkHour;
    int chunkDay;
    Map<String, Integer> chunk;
    Map<String, List<Integer>> map;

    public TweetCounts() {
        this.chunkMinute = 60;
        this.chunkHour = 3600;
        this.chunkDay = 86400;

        this.chunk = new HashMap<>();
        this.map = new HashMap<>();

        chunk.put("minute", chunkMinute);
        chunk.put("hour", chunkHour);
        chunk.put("day", chunkDay);
    }

    public void recordTweet(String tweetName, int time) {
        List<Integer> list;

        if (map.containsKey(tweetName)) {
            list = map.get(tweetName);
            list.add(time);
        } else {
            list = new ArrayList<>();
            list.add(time);
        }

        map.put(tweetName, list);
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        List<Integer> result = new ArrayList<>();
        if (!map.containsKey(tweetName)) {
            return result;
        }

        List<Integer> times = map.get(tweetName);
        int freqChunk = chunk.get(freq);

        Map<Integer, Integer> freqMap = new HashMap<>();
        for (Integer time : times) {
            if (time >= startTime && time <= endTime) {
                int x = (time - startTime) / freqChunk;
                freqMap.put(x, freqMap.getOrDefault(x,0) + 1);
            }
        }

        int chunkNum = (endTime-startTime)/freqChunk+1;
        for (int i = 0; i < chunkNum; i++) {
            if (freqMap.containsKey(i)) {
                result.add(freqMap.get(i));
            } else {
                result.add(0);
            }
        }

        return result;
    }
}

