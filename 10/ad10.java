import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ad10 {
    public static int part1(String[] input) {
        int count = 0;
        List<Character> opn = Arrays.asList('(', '[', '{', '<');
        List<Character> cls = Arrays.asList(')', ']', '}', '>');
        Set<Character> open = new HashSet<>(opn);
        Set<Character> close = new HashSet<>(cls);
        for (String line : input) {
            ArrayList<Character> stack = new ArrayList<>();
            for (char c : line.toCharArray()) {
                if (open.contains(c)) {
                    stack.add(c);
                }
                else if (close.contains(c)) {
                    boolean stop = false;
                    switch (c) {
                        case ')':
                            if (stack.get(stack.size() - 1) != '(') {
                                count += 3;
                                stop = true;
                            }
                            else
                                stack.remove(stack.size() - 1);
                            break;
                        case ']':
                            if (stack.get(stack.size() - 1) != '[') {
                                count += 57;
                                stop = true;
                            }
                            else
                                stack.remove(stack.size() - 1);
                            break;
                        case '}':
                            if (stack.get(stack.size() - 1) != '{') {
                                count += 1197;
                                stop = true;
                            }
                            else
                                stack.remove(stack.size() - 1);
                            break;
                        case '>':
                            if (stack.get(stack.size() - 1) != '<') {
                                count += 25137;
                                stop = true;
                            }
                            else
                                stack.remove(stack.size() - 1);
                            break;
                    }
                    if (stop)
                        break;
                }
            }
        }
        return count;
    }

    public static long part2(String[] input) {
        List<Character> opn = Arrays.asList('(', '[', '{', '<');
        List<Character> cls = Arrays.asList(')', ']', '}', '>');
        Set<Character> open = new HashSet<>(opn);
        Set<Character> close = new HashSet<>(cls);

        List<String> incomplete = new ArrayList<>();
        // remove corrupted lines from consideration
        for (String line : input) {
            boolean corrupted = false;
            List<Character> stack = new ArrayList<>();
            for (char c : line.toCharArray()) {
                if (open.contains(c))
                    stack.add(c);
                else if (close.contains(c)) {
                    boolean stop = false;
                    switch (c) {
                        case ')':
                            if (stack.get(stack.size() - 1) != '(') {
                                corrupted = true;
                                stop = true;
                            }
                            else
                                stack.remove(stack.size() - 1);
                            break;
                        case ']':
                            if (stack.get(stack.size() - 1) != '[') {
                                corrupted = true;
                                stop = true;
                            }
                            else
                                stack.remove(stack.size() - 1);
                            break;
                        case '}':
                            if (stack.get(stack.size() - 1) != '{') {
                                corrupted = true;
                                stop = true;
                            }
                            else
                                stack.remove(stack.size() - 1);
                            break;
                        case '>':
                            if (stack.get(stack.size() - 1) != '<') {
                                corrupted = true;
                                stop = true;
                            }
                            else
                                stack.remove(stack.size() - 1);
                            break;
                    }
                    if (stop)
                        break;
                }
            }
            if (!corrupted)
                incomplete.add(line);
        }

        System.out.println(incomplete);

        // get lines that need completion
        List<String> completion = new ArrayList<>();
        for (String line : incomplete) {
            List<Character> stack = new ArrayList<>();
            for (char c : line.toCharArray()) {
                if (open.contains(c))
                    stack.add(c);
                else if (close.contains(c))
                    stack.remove(stack.size() - 1);
            }
            StringBuilder s = new StringBuilder();
            for (char c : stack)
                s.append(c);
            completion.add(s.toString());
        }

        // get strings to complete lines
        List<String> completor = new ArrayList<>();
        for (String line : completion) {
            List<Character> add = new ArrayList<>();
            List<Character> curr = new ArrayList<>();
            for (char c : line.toCharArray())
                curr.add(c);
            Collections.reverse(curr);
            for (char c : curr) {
                switch (c) {
                    case '(' -> add.add(')');
                    case '[' -> add.add(']');
                    case '{' -> add.add('}');
                    case '<' -> add.add('>');
                }
            }
            StringBuilder s = new StringBuilder();
            for (char c : add)
                s.append(c);
            completor.add(s.toString());
        }

        System.out.println(completor);

        // calculate scores
        List<Long> scores = new ArrayList<>();
        for (String line : completor) {
            long total = 0;
            for (char c : line.toCharArray()) {
                total *= 5;
                switch (c) {
                    case ')' -> total += 1;
                    case ']' -> total += 2;
                    case '}' -> total += 3;
                    case '>' -> total += 4;
                }
            }
            scores.add(total);
        }
        Collections.sort(scores);
        System.out.println(scores);
        return scores.get((scores.size() - 1) / 2);
        // 194061416 is too low
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        // code to read from file
        File file = new File("C:\\Users\\lucyw\\Documents\\adventofcode2021\\10\\input.txt");
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
