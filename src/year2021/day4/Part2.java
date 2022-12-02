package year2021.day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Stream;

public class Part2 {
    public static void main(String[] args) {
        String path = "D:\\Downloads\\2021.day.4.input.txt";
        String[] file = readFile(path);

        int[] bingoNumbers = null;
        LinkedList<BingoBoard> bingoBoards = new LinkedList<>();

        String[] splittedFirstLine = file[0].split(",");
        bingoNumbers = getBingoNumbers(splittedFirstLine);

        int row = 0;
        int column = 0;
        BingoBoard bingoBoard = new BingoBoard();

        for (int i = 2; i < file.length; i++) {
            column = 0;

            if (file[i].isEmpty()) {
                row = 0;
                bingoBoards.add(bingoBoard);
                bingoBoard = new BingoBoard();
                continue;
            }

            fillInOneLineOnBoard(bingoBoard.board, file[i].split(" +"), row, column);
            row++;
        }

        bingoBoards.add(bingoBoard);

        int answer = 0;
        ArrayList<Integer> queue = new ArrayList<>();

        matchNumberToBoardElement:
        {
            for (int number : bingoNumbers) {
                for (int indexBoard = 0; indexBoard < bingoBoards.size(); indexBoard++) {
                    bingoBoards.get(indexBoard).findNumberOnBoard(number);
                    BingoBoard matchedBoard = bingoBoards.get(indexBoard).checkLine();

                    if (matchedBoard != null) {
                        if (!queue.contains(indexBoard)) {
                            queue.add(indexBoard);

                            if (queue.size() == 100) {
                                int sum = matchedBoard.sumOfUncheckedNumbers();
                                answer = sum * number;

                                break matchNumberToBoardElement;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }

    private static void fillInOneLineOnBoard(int[][] board, String[] line, int row, int column) {
        for (String number : line) {
            try {
                int digitalNumber = Integer.parseInt(number);
                board[row][column++] = digitalNumber;
            } catch (NumberFormatException e) {
//                        throw new RuntimeException(e);
            }
        }
    }

    private static int[] getBingoNumbers(String[] splittedFirstLine) {
        int[] bingoNumbers = new int[splittedFirstLine.length];

        for (int i = 0; i < splittedFirstLine.length; i++) {
            bingoNumbers[i] = Integer.parseInt(splittedFirstLine[i]);
        }

        return bingoNumbers;
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
