package day4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Direction {
    final int col;
    final int row;

    public static final Direction UP = new Direction(-1, 0);
    public static final Direction DOWN = new Direction(1, 0);
    public static final Direction LEFT = new Direction(0, -1);
    public static final Direction RIGHT = new Direction(0, 1);
    public static final Direction UP_RIGHT = new Direction(-1, 1);
    public static final Direction UP_LEFT = new Direction(-1, -1);
    public static final Direction DOWN_RIGHT = new Direction(1, 1);
    public static final Direction DOWN_LEFT = new Direction(1, -1);

    public static Direction[] getAllDirections() {
        return new Direction[] { UP, DOWN, LEFT, RIGHT, UP_RIGHT, UP_LEFT, DOWN_LEFT, DOWN_RIGHT};
    }

    public Direction(int col, int row) {
        this.col = col;
        this.row = row;
    }
}

public class CeresSearch {

    private static final String SEARCH_WORD = "XMAS";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("AdventOfCode2024/src/day4/input.txt"));
        String line;
        List<List<Character>> grid = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            List<Character> charList = new ArrayList<>();
            for (char c : line.toCharArray()) {
                charList.add(c);
            }
            grid.add(charList);
        }

        System.out.println(countXmasWord(grid));
    }

    // Part 1
    private static int countXmasWord(List<List<Character>> grid) {
        int count = 0;
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                for (Direction dir : Direction.getAllDirections()) {
                    if (hasXmasInDir(grid, i, j, dir)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    // Part 2
    private static boolean hasMasInX(List<List<Character>> grid, int i, int j) {
        if (grid.get(i - 1).get(j - 1) == 'M'
                && grid.get(i + 1).get(j + 1) == 'S'
                && grid.get(i - 1).get(j + 1) == 'S'
                && grid.get(i + 1).get(j - 1) == 'M') {
            return true;
        }

        if (grid.get(i - 1).get(j - 1) == 'S'
                && grid.get(i + 1).get(j - 1) == 'S'
                && grid.get(i - 1).get(j + 1) == 'M'
                && grid.get(i + 1).get(j + 1) == 'M') {
            return true;
        }

        if (grid.get(i - 1).get(j - 1) == 'M'
                && grid.get(i + 1).get(j - 1) == 'S'
                && grid.get(i - 1).get(j + 1) == 'M'
                && grid.get(i + 1).get(j + 1) == 'S') {
            return true;
        }

        if (grid.get(i - 1).get(j - 1) == 'S'
                && grid.get(i + 1).get(j - 1) == 'M'
                && grid.get(i - 1).get(j + 1) == 'S'
                && grid.get(i + 1).get(j + 1) == 'M') {
            return true;
        }

        return false;
    }

    private static boolean hasXmasInDir(List<List<Character>> grid, int i, int j, Direction direction) {
        for (int k = 0; k < SEARCH_WORD.length(); k++) {
            int ni = i + k * direction.col;
            int nj = j + k * direction.row;

            if (ni < 0 || ni >= grid.size() || nj < 0 || nj >= grid.get(0).size() || grid.get(ni).get(nj) != SEARCH_WORD.charAt(k)) {
                return false;
            }
        }
        return true;
    }

}
