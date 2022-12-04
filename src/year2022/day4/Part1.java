package year2022.day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Part1 {
    public static void main(String[] args) {
        String path = "D:\\Downloads\\2022.day.4.input.txt";
        String[] file = readFile(path);

        int counter = 0;

        for (String line : file) {
            String[] sectionAssignmentForOnePair = line.split(",");
            String[] firstElfRange = sectionAssignmentForOnePair[0].split("-");
            String[] secondElfRange = sectionAssignmentForOnePair[1].split("-");

            boolean firstContainsFullSecond = Integer.parseInt(firstElfRange[0]) <= Integer.parseInt(secondElfRange[0]) && Integer.parseInt(firstElfRange[1]) >= Integer.parseInt(secondElfRange[1]);
            boolean secondContainsFullFirst = Integer.parseInt(secondElfRange[0]) <= Integer.parseInt(firstElfRange[0]) && Integer.parseInt(secondElfRange[1]) >= Integer.parseInt(firstElfRange[1]);

            if (firstContainsFullSecond || secondContainsFullFirst) {
                counter++;
            }
        }

        System.out.println(counter);
    }

    private static String[] readFile(String filePath) {
        Path path = Paths.get(filePath);

        long linesCount;
        String[] file;
        String line;

        try (Stream<String> lines = Files.lines(path); BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
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
