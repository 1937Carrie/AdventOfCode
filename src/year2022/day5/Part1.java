package year2022.day5;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Stack;

public class Part1 {
    public static void main(String[] args) {
        String path = "D:\\Downloads\\2022.day.5.input.txt";
        String[] content;

        try {
            content = Files.readString(Path.of(path), StandardCharsets.UTF_8).split("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stack<String>[] stacks = (Stack<String>[]) new Stack[9];
        initializeStacks(stacks);

        fillStacks(content, stacks);

        for (int row = 10; row < content.length; row++) {
            String[] splittedRow = content[row].split("\\s*[a-z]+\\s*");

            for (int moveCount = 0; moveCount < Integer.parseInt(splittedRow[1]); moveCount++) {
                int from = Integer.parseInt(splittedRow[2]) - 1;
                int to = Integer.parseInt(splittedRow[3]) - 1;

                stacks[to].push(stacks[from].pop());
                int n = 0;
            }
        }

        String stackPeek = "";

        for (Stack<String> current : stacks) {
            stackPeek += current.peek();
        }

        System.out.println(stackPeek);

    }

    private static void fillStacks(String[] content, Stack<String>[] stacks) {
        for (int row = 7; row >= 0; row--) {
            for (int column = 0; column < 9; column++) {
                char currentChar = content[row].charAt(column * 4 + 1);
                if (currentChar != ' ') {
                    stacks[column].push(String.valueOf(currentChar));
                }
            }
        }
    }

    private static void initializeStacks(Stack<String>[] stacks) {
        for (int i = 0; i < stacks.length; i++) {
            stacks[i] = new Stack<>();
        }
    }
}
