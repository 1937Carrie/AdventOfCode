package year2022.day2;

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
        String path = "D:\\Downloads\\2022.day.2.input.txt";
        String[] strings = readFile(path);

        int result = 0;

        for (String line : strings) {
            String[] round = line.split(" ");
            String opponentChoose = round[0];
            String myChoose = round[1];

            result += getResultForOneRound(opponentChoose, myChoose);
        }

        System.out.println(result);
    }

    private static int getResultForOneRound(String opponentChoose, String myChoose) {
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();

        stringIntegerHashMap.put("AX", 1 + 3);
        stringIntegerHashMap.put("BX", 1 + 0);
        stringIntegerHashMap.put("CX", 1 + 6);
        stringIntegerHashMap.put("AY", 2 + 6);
        stringIntegerHashMap.put("BY", 2 + 3);
        stringIntegerHashMap.put("CY", 2 + 0);
        stringIntegerHashMap.put("AZ", 3 + 0);
        stringIntegerHashMap.put("BZ", 3 + 6);
        stringIntegerHashMap.put("CZ", 3 + 3);

        return stringIntegerHashMap.get(opponentChoose + myChoose);
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
