package year2021.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Part2 {
    public static void main(String[] args) {
        String path = "D:\\Downloads\\input.txt";
        String[] file = readFile(path);

        int sumOfA = 0;
        int sumOfB = 0;
        int largerThanPreviousSum = 0;

        for (int i = 0; i < file.length - 3; i++) {
            sumOfA = Integer.parseInt(file[i]) + Integer.parseInt(file[i + 1]) + Integer.parseInt(file[i + 2]);
            sumOfB = Integer.parseInt(file[i + 1]) + Integer.parseInt(file[i + 2]) + Integer.parseInt(file[i + 3]);

            if (sumOfA < sumOfB) {
                largerThanPreviousSum++;
            }
        }

        System.out.println(largerThanPreviousSum);
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
