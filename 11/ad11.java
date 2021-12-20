import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ad11 {

    static int[][] grid;

    public static int part1(String[] input) {
        int count = 0;
        // read input data into grid
        grid = new int[input.length][input[0].length()];
        for (int y = 0; y < grid.length; y++) {
            char[] c = input[y].toCharArray();
            for (int x = 0; x < grid[0].length; x++)
                grid[y][x] = c[x] - 48;
        }
        for (int step = 0; step < 100; step++) {
            // increase energy level of each octopus by 1
            for (int y = 0; y < grid.length; y++)
                for (int x = 0; x < grid[0].length; x++)
                    grid[y][x]++;
            // check for energy level greater than 9
            for (int y = 0; y < grid.length; y++) {
                for (int x = 0; x < grid[0].length; x++) {
                    if (grid[y][x] > 9) {
                        count = flash(count, x, y);
                    }
                }
            }
            // set flashers to 0
            for (int y = 0; y < grid.length; y++) {
                for (int x = 0; x < grid[0].length; x++) {
                    if (grid[y][x] == -1) {
                        grid[y][x] = 0;
                    }
                }
            }
        }
        return count;
    }

    public static int flash(int count, int x, int y) {
        count++;
        boolean tl = false, tr = false, bl = false, br = false;
        // up
        if (y - 1 >= 0) {
            // straight up
            if (grid[y - 1][x] != -1) {
                grid[y - 1][x]++;
                if (grid[y - 1][x] == 10)
                     count = flash(count, x, y - 1);
            }
            // top left
            if (x - 1 >= 0) {
                if (grid[y - 1][x - 1] != -1) {
                    grid[y - 1][x - 1]++;
                    tl = true;
                    if (grid[y - 1][x - 1] == 10)
                        count =flash(count, x - 1, y - 1);
                }
            }
            // top right
            if (x + 1 < grid[0].length) {
                if (grid[y - 1][x + 1] != -1) {
                    grid[y - 1][x + 1]++;
                    tr = true;
                    if (grid[y - 1][x + 1] == 10)
                        count = flash(count, x + 1, y - 1);
                }
            }
        }
        // down
        if (y + 1 < grid.length) {
            // straight down
            if (grid[y + 1][x] != -1) {
                grid[y + 1][x]++;
                if (grid[y + 1][x] == 10)
                    count = flash(count, x, y + 1);
            }
            // bottom left
            if (x - 1 >= 0) {
                if (grid[y + 1][x - 1] != -1) {
                    grid[y + 1][x - 1]++;
                    bl = true;
                    if (grid[y + 1][x - 1] == 10)
                        count = flash(count, x - 1, y + 1);
                }
            }
            // bottom right
            if (x + 1 < grid[0].length) {
                if (grid[y + 1][x + 1] != -1) {
                    grid[y + 1][x + 1]++;
                    br = true;
                    if (grid[y + 1][x + 1] == 10)
                        count = flash(count, x + 1, y + 1);
                }
            }
        }
        // left
        if (x - 1 >= 0) {
            // straight left
            if (grid[y][x - 1] != -1) {
                grid[y][x - 1]++;
                if (grid[y][x - 1] == 10)
                    count = flash(count, x - 1, y);
            }
            // top left
            if (y - 1 >= 0 && !tl) {
                if (grid[y - 1][x - 1] != -1) {
                    grid[y - 1][x - 1]++;
                    if (grid[y - 1][x - 1] == 10)
                        count = flash(count, x - 1, y - 1);
                }
            }
            // bottom left
            if (y + 1 < grid.length && !bl) {
                if (grid[y + 1][x - 1] != -1) {
                    grid[y + 1][x - 1]++;
                    if (grid[y + 1][x - 1] == 10)
                        count = flash(count, x - 1, y + 1);
                }
            }
        }
        // right
        if (x + 1 < grid[0].length) {
            // straight right
            if (grid[y][x + 1] != -1) {
                grid[y][x + 1]++;
                if (grid[y][x + 1] == 10)
                    count = flash(count, x + 1, y);
            }
            // top right
            if (y - 1 >= 0 && !tr) {
                if (grid[y - 1][x + 1] != -1) {
                    grid[y - 1][x + 1]++;
                    if (grid[y - 1][x + 1] == 10)
                        count = flash(count, x + 1, y - 1);
                }
            }
            // bottom right
            if (y + 1 < grid.length && !br) {
                if (grid[y + 1][x + 1] != -1) {
                    grid[y + 1][x + 1]++;
                    if (grid[y + 1][x + 1] == 10)
                        count = flash(count, x + 1, y + 1);
                }
            }
        }
        grid[y][x] = -1;
        return count;
    }

    public static int part2(String[] input) {
        // read input data into grid
        grid = new int[input.length][input[0].length()];
        for (int y = 0; y < grid.length; y++) {
            char[] c = input[y].toCharArray();
            for (int x = 0; x < grid[0].length; x++)
                grid[y][x] = c[x] - 48;
        }
        int step = 0;
        while (true) {
            step++;
            // increase energy level of each octopus by 1
            for (int y = 0; y < grid.length; y++)
                for (int x = 0; x < grid[0].length; x++)
                    grid[y][x]++;
            // check for energy level greater than 9
            for (int y = 0; y < grid.length; y++) {
                for (int x = 0; x < grid[0].length; x++) {
                    if (grid[y][x] > 9) {
                        flash(0, x, y);
                    }
                }
            }
            // set flashers to 0
            for (int y = 0; y < grid.length; y++) {
                for (int x = 0; x < grid[0].length; x++) {
                    if (grid[y][x] == -1) {
                        grid[y][x] = 0;
                    }
                }
            }
            // check if all flashes are in sync
            boolean sync = true;
            for (int[] ints : grid) {
                for (int x = 0; x < grid[0].length; x++) {
                    if (ints[x] != 0) {
                        sync = false;
                        break;
                    }
                }
            }
            if (sync)
                return step;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        // code to read from file
        File file = new File("C:\\Users\\lucyw\\Documents\\adventofcode2021\\11\\input.txt");
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
