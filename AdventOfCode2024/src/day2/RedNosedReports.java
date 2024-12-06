package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;

public class RedNosedReports {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/day2/input.txt"));
        String line;
        int safeReports = 0;
        while ((line = br.readLine()) != null) {
            String[] levelStrings = line.split(" ");
            List<Integer> reportLevels = new ArrayList<>();
            for (String level : levelStrings) {
                reportLevels.add(Integer.parseInt(level));
            }
            if (isSafeReport(reportLevels)) {
                safeReports++;
            }
        }

        System.out.println(safeReports);
    }

    private static boolean isSafeReport(List<Integer> levels) {
        if (isSafe(levels)) {
            return true;
        }

        int k = 0;
        while (k++ < levels.size()) {
            for (int i = 0; i < levels.size(); i++) {
                List<Integer> tempList = new ArrayList<>(levels);
                tempList.remove(i);
                if (isSafe(tempList)) {
                    return true;
                }
            }
        }


        return false;
    }

    private static boolean isSafe(List<Integer> modifiedList) {
        for (int i = 0; i < modifiedList.size() - 1; i++) {
            if (Math.abs(modifiedList.get(i) - modifiedList.get(i + 1)) > 3) {
                return false;
            }
        }

        return allAsc(modifiedList) || allDesc(modifiedList);
    }

    private static boolean allAsc(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    private static boolean allDesc(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) <= list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
}
