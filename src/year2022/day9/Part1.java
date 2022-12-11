package year2022.day9;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;

public class Part1 {
    static int hx = 0, hy = 0;
    static int tx = 0, ty = 0;

    public static void main(String[] args) {
        String path = "D:\\Downloads\\2022.day.9.input.txt";
        String[] content;

        try {
            content = Files.readString(Path.of(path), StandardCharsets.UTF_8).split("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HashMap<String, int[]> dd = new HashMap<>();
        dd.put("R", new int[]{1, 0});
        dd.put("U", new int[]{0, 1});
        dd.put("L", new int[]{-1, 0});
        dd.put("D", new int[]{0, -1});

//        HashSet<String> tailVisited = new HashSet<>();
        HashSet<String> tailVisited = new HashSet<>();
//        tailVisited.add(String.format("%d, %d", tx, ty));
        tailVisited.add(String.format("%d, %d", tx, ty));

        for (String line : content) {
            String[] splitted = line.split("\\s");

            String op = splitted[0];
            int amount = Integer.parseInt(splitted[1]);

            int dx = dd.get(op)[0];
            int dy = dd.get(op)[1];

            for (int i = 0; i < amount; i++) {
                move(dx, dy);
                tailVisited.add(String.format("%d, %d", tx, ty));
            }
        }

        System.out.println(tailVisited.size());

    }

    static boolean isTouching(int x1, int y1, int x2, int y2) {
        return StrictMath.abs(x1 - x2) <= 1 && StrictMath.abs(y1 - y2) <= 1;
    }

    static void move(int dx, int dy) {
        hx += dx;
        hy += dy;

        if (!isTouching(hx, hy, tx, ty)) {
            int sign_x = hx == tx ? 0 : (hx - tx) / StrictMath.abs(hx - tx);
            int sign_y = hy == ty ? 0 : (hy - ty) / StrictMath.abs(hy - ty);

            tx += sign_x;
            ty += sign_y;
        }
    }
}
