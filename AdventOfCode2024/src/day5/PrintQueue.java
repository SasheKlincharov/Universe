package day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class PrintQueue {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("AdventOfCode2024/src/day5/input.txt"));
        String input;
        Map<Integer, List<Integer>> rules = new HashMap<>();
        while (!(input = br.readLine()).isEmpty()) {
            storeRule(rules, input);
        }

        int sumCorrect = 0;
        int sumCorrected = 0;
        while ((input = br.readLine()) != null) {
            List<Integer> pageNumbers = parsePageNumbers(input);
            if (isCorrectOrder(pageNumbers, rules)) {
                sumCorrect += pageNumbers.get(pageNumbers.size() / 2);
            } else {
                sumCorrected += pageNumbers.get(pageNumbers.size() / 2);
            }
        }

        // Part 1 Output
        System.out.println(sumCorrect);
        // Part 2 Output
        System.out.println(sumCorrected);
    }

    private static boolean isCorrectOrder(List<Integer> pageNumbers, Map<Integer, List<Integer>> rules) {
        boolean correctRight = checkRightSideOrder(pageNumbers, rules);
        boolean correctLeft = checkLeftSideOrder(pageNumbers, rules);

        return correctRight && correctLeft;
    }

    private static boolean checkLeftSideOrder(List<Integer> pageNumbers, Map<Integer, List<Integer>> rules) {
        boolean correctionMade = false;
        for (int i = 1; i < pageNumbers.size(); ++i) {
            for (int j = 0; j < i; ++j) {
                List<Integer> values = rules.get(pageNumbers.get(j));
                if (values == null) {
                    if (rules.get(pageNumbers.get(i)).contains(pageNumbers.get(j))) {
                        swapPlaces(pageNumbers, i, j);
                        correctionMade = true;
                    }
                    continue;
                }

                if (!values.contains(pageNumbers.get(i))) {
                    swapPlaces(pageNumbers, i, j);
                    correctionMade = true;
                }
            }
        }

        return !correctionMade;
    }

    private static boolean checkRightSideOrder(List<Integer> pageNumbers, Map<Integer, List<Integer>> rules) {
        boolean correctionMade = false;
        for (int i = 0; i < pageNumbers.size() - 1; ++i) {
            List<Integer> values = rules.get(pageNumbers.get(i));
            if (values == null) {
                continue;
            }

            for (int j = i + 1; j < pageNumbers.size(); ++j) {
                if (!values.contains(pageNumbers.get(j))) {
                    swapPlaces(pageNumbers, i, j);
                    correctionMade = true;
                }
            }
        }

        return !correctionMade;
    }

    private static void swapPlaces(List<Integer> pageNumbers, int i, int j) {
        int tmp = pageNumbers.get(i);
        pageNumbers.set(i, pageNumbers.get(j));
        pageNumbers.set(j, tmp);
    }

    private static List<Integer> parsePageNumbers(String pageInput) {
        return Arrays.stream(pageInput.split(","))
                     .map(Integer::parseInt)
                     .collect(Collectors.toList());
    }

    private static void storeRule(Map<Integer, List<Integer>> rules, String input) {
        String[] parts = input.split("\\|");
        int key = Integer.parseInt(parts[0]);
        int value = Integer.parseInt(parts[1]);

        if (rules.containsKey(key)) {
            rules.get(key).add(value);
        } else {
            ArrayList<Integer> values = new ArrayList<>();
            values.add(value);
            rules.put(key, values);
        }
    }
}
