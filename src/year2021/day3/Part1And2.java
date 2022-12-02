package year2021.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Part1And2 {
    static long linesCount;

    public static void main(String[] args) {
        String path = "D:\\Downloads\\2021.day.3.input.txt";
        String[] file = readFile(path);

        int gammaRate = 0;
        int epsilonRate = 0;
        int oxygenGeneratorRating = 0;
        int CO2ScrubberRating = 0;

        ArrayList<int[]> ints = new ArrayList<>();
        int[][] matrix = new int[file[0].length()][(int) linesCount];

        int column = 0;

        for (String line : file) {
            char[] chars = line.toCharArray();

            int row = 0;

            int[] ints1 = new int[line.length()];

            for (char element : chars) {
                int value = element == 48 ? 0 : 1;
                matrix[row][column] = value;
                ints1[row] = value;
                row++;
            }

            ints.add(ints1);

            column++;
        }

        StringBuilder gammaSB = new StringBuilder();
        StringBuilder epsilonSB = new StringBuilder();

        for (int[] row : matrix) {
            int countZero = 0;
            int countOne = 0;

            for (int col : row) {
                if (col == 0) {countZero++;}
                else {countOne++;}
            }

            if (countZero > countOne) {
                gammaSB.append('0');
                epsilonSB.append('1');
            }
            else {
                gammaSB.append('1');
                epsilonSB.append('0');
            }
        }

        gammaRate = Integer.parseInt(gammaSB.toString(), 2);
        epsilonRate = Integer.parseInt(epsilonSB.toString(), 2);
        oxygenGeneratorRating = getOxygenGeneratorRating(ints);
        CO2ScrubberRating = getCO2ScrubberRating(ints);

        System.out.println("Part1And2 answer: " + gammaRate * epsilonRate);

        System.out.println("Part2 answer: " + oxygenGeneratorRating * CO2ScrubberRating);
    }

    private static int getCO2ScrubberRating(ArrayList<int[]> ints) {
        ArrayList<int[]> matrix = (ArrayList<int[]>) ints.clone();

        for (int i = 0; matrix.size() != 1; i++) {
            int oneCounter = 0;
            int zeroCounter = 0;

            for (int[] number : matrix) {
                if (number[i % number.length] == 0) {zeroCounter++;}
                else {oneCounter++;}
            }

            int notDominantBit = zeroCounter <= oneCounter ? 0 : 1;

            for (int k = 0; k < matrix.size(); k++) {
                if (matrix.get(k)[i] != notDominantBit) {
                    matrix.remove(k--);
                }
            }
        }

        StringBuilder CO2ScrubberRatingSB = new StringBuilder();

        for (int digit : matrix.get(0)) {
            CO2ScrubberRatingSB.append(digit);
        }
        return Integer.parseInt(CO2ScrubberRatingSB.toString(), 2);
    }

    private static int getOxygenGeneratorRating(ArrayList<int[]> ints) {
        ArrayList<int[]> matrix = (ArrayList<int[]>) ints.clone();

        for (int i = 0; matrix.size() != 1; i++) {
            int oneCounter = 0;
            int zeroCounter = 0;

            for (int[] number : matrix) {
                if (number[i % number.length] == 0) {zeroCounter++;}
                else {oneCounter++;}
            }

            int dominantBit = zeroCounter <= oneCounter ? 1 : 0;

            for (int k = 0; k < matrix.size(); k++) {
                if (matrix.get(k)[i] != dominantBit) {
                    matrix.remove(k--);
                }
            }
        }

        StringBuilder oxygenGeneratorSB = new StringBuilder();

        for (int digit : matrix.get(0)) {
            oxygenGeneratorSB.append(digit);
        }
        return Integer.parseInt(oxygenGeneratorSB.toString(), 2);
    }

    private static String[] readFile(String filePath) {
        Path path = Paths.get(filePath);

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
