package year2021.day6;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Part1 {
    public static void main(String[] args) {
        String path = "D:\\Downloads\\2021.day.6.input.txt";
        String[] content;

        try {
            content = Files.readString(Path.of(path), StandardCharsets.UTF_8).split("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        content = content[0].split(",");
        ArrayList<Integer> lanternfish = new ArrayList<>();

        for (String fish : content) {
            lanternfish.add(Integer.parseInt(fish));
        }

        int counter = 0;
        int days = 80;

        do {
            int size = lanternfish.size();

            for (int i = 0; i < size; i++) {
                if (lanternfish.get(i) == 0) {
                    lanternfish.set(i, 6);
                    lanternfish.add(8);
                }
                else {
                    lanternfish.set(i, lanternfish.get(i) - 1);
                }
            }

            counter++;
        } while (counter < days);

        System.out.println(lanternfish.size());
    }
}
