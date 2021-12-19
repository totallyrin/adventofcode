import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ad9 {
    public static int part1(String[] input) {
        int count = 0;
        int[][] map = new int[input.length][input[0].length()];
        for (int y = 0; y < input.length; y++) {
            char[] temp = input[y].toCharArray();
            for (int x = 0; x < temp.length; x++) {
                map[y][x] = temp[x] - 48;
            }
        }
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                boolean lowest = true;
                if (y - 1 >= 0) {
                    if (map[y][x] >= map[y - 1][x])
                        lowest = false;
                }
                if (y + 1 < map.length) {
                    if (map[y][x] >= map[y + 1][x])
                        lowest = false;
                }
                if (x - 1 >= 0) {
                    if (map[y][x] >= map[y][x - 1])
                        lowest = false;
                }
                if (x + 1 < map[y].length) {
                    if (map[y][x] >= map[y][x + 1])
                        lowest = false;
                }
                if (lowest)
                    count += map[y][x] + 1;
            }
        }
        return count;
    }

    public static int size;

    public static int part2(String[] input) {
        int count;
        int[][] map = new int[input.length + 2][input[0].length() + 2];
        // loop to pad map with 9s
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if ((y == 0 || y == map.length - 1) || (x == 0 || x == map[y].length - 1)) {
                    map[y][x] = 9;
                }
            }
        }
        for (int y = 0; y < input.length; y++) {
            char[] temp = input[y].toCharArray();
            for (int x = 0; x < temp.length; x++) {
                map[y + 1][x + 1] = temp[x] - 48;
            }
        }
        ArrayList<Integer> basins = new ArrayList<>();
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                size = -1;
                search(map, x, y);
                if (size != 0)
                    basins.add(size);
            }
        }
        basins.sort(Collections.reverseOrder());
        count = basins.get(0) * basins.get(1) * basins.get(2);
        return count;
    }

    public static void search(int[][] map, int x, int y) {
        // recursive depth-first search
        // set visited cells to 9s
        map[y][x] = 9;
        size++;
        if (y - 1 >= 0 && map[y - 1][x] != 9) {
            search(map, x, y - 1);
        }
        if (y + 1 < map.length && map[y + 1][x] != 9) {
            search(map, x, y + 1);
        }
        if (x - 1 >= 0 && map[y][x - 1] != 9) {
            search(map, x - 1, y);
        }
        if (x + 1 < map[y].length && map[y][x + 1] != 9) {
            search(map, x + 1, y);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        // code to read from file
        File file = new File("C:\\Users\\lucyw\\Documents\\adventofcode2021\\09\\input.txt");
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
