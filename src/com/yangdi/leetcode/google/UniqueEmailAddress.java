package com.yangdi.leetcode.google;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UniqueEmailAddress {
    public int numUniqueEmails(String[] emails) {
        if (emails == null || emails.length == 0) {
            return 0;
        }

        Set<String> uniqueEmails = new HashSet<>();

        for (String email : emails) {
            String[] emailName = email.split("@");
            String localName = emailName[0];
            String domainName = emailName[1];

            localName = removeSign(localName);

            email = localName + domainName;
            if (!uniqueEmails.contains(email)) {
                uniqueEmails.add(email);
            }
        }

        return uniqueEmails.size();
    }

    String removeSign(String name) {
        StringBuffer strBuffer = new StringBuffer();
        char[] items = name.toCharArray();

        for (char item : items) {
            if ('+' == item) {
                break;
            }

            if ('.' != item) {
                strBuffer.append(item);
            }
        }

        return strBuffer.toString();
    }

    /**
     * from LeetCode
     */
    public int numUniqueEmails2(String[] emails) {
        Set<String> seen = new HashSet();

        for (String email : emails) {
            int i = email.indexOf("@");
            String local = email.substring(0, i);
            String rest = email.substring(i);

            if (local.contains("+")) {
                local = local.substring(0, local.indexOf("+"));
            }
            // Note: one should escape the specific character '.',
            // since it is treated as a regex expression.
            local = local.replaceAll("\\.", "");
            seen.add(local + rest);
        }

        return seen.size();
    }

    public static void main(String[] args) {
        String[] emails = {"test.email+alex@leetcode.com", "test.email@leetcode.com"};
        UniqueEmailAddress unique = new UniqueEmailAddress();

        System.out.println(unique.numUniqueEmails(emails));
    }
}
