package year2022.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class Part2 {
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

        for (int i = 0; i < file.length; i += 3) {
            List<String> symbolsInLine = Arrays.asList(file[i].split(""));
            Object[] distinctSymbolsInLine = symbolsInLine.stream().distinct().toArray();

            for (Object letter : distinctSymbolsInLine) {
                if (file[i + 1].contains((CharSequence) letter) && file[i + 2].contains((CharSequence) letter)) {
                    sum += priorities.get((String) letter);
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
