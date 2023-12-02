import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ad13 {
    public static int part1(String[] input) {
        int count = 0;
        List<String> coords = new ArrayList<>();
        List<String> instructions = new ArrayList<>();
        // split input into coordinates and instructions
        for (String line : input) {
            if (line.equals(""))
                assert true;
            else if (Character.isDigit(line.charAt(0)))
                coords.add(line);
            else if (line.charAt(0) == 'f')
                instructions.add(line);
        }
        // read coords into grid
        boolean[][] grid = new boolean[2500][2500];
        for (String line : coords) {
            String[] coord = line.split(",");
            int x = Integer.parseInt(coord[0]), y = Integer.parseInt(coord[1]);
            grid[y][x] = true;
        }
        List<String[]> folds = new ArrayList<>();
        // read instructions
        for (String line : instructions) {
            String[] temp = line.split("=");
            temp[0] = temp[0].substring(temp[0].length() - 1);
            folds.add(temp);
        }
        int length = grid.length, width = grid[0].length;
        // do folds
        for (int i = 0; i < 1; i++) {
            String[] current = folds.get(i);
            int digit = Integer.parseInt(current[1]);
            // x fold
            if (current[0].equals("x")) {
                width = (grid[0].length - 1) / 2;
                boolean[][] folded = new boolean[length][width];
                for (int y = 0; y < grid.length; y++) {
                    for (int x = 0; x < grid[0].length; x++) {
                        // if on left side of fold
                        if (x < digit)
                            folded[y][x] = grid[y][x];
                        // if on right side of fold
                        else if (x > digit)
                            if (grid[y][x])
                                folded[y][digit - (x - digit)] = grid[y][x];
                    }
                }
                for (boolean[] booleans : folded) {
                    for (boolean b : booleans) {
                        if (b)
                            count++;
                    }
                }
                grid = folded;
            }
            // y fold
            else {
                length = (grid.length - 1) / 2;
                boolean[][] folded = new boolean[length][width];
                for (int y = 0; y < grid.length; y++) {
                    for (int x = 0; x < grid[0].length; x++) {
                        // if on left side of fold
                        if (y < digit)
                            folded[y][x] = grid[y][x];
                            // if on right side of fold
                        else if (y > digit)
                            if (grid[y][x])
                                folded[digit - (y - digit)][x] = grid[y][x];
                    }
                }
                for (boolean[] booleans : folded) {
                    for (boolean b : booleans) {
                        if (b)
                            count++;
                    }
                }
                grid = folded;
            }
        }
        return count;
    }

    public static int part2(String[] input) {
        List<String> coords = new ArrayList<>();
        List<String> instructions = new ArrayList<>();
        // split input into coordinates and instructions
        for (String line : input) {
            if (line.equals(""))
                assert true;
            else if (Character.isDigit(line.charAt(0)))
                coords.add(line);
            else if (line.charAt(0) == 'f')
                instructions.add(line);
        }
        // read coords into grid
        boolean[][] grid = new boolean[2500][2500];
        for (String line : coords) {
            String[] coord = line.split(",");
            int x = Integer.parseInt(coord[0]), y = Integer.parseInt(coord[1]);
            grid[y][x] = true;
        }
        List<String[]> folds = new ArrayList<>();
        // read instructions
        for (String line : instructions) {
            String[] temp = line.split("=");
            temp[0] = temp[0].substring(temp[0].length() - 1);
            folds.add(temp);
        }
        int length = grid.length, width = grid[0].length;
        // do folds
        for (String[] current : folds) {
            int digit = Integer.parseInt(current[1]);
            // x fold
            if (current[0].equals("x")) {
                width = (grid[0].length - 1) / 2;
                boolean[][] folded = new boolean[length][width];
                for (int y = 0; y < grid.length; y++) {
                    for (int x = 0; x < grid[0].length; x++) {
                        // if on left side of fold
                        if (x < digit)
                            folded[y][x] = grid[y][x];
                            // if on right side of fold
                        else if (x > digit)
                            if (grid[y][x])
                                folded[y][digit - (x - digit)] = grid[y][x];
                    }
                }
                grid = folded;
            }
            // y fold
            else {
                length = (grid.length - 1) / 2;
                boolean[][] folded = new boolean[length][width];
                for (int y = 0; y < grid.length; y++) {
                    for (int x = 0; x < grid[0].length; x++) {
                        // if on left side of fold
                        if (y < digit)
                            folded[y][x] = grid[y][x];
                            // if on right side of fold
                        else if (y > digit)
                            if (grid[y][x])
                                folded[digit - (y - digit)][x] = grid[y][x];
                    }
                }
                grid = folded;
            }
        }
        for (boolean[] booleans : grid) {
            for (boolean b : booleans) {
                if (b)
                    System.out.print("#");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
        return 0;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        // code to read from file
        File file = new File("C:\\Users\\lucyw\\Documents\\adventofcode2021\\13\\input.txt");
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
