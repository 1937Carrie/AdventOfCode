package year2022.day9;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;

public class Part1 {
    public static void main(String[] args) {
        //Correct answer = 6087, received answer = 6072
        String path = "D:\\Downloads\\2022.day.9.input.txt";
        String[] content;

        try {
            content = Files.readString(Path.of(path), StandardCharsets.UTF_8).split("\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HeadTail headTail = new HeadTail(new Point(0, 0), new Point(0, 0));

        HashSet<String> tails = new HashSet<>();
        tails.add(headTail.tail.x + ", " + headTail.tail.y);

        for (String line : content) {
            String[] splitted = line.split("\s");
            headTail = makeHT(splitted, headTail, tails);
        }

        System.out.println(tails.size());
    }

    private static HeadTail makeHT(String[] splitted, HeadTail headTail, HashSet<String> tails) {
        String vector = splitted[0];
        int delta = Integer.parseInt(splitted[1]);

        Point head = headTail.head;
        Point tail = headTail.tail;

        for (int i = 0; i < delta; i++) {
            switch (vector) {
                case "U" -> {
                    head.y++;
                    tail = checkDistance(head, tail) ? moveTtoH(head, tail) : tail;
                }
                case "D" -> {
                    head.y--;
                    tail = checkDistance(head, tail) ? moveTtoH(head, tail) : tail;
                }
                case "L" -> {
                    head.x--;
                    tail = checkDistance(head, tail) ? moveTtoH(head, tail) : tail;
                }
                default -> {
                    head.x++;
                    tail = checkDistance(head, tail) ? moveTtoH(head, tail) : tail;
                }
            }

            tails.add(tail.x + ", " + tail.y);
        }

        return new HeadTail(head, tail);
    }

    private static Point moveTtoH(Point head, Point tail) {
        if (head.x == tail.x) {
            if (head.y > tail.y) {
                return new Point(tail.x, tail.y + 1);
            }
            else {
                return new Point(tail.x, tail.y - 1);
            }
        }
        else if (head.y == tail.y) {
            if (head.x > tail.x) {
                return new Point(tail.x + 1, tail.y);
            }
            else {
                return new Point(tail.x - 1, tail.y);
            }
        }
        else {
            if (head.x < tail.x && head.y > tail.y) {
                return new Point(tail.x - 1, tail.y + 1);
            }
            else if (head.x > tail.x && head.y > tail.y) {
                return new Point(tail.x + 1, tail.y + 1);
            }
            else if (head.x > tail.x && head.y < tail.y) {
                return new Point(tail.x + 1, tail.y - 1);
            }
            else {
                return new Point(tail.x - 1, tail.y - 1);
            }
        }
    }

    private static boolean checkDistance(Point head, Point tail) {
        int deltaX = StrictMath.abs(StrictMath.abs(tail.x) - StrictMath.abs(head.x));
        int deltaY = StrictMath.abs(StrictMath.abs(tail.y) - StrictMath.abs(head.y));

        return deltaX > 1 || deltaY > 1;
    }
}


class HeadTail {
    Point head;
    Point tail;

    HeadTail(Point head, Point tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public String toString() {
        return "head = " + head + ", tail = " + tail;
    }
}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
