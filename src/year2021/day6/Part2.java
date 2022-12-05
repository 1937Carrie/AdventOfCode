package year2021.day6;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Part2 {
    public static void main(String[] args) {
        String path = "D:\\Downloads\\2021.day.6.input.txt";
        String[] content;

        try {
            content = Files.readString(Path.of(path), StandardCharsets.UTF_8).split("\n");
            content = content[0].split(",");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long[] groupsOfLantern = new long[9];

        for (String currentValue : content) {
            groupsOfLantern[Integer.parseInt(currentValue)]++;
        }

        int days = 256;

        for (int i = 0; i < days; i++) {
            long tmpPrevious = 0;
            long tmpNext = 0;

            for (int index = groupsOfLantern.length - 1; index >= 0; index--) {
                if (index == 0) {
                    groupsOfLantern[8] = groupsOfLantern[0];
                    groupsOfLantern[6] += groupsOfLantern[0];
                    groupsOfLantern[index] = tmpPrevious;
                }
                else {
                    tmpNext = groupsOfLantern[index];
                    groupsOfLantern[index] = tmpPrevious;
                    tmpPrevious = tmpNext;
                }
            }
        }

        long total = Arrays.stream(groupsOfLantern).sum();
        System.out.println(total);
    }
}
