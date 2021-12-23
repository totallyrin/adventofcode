import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ad14 {
    public static long part1(String[] input) {
        // read input
        String template = input[0];
        String[][] pairs = new String[input.length - 2][2];
        for (int i = 0; i < pairs.length; i++) {
            String[] temp = input[i + 2].split(" -> ");
            pairs[i] = temp;
        }
        for (int step = 0; step < 10; step++) {
            StringBuilder result = new StringBuilder();
            for (int letter = 0; letter < template.length() - 1; letter++) {
                String tempPair = template.substring(letter, letter + 2);
                for (String[] pair : pairs) {
                    if (pair[0].equals(tempPair)) {
                        result.append(tempPair.charAt(0));
                        result.append(pair[1]);
                        if (letter == template.length() - 2)
                            result.append(tempPair.charAt(1));
                        break;
                    }
                }
            }
            template = result.toString();
        }
        int[] count = new int[26];
        for (char c : template.toCharArray())
            count[c - 65]++;
        int max = 0, min = Integer.MAX_VALUE;
        for (int i : count) {
            if (i == 0)
                assert true;
            else if (i > max)
                max = i;
            else if (i < min)
                min = i;
        }
        return max - min;
    }

    public static int part2(String[] input) {
        // read input
        String template = input[0];
        String[][] pairs = new String[input.length - 2][2];
        for (int i = 0; i < pairs.length; i++) {
            String[] temp = input[i + 2].split(" -> ");
            pairs[i] = temp;
        }
        // read template into pairs
        Set<String> ps = new HashSet<>();
        Set<String> elems = new HashSet<>();


        for (int step = 0; step < 10; step++) {
            StringBuilder result = new StringBuilder();
            for (int letter = 0; letter < template.length() - 1; letter++) {
                String tempPair = template.substring(letter, letter + 2);
                for (String[] pair : pairs) {
                    if (pair[0].equals(tempPair)) {
                        result.append(tempPair.charAt(0));
                        result.append(pair[1]);
                        if (letter == template.length() - 2)
                            result.append(tempPair.charAt(1));
                        break;
                    }
                }
            }
            template = result.toString();
        }
        int[] count = new int[26];
        for (char c : template.toCharArray())
            count[c - 65]++;
        int max = 0, min = Integer.MAX_VALUE;
        for (int i : count) {
            if (i == 0)
                assert true;
            else if (i > max)
                max = i;
            else if (i < min)
                min = i;
        }
        return max - min;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        // code to read from file
        File file = new File("C:\\Users\\lucyw\\Documents\\adventofcode2021\\14\\input.txt");
        // read number of lines
        Scanner scanner = new Scanner(file);
        int lines = 0;
        while(scanner.hasNextLine()) {
            scanner.nextLine();
            lines++;
        }
        // reset scanner to top of file
        scanner = new Scanner(file);
        String[] input = new String[lines];
        for (int i = 0; i < lines; i++) {
            input[i] = scanner.nextLine();
        }

        System.out.println(part1(input));
        System.out.println("Time after part 1 (seconds): " + ((float)(System.currentTimeMillis() - startTime) / 1000));
        System.out.println(part2(input));
        System.out.println("End time (seconds): " + ((float)(System.currentTimeMillis() - startTime) / 1000));
    }
}
