package year2022.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Part2 {
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
        int winOrLose = 0;
        int itemScore = 0;

        if (myChoose.equals("X")) {
            winOrLose = 0;
            switch (opponentChoose) {
                case "A":
                    itemScore = 3;
                    break;
                case "B":
                    itemScore = 1;
                    break;
                case "C":
                    itemScore = 2;
                    break;
            }
        }
        else if (myChoose.equals("Y")) {
            winOrLose = 3;
            switch (opponentChoose) {
                case "A":
                    itemScore = 1;
                    break;
                case "B":
                    itemScore = 2;
                    break;
                case "C":
                    itemScore = 3;
                    break;
            }
        }
        else {
            winOrLose = 6;
            switch (opponentChoose) {
                case "A":
                    itemScore = 2;
                    break;
                case "B":
                    itemScore = 3;
                    break;
                case "C":
                    itemScore = 1;
                    break;
            }
        }

        return itemScore + winOrLose;
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
