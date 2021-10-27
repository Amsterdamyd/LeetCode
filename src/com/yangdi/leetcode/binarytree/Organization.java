package com.yangdi.leetcode.binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Organization {

    Map<String, List<String>> map = new HashMap<>();

    public String createOrg(String str) {
        String root = "";
        String[] employees = str.split(",");

        for (String employee : employees) {
            String[] items = employee.split("/");

            if ("-".equals(items[2])) {
                root = items[0];
            }

            if (map.containsKey(items[2])) {
                List<String> list = map.get(items[2]);
                list.add(items[1]);
            }

            map.put(items[1], new ArrayList<>());
        }

        return root;
    }

    public List<String> getEmployees(String employeeId) {
        return map.get(employeeId);
    }

    List<String> employee = new ArrayList<>();

    public List<String> getAllEmployees(String employeeId) {
        List<String> list = map.get(employeeId);
        if (list == null || list.size() == 0) {
            return employee;
        }

        for (String empId : list) {
            employee.add(empId);
            getAllEmployees(empId);
        }

        return employee;
    }

    //str = "J/1/-," + "B/2/1," + "A/3/1," + "S/4/3," + "D/5/3" + "M/6/3" + "R/7/5" + "F/8/6";
    public static void main(String[] args) {
        String str = "J/1/-," + "B/2/1," + "A/3/1," + "S/4/3," + "D/5/3," + "M/6/3," + "R/7/5," + "F/8/6";
        Organization org = new Organization();
        System.out.println(org.createOrg(str));
        System.out.println(org.getAllEmployees("3"));
    }
}
