package com.yangdi.leetcode.arraystring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubdomainVisitCount {

    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();

        for (String cpdomain : cpdomains) {
            int count = Integer.valueOf(cpdomain.substring(0, cpdomain.indexOf(" ")));
            String domain = cpdomain.substring(cpdomain.indexOf(" ") + 1);

            String[] subDomains = domain.split("\\.");
            String key = "";

            for (int i = subDomains.length - 1; i >= 0; i--) {
                if (i == subDomains.length - 1) {
                    key = subDomains[i];
                } else {
                    key = subDomains[i] + "." + key;
                }

                if (map.containsKey(key)) {
                    map.put(key, map.get(key) + count);
                } else {
                    map.put(key, count);
                }
            }
        }

        List<String> list = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            list.add(entry.getValue() + " " + entry.getKey());
        }

        return list;
    }

    public static void main(String[] args) {
        SubdomainVisitCount count = new SubdomainVisitCount();
        String[] cpdomains = new String[]{"9001 discuss.leetcode.com"};
        System.out.println(count.subdomainVisits(cpdomains).toString());
    }
}
