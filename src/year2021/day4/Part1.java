package year2021.day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.stream.Stream;

public class Part1 {
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
        matchNumberToBoardElement:
        {
            for (int number : bingoNumbers) {
                for (BingoBoard board : bingoBoards) {
                    board.findNumberOnBoard(number);
                    BingoBoard matchedBoard = board.checkLine();

                    if (matchedBoard != null) {
                        int sum = matchedBoard.sumOfUncheckedNumbers();
                        answer = sum * number;
                        break matchNumberToBoardElement;
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

class BingoBoard {
    int[][] board = new int[5][5];
    boolean[][] truthBoard = new boolean[5][5];
    private int truthCount = 0;

    boolean findNumberOnBoard(int number) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (board[row][column] == number) {
                    truthBoard[row][column] = true;
                    truthCount++;
                    return true;
                }
            }
        }
        return false;
    }

    BingoBoard checkLine() {
        if (truthCount > 4) {
            boolean fullLineMatched = true;
            for (int row = 0; row < truthBoard.length; row++) {
                for (int column = 0; column < truthBoard[0].length; column++) {
                    if (!truthBoard[row][column]) {
                        fullLineMatched = false;
                        break;
                    }
                }
                if (fullLineMatched) {return this;}
                fullLineMatched = true;
                for (int column = 0; column < truthBoard[0].length; column++) {
                    if (!truthBoard[column][row]) {
                        fullLineMatched = false;
                        break;
                    }
                }
                if (fullLineMatched) {return this;}
                fullLineMatched = true;
            }

        }
        return null;
    }

    int sumOfUncheckedNumbers() {
        int sum = 0;

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (!truthBoard[row][column]) {
                    sum += board[row][column];
                }
            }
        }
        return sum;
    }
}

