package year2022.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

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

    private static String[] readFile(String filePath) {
        Path path = Paths.get(filePath);

        long linesCount;
        String[] file;
        String line;

        try (Stream<String> lines = Files.lines(path);
             BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            linesCount = lines.count();

            file = new String[(int) linesCount];

            int lineCounter = 0;

            while ((line = bufferedReader.readLine()) != null) {
                file[lineCounter++] = line;
            }
        } catch (IOException exc) {
            throw new RuntimeException();
        }

        return file;
    }

}
