package year2022.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Stream;

public class Part1 {
    public static void main(String[] args) {
        String path = "D:\\Downloads\\2022.day.3.input.txt";
        String[] file = readFile(path);

        HashMap<String, Integer> priorities = new HashMap<>();

        int value = 1;
        int valueCapital = 27;

        for (char ch = 'a', chCapital = 'A'; ch <= 'z'; ch++, chCapital++) {
            priorities.put(String.valueOf(ch), value++);
            priorities.put(String.valueOf(chCapital), valueCapital++);
        }

        int sum = 0;

        for (String line : file) {
            String firstCompartment = line.substring(0, line.length() / 2);
            String secondCompartment = line.substring(line.length() / 2);

            for (char ch : firstCompartment.toCharArray()) {
                if (secondCompartment.contains(String.valueOf(ch))) {
                    sum += priorities.get(String.valueOf(ch));
                    break;
                }
            }
        }

        System.out.println(sum);
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
