package com.yangdi.algorithm;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class DataFileHandle {

    int[] getIntData(String fileName) {
        FileReader fileReader;

        try {
            fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            fileReader.close();

            String[] items = line.split(",");
            List<String> list = Arrays.asList(items);
            int[] nums = list.stream().mapToInt(Integer::parseInt).toArray();

            return nums;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
