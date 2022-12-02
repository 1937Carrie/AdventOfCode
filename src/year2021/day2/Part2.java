package year2021.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Part2 {
    public static void main(String[] args) {
        String path = "D:\\Downloads\\2021.day.2.input.txt";
        String[] file = readFile(path);

        int horizontal = 0;
        int depth = 0;
        int aim = 0;

        for (String line : file) {
            String[] split = line.split("\\b\\D*\\b");
            int xValue = Integer.parseInt(split[2]);

            if (line.contains("forward")) {
                horizontal += xValue;
                depth += aim * xValue;
            }
            else {
                if (line.contains("up")) {
                    aim -= xValue;
                }
                else {
                    aim += xValue;
                }
            }
        }

        System.out.println(horizontal * depth);
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
