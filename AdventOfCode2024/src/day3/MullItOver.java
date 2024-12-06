package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MullItOver {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("AdventOfCode2024/src/day3/input.txt"));
        List<Integer> results = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            results.add(multiplyAndSum(line));
        }
        br.close();

        System.out.println(results.stream().mapToInt(Integer::intValue).sum());
    }

    private static int multiplyAndSum(String value) {
        Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)");
        Matcher matcher = pattern.matcher(value);

        int sum = 0;
        boolean enabled = true;
        while (matcher.find()) {
            String foundMatch = matcher.group();

            if (foundMatch.equals("do()")) {
                enabled = true;
            } else if (foundMatch.equals("don't()")) {
                enabled = false;
            }

            if (enabled && foundMatch.startsWith("mul")) {
                // Replaces everything that is not a digit or a comma
                String[] numbers = foundMatch.replaceAll("[^\\d+,]", "").split(",");
                int num1 = Integer.parseInt(numbers[0]);
                int num2 = Integer.parseInt(numbers[1]);
                sum += num1 * num2;
            }
        }
        return sum;
    }
}
