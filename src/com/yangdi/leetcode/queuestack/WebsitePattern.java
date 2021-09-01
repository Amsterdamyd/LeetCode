package com.yangdi.leetcode.queuestack;

import java.util.*;

/**
 * 1152. Analyze User Website Visit Pattern
 */
public class WebsitePattern {

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, List<Object[]>> map = new HashMap<>();

        for (int i = 0; i < username.length; i++) {
            List<Object[]> list;
            if (!map.containsKey(username[i])) {
                list = new ArrayList<>();
            } else {
                list = map.get(username[i]);
            }

            Object[] obs = new Object[2];
            obs[0] = timestamp[i];
            obs[1] = website[i];

            list.add(obs);
            map.put(username[i], list);
        }

        Map<String, Set<String>> count = new HashMap<>();

        for (String user : map.keySet()) {
            List<Object[]> web = map.get(user);
            if (web.size() < 3) {
                continue;
            }

            Collections.sort(web, (Object[] a, Object[] b) -> Integer.parseInt(a[0].toString()) - Integer.parseInt(b[0].toString()));

            for (int i = 0; i < web.size() - 2; i++) {
                for (int j = i + 1; j < web.size() - 1; j++) {
                    for (int k = j + 1; k < web.size(); k++) {
                        String item = web.get(i)[1] + "-" + web.get(j)[1] + "-" + web.get(k)[1];
                        Set<String> set = count.getOrDefault(item, new HashSet<>());
                        set.add(user);

                        count.put(item, set);
                    }
                }
            }
        }

        String result = count.keySet().toArray()[0].toString();
        for (String sequence : count.keySet()) {
            Set<String> userSet = count.get(sequence);
            int size = userSet.size();
            if (size > count.get(result).size() || (size == count.get(result).size() && result.compareTo(sequence) > 0)) {
                result = sequence;
            }
        }

        return Arrays.asList(result.split("-"));
    }

    public static void main(String[] args) {
        String[] username = {"h", "eiy", "cq", "h", "cq", "txldsscx", "cq", "txldsscx", "h", "cq", "cq"};
        int[] timestamp = {527896567, 334462937, 517687281, 134127993, 859112386, 159548699, 51100299, 444082139, 926837079, 317455832, 411747930};
        String[] website = {"hibympufi", "hibympufi", "hibympufi", "hibympufi", "hibympufi", "hibympufi", "hibympufi", "hibympufi", "yljmntrclw", "hibympufi", "yljmntrclw"};

        WebsitePattern pattern = new WebsitePattern();
        List<String> list = pattern.mostVisitedPattern(username, timestamp, website);
        System.out.println(list.toString());
    }
}
