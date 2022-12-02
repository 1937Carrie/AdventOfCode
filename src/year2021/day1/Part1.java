package year2021.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Part1 {
    public static void main(String[] args) {
        String path = "D:\\Downloads\\input.txt";
        String[] file = readFile(path);

        int largerThanPrevious = 0;

        for (int i = 0; i < file.length - 1; i++) {
            if (Integer.parseInt(file[i + 1]) > Integer.parseInt(file[i])) {
                largerThanPrevious++;
            }
        }

        System.out.println(largerThanPrevious);
    }

    private static String[] readFile(String filePath) {
        Path path = Paths.get(filePath);
        long linesCount;

        try {
            linesCount = Files.lines(path).count();
        } catch (IOException exc) {
            throw new RuntimeException();
        }

        String line;
        String[] file = new String[(int) linesCount];

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
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
