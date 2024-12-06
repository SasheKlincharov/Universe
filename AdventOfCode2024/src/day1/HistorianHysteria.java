package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HistorianHysteria {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/day1/input.txt"));
        String line;
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            String [] locationIDs = line.split("\\s+");
            list1.add(Integer.parseInt(locationIDs[0]));
            list2.add(Integer.parseInt(locationIDs[1]));
        }

//        part1(list1, list2);
        part2(list1, list2);

    }

    private static void part2(List<Integer> list1, List<Integer> list2) {
        int[] similarity = new int[list1.size()];
        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0 ; j < list2.size(); j++) {
                if (list1.get(i).equals(list2.get(j))) {
                    similarity[i]++;
                }
            }
            similarity[i] *= list1.get(i);
        }

        System.out.println(Arrays.stream(similarity).sum());
    }

    private static void part1(List<Integer> list1, List<Integer> list2) {
        List<Integer> distances = new ArrayList<>();
        // Both lists are the same size
        int min1 = Integer.MAX_VALUE;
        int min1_i = -1;
        int min2 = Integer.MAX_VALUE;
        int min2_i = -1;
        while (!list1.isEmpty() && !list2.isEmpty()) {
            for (int i = 0; i < list1.size(); i++) {
                if (list1.get(i) < min1) {
                    min1 = list1.get(i);
                    min1_i = i;
                }

                if (list2.get(i) < min2) {
                    min2 = list2.get(i);
                    min2_i = i;
                }
            }
            distances.add(Math.abs(min2 - min1));
            list1.remove(min1_i);
            list2.remove(min2_i);
            min1 = Integer.MAX_VALUE;
            min2 = Integer.MAX_VALUE;
            min1_i = -1;
            min2_i = -1;
        }

        System.out.println(distances.stream().mapToInt(Integer::intValue).sum());
    }
}
