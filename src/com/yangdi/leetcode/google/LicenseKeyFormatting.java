package com.yangdi.leetcode.google;

public class LicenseKeyFormatting {

    public String licenseKeyFormatting(String S, int K) {
        String noDashStr = S.replaceAll("-", "");
        noDashStr = noDashStr.toUpperCase();

        String[] groups = S.split("-");
        int alphanumericLength = 0;

        for (String group : groups) {
            alphanumericLength += group.length();
        }

        int firstGrouplength = alphanumericLength % K;
        int leftGroupNumber = alphanumericLength / K;
        int groupNumber = (firstGrouplength == 0) ? leftGroupNumber : leftGroupNumber + 1;

        String returnStr = "";
        int endIndex = 0;

        for (int i = 0 ; i < groupNumber; i++) {
            if (i == groupNumber - 1) {
                returnStr += noDashStr.substring(endIndex);
            } else if (i == 0) {
                endIndex = (firstGrouplength == 0) ? K : firstGrouplength;
                returnStr += noDashStr.substring(0, endIndex) + "-";
            } else {
                returnStr += noDashStr.substring(endIndex, endIndex + K) + "-";
                endIndex += K;
            }
        }

        return returnStr;
    }

    public static void main(String[] args) {
        String S = "5F3Z-2e-9-w";
        String S2 = "r";
        LicenseKeyFormatting formatting = new LicenseKeyFormatting();
        System.out.println(formatting.licenseKeyFormatting(S2, 1));
    }
}
