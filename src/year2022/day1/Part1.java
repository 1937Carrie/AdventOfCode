package year2022.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class Part1 {
    public static void main(String[] args) {
        String path = "D:\\Downloads\\2022_day1.txt";
        String[] strings = readFile(path);
        ArrayList<Integer> totalCaloriesByElf = new ArrayList<>();
        int totalBySingle = 0;
        for (String line : strings) {
            if (line.isEmpty()) {
                totalCaloriesByElf.add(totalBySingle);
                totalBySingle = 0;
            }
            else {
                totalBySingle += Integer.parseInt(line);
            }
        }
        totalCaloriesByElf.sort(Collections.reverseOrder());
        System.out.println(totalCaloriesByElf.get(0));
    }

    private static String[] readFile(String path) {
        String s;

        try (FileReader fileReader = new FileReader(path);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            java.util.ArrayList<String> arrayList = new java.util.ArrayList<>();

            while ((s = bufferedReader.readLine()) != null) {
                arrayList.add(s);
            }

            return arrayList.toArray(new String[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
