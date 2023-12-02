import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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

    public static long part2(String[] input) {
        // read input
        String template = input[0];
        String[][] pairs = new String[input.length - 2][2];
        for (int i = 0; i < pairs.length; i++) {
            String[] temp = input[i + 2].split(" -> ");
            pairs[i] = temp;
        }
        // read template into pairs
        String[][] ps = new String[pairs.length][2];
        List<String[]> elems = new ArrayList<>();
        // ps holds pair and number of occurrences
        for (int i = 0; i < pairs.length; i++) {
            ps[i][0] = pairs[i][0];
            ps[i][1] = "0";
        }
        // get existing pairs from template
        for (int i = 0; i < template.length() - 1; i++) {
            String curr = template.substring(i, i + 2);
            for (int j = 0; j < ps.length; j++) {
                String[] pair = ps[j];
                if (curr.equals(pair[0])) {
                    pair[1] = Integer.toString(Integer.parseInt(pair[1]) + 1);
                    ps[j] = pair;
                    break;
                }
            }
        }
        // elems holds char and number of occurrences
        for (int i = 0; i < template.length(); i++) {
            String curr = Character.toString(template.charAt(i));
            boolean exists = false;
            for (String[] item : elems)
                if (item[0].equals(curr)) {
                    int index = elems.indexOf(item);
                    String[] temp = elems.get(index);
                    temp[1] = Integer.toString(Integer.parseInt(temp[1]) + 1);
                    elems.set(index, temp);
                    exists = true;
                    break;
                }
            if (!exists) {
                String[] s = {Character.toString(template.charAt(i)), "1"};
                elems.add(s);
            }
        }
        // main sequence
        for (int step = 0; step < 40; step++) {
            String[][] aug = new String[ps.length][2];
            for (int i = 0; i < aug.length; i++) {
                aug[i][0] = ps[i][0];
                aug[i][1] = "0";
            }
            for (String[] pair : ps) {
                if (Integer.parseInt(pair[1]) > 0) {
                    String current = pair[0];
                    // get pair result and increment
                    for (String[] p : pairs) {
                        if (p[0].equals(current)) {
                            String elem = p[1];
                            boolean exists = false;
                            // increment if exists
                            for (String[] item : elems)
                                if (item[0].equals(elem)) {
                                    int index = elems.indexOf(item);
                                    String[] temp = elems.get(index);
                                    temp[1] = Integer.toString(Integer.parseInt(temp[1]) + Integer.parseInt(pair[1]));
                                    elems.set(index, temp);
                                    exists = true;
                                    break;
                                }
                            // create if not exists
                            if (!exists) {
                                String[] s = {elem, "1"};
                                elems.add(s);
                            }
                            // add new pairs
                            String pair1 = current.charAt(0) + elem, pair2 = elem + current.charAt(1);
                            for (int k = 0; k < Integer.parseInt(pair[1]); k++) {
                                int tcount = 0;
                                for (int j = 0; j < ps.length; j++) {
                                    if (tcount == 2)
                                        break;
                                    String[] p1 = ps[j];
                                    if (pair1.equals(p1[0]) || pair2.equals(p1[0])) {
                                        for (String[] item : aug)
                                            if (item[0].equals(p1[0])) {
                                                item[1] = Integer.toString(Integer.parseInt(item[1]) + 1);
                                                break;
                                            }
                                        tcount++;
                                    }
                                }
                            }
                            // reset counter
                            pair[1] = "0";
                            break;
                        }
                    }
                }
            }
            // increment pairs
            ps = Arrays.copyOf(aug, aug.length);
//            for (String s : aug)
//                for (String[] pair : ps)
//                    if (s.equals(pair[0])) {
//                        pair[1] = Integer.toString(Integer.parseInt(pair[1]) + 1);
//                        break;
//                    }
        }
        long max = 0, min = Integer.MAX_VALUE;
        for (String[] j : elems) {
            int i = Integer.parseInt(j[1]);
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
