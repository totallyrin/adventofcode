import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ad8 {

    public static int part1(String[] input) {
        int count = 0;
        for (String line : input) {
            String[] parts = line.split("[|]");
            String[] output = parts[1].split(" ");
            for (String digit : output) {
                if (digit.length() == 2 || digit.length() == 3 || digit.length() == 4 || digit.length() == 7)
                    count++;
            }
        }
        return count;
    }

    public static int part2(String[] input) {
        int count = 0;
        for (String line : input) {
            String[] parts = line.split("[|]");
            String[] inpt = parts[0].split(" ");
            for (int i = 0; i < inpt.length; i++)
                inpt[i] = inpt[i].trim();
            String[] output = Arrays.copyOfRange(parts[1].split(" "), 1, parts[1].split(" ").length);
            String[] digits = new String[10];
            for (String digit : inpt) {
                switch (digit.length()) {
                    case 2 -> digits[1] = digit;
                    case 3 -> digits[7] = digit;
                    case 4 -> digits[4] = digit;
                    case 7 -> digits[8] = digit;
                }
            }
            for (String digit : inpt) {
                Set<Character> dig = new HashSet<>();
                char[] temp = digit.toCharArray();
                for (char c : temp)
                    dig.add(c);
                switch (digit.length()) {
                    case 2:
                    case 3:
                    case 4:
                    case 7:
                        break;
                    case 6:
                        char[] con4 = digits[4].toCharArray(), con7 = digits[7].toCharArray();
                        Set<Character> c4 = new HashSet<>(), c7 = new HashSet<>();
                        for (char c : con4)
                            c4.add(c);
                        for (char c : con7)
                            c7.add(c);
                        boolean has4 = dig.containsAll(c4), has7 = dig.containsAll(c7);
                        if (has4)
                            digits[9] = digit;
                        else if (has7)
                            digits[0] = digit;
                        else
                            digits[6] = digit;
                        break;
                    case 5:
                        con7 = digits[7].toCharArray(); con4 = digits[4].toCharArray();
                        char[] con1 = digits[1].toCharArray();
                        Set<Character> c1 = new HashSet<>();
                        for (char c : con1)
                            c1.add(c);
                        c7 = new HashSet<>(); c4 = new HashSet<>();
                        for (char c : con4) {
                            if (!c1.contains(c))
                                c4.add(c);
                        }
                        for (char c : con7)
                            c7.add(c);
                        has7 = dig.containsAll(c7);
                        boolean is5 = dig.containsAll(c4);
                        if (has7)
                            digits[3] = digit;
                        else if (is5)
                            digits[5] = digit;
                        else
                            digits[2] = digit;
                        break;
                }
            }
            StringBuilder s = new StringBuilder();
            for (String digit : output) {
                if (digit.length() == 2)
                    s.append("1");
                else if (digit.length() == 3)
                    s.append("7");
                else if (digit.length() == 4)
                    s.append("4");
                else if (digit.length() == 7)
                    s.append("8");
                else {
                    Set<Character> dig = new HashSet<>();
                    for (char c : digit.toCharArray())
                        dig.add(c);
                    if (digit.length() == 6) {
                        char[] con4 = digits[4].toCharArray(), con7 = digits[7].toCharArray();
                        Set<Character> c4 = new HashSet<>(), c7 = new HashSet<>();
                        for (char c : con4)
                            c4.add(c);
                        for (char c : con7)
                            c7.add(c);
                        boolean has4 = dig.containsAll(c4), has7 = dig.containsAll(c7);
                        if (has4)
                            s.append("9");
                        else if (has7)
                            s.append("0");
                        else
                            s.append("6");
                    }
                    if (digit.length() == 5) {
                        char[] con7 = digits[7].toCharArray(), con4 = digits[4].toCharArray(), con1 = digits[1].toCharArray();
                        Set<Character> c1 = new HashSet<>(), c7 = new HashSet<>(), c4 = new HashSet<>();
                        for (char c : con1)
                            c1.add(c);
                        for (char c : con4) {
                            if (!c1.contains(c))
                                c4.add(c);
                        }
                        for (char c : con7)
                            c7.add(c);
                        boolean has7 = dig.containsAll(c7);
                        boolean is5 = dig.containsAll(c4);
                        if (has7)
                            s.append("3");
                        else if (is5)
                            s.append("5");
                        else
                            s.append("2");
                    }
                }

            }
            if (!s.toString().equals("")) {
                count += Integer.parseInt(s.toString());
            }
        }
        return count;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        // code to read from file
        File file = new File("C:\\Users\\lucyw\\Documents\\adventofcode2021\\08\\input.txt");
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
