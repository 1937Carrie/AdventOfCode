package year2021.day25;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Part1 {
    static Boolean isCucumbersMoved = false;

    public static void main(String[] args) {
        String path = "D:\\Downloads\\2021.day.25.input.txt";
        String[] file = readFile(path);
        char[][] oceanBottomMatrix = getMatrix(file);

        int step = 0;

        do {
            isCucumbersMoved = false;
            moveEastFacing(oceanBottomMatrix);
            moveSouthFacing(oceanBottomMatrix);
            step++;
        } while (isCucumbersMoved);

        System.out.println(step);
    }

    private static void moveSouthFacing(char[][] oceanBottomMatrix) {
        for (int column = 0; column < oceanBottomMatrix[0].length; column++) {
            char firstPosition = oceanBottomMatrix[0][column];
            for (int row = 0; row < oceanBottomMatrix.length; row++) {
                char unit = oceanBottomMatrix[row][column];
                char southFacingHerd = 'v';
                char empty = '.';

                if (unit == southFacingHerd) {
                    char nextPosition = row + 1 < oceanBottomMatrix.length ? oceanBottomMatrix[row + 1][column] : firstPosition;

                    if (nextPosition == empty) {
                        if (row + 1 < oceanBottomMatrix.length) {
                            oceanBottomMatrix[row + 1][column] = southFacingHerd;
                            oceanBottomMatrix[row][column] = empty;
                            row++;
                        }
                        else {
                            oceanBottomMatrix[0][column] = southFacingHerd;
                            oceanBottomMatrix[row][column] = empty;
                        }

                        isCucumbersMoved = true;
                    }
                }
            }
        }
    }

    public static void moveEastFacing(char[][] oceanBottomMatrix) {
        for (char[] bottomMatrixRow : oceanBottomMatrix) {
            char firstPosition = bottomMatrixRow[0];
            for (int position = 0; position < bottomMatrixRow.length; position++) {
                char unit = bottomMatrixRow[position];
                char eastFacingHerd = '>';
                char empty = '.';

                if (unit == eastFacingHerd) {
                    char nextPosition = position + 1 < bottomMatrixRow.length ? bottomMatrixRow[position + 1] : firstPosition;

                    if (nextPosition == empty) {
                        if (position + 1 < bottomMatrixRow.length) {
                            bottomMatrixRow[position + 1] = eastFacingHerd;
                            bottomMatrixRow[position] = empty;
                            position++;
                        }
                        else {
                            bottomMatrixRow[0] = eastFacingHerd;
                            bottomMatrixRow[position] = empty;
                        }

                        isCucumbersMoved = true;
                    }
                }
            }
        }

    }

    private static char[][] getMatrix(String[] file) {
        char[][] bottom = new char[file.length][file[0].length()];

        for (int row = 0; row < file.length; row++) {
            for (int column = 0; column < file[0].length(); column++) {
                bottom[row][column] = file[row].charAt(column);
            }
        }

        return bottom;
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
