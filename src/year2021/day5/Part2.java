package year2021.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Part2 {
    public static void main(String[] args) {
        String path = "D:\\Downloads\\2021.day.5.input.txt";
        String[] file = readFile(path);

        int[][] oceanFloor = new int[1000][1000];

        for (String line : file) {
            String[] splittedLine = line.split("\\b -> \\b");

            int x1 = Integer.parseInt(splittedLine[0].split(",")[0]);
            int y1 = Integer.parseInt(splittedLine[0].split(",")[1]);
            int x2 = Integer.parseInt(splittedLine[1].split(",")[0]);
            int y2 = Integer.parseInt(splittedLine[1].split(",")[1]);

            if (x1 == x2) {
                for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                    oceanFloor[y][x1]++;
                }
            }
            else if (y1 == y2) {
                for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                    oceanFloor[y1][x]++;
                }
            }
            else if (Math.max(x1, x2) - Math.min(x1, x2) == Math.max(y1, y2) - Math.min(y1, y2)) {
                int deltaX = x1 > x2 ? -1 : 1;
                int deltaY = y1 > y2 ? -1 : 1;

                for (int i = 0, x = x1, y = y1; i <= Math.max(y1, y2) - Math.min(y1, y2); i++, x += deltaX, y += deltaY) {
                    oceanFloor[y][x]++;
                }
            }
        }

        int answer = 0;

        for (int[] row : oceanFloor) {
            for (int column : row) {
                if (column > 1) {
                    answer++;
                }
            }
        }

        System.out.println(answer);

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
