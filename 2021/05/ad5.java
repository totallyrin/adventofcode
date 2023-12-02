import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Program written for 2021's Advent of Code day 5.
 *
 * Author: totallyrin
 */

public class ad5 {

    public static int part1(int[][][] coords) {
        int[][] grid = new int[1000][1000];
        for (int[][] coord : coords) {
            // break if x1 != x2 and y1 != y2
            if (coord[0][0] == coord[0][1] || coord[1][0] == coord[1][1]) {
                // if x1 == x2, do y
                if (coord[0][0] == coord[0][1]) {
                    int starty = Math.min(coord[1][0], coord[1][1]), endy = Math.max(coord[1][0], coord[1][1]);
                    for (int i = starty; i <= endy; i++) {
                        grid[i][coord[0][0]]++;
                    }
                }
                // execute if y1 == y2
                if (coord[1][0] == coord[1][1]) {
                    int startx = Math.min(coord[0][0], coord[0][1]), endx = Math.max(coord[0][0], coord[0][1]);
                    for (int i = startx; i <= endx; i++) {
                        grid[coord[1][0]][i]++;
                    }
                }
            }
        }

        int overlaps = 0;

        for (int[] ints : grid) {
            for (int i : ints) {
                if (i > 1)
                    overlaps++;
            }
        }
        return overlaps;
    }

    public static int part2(int[][][] coords) {
        int[][] grid = new int[1000][1000];
        for (int[][] coord : coords) {
            // break if x1 != x2 and y1 != y2
            if (coord[0][0] == coord[0][1] || coord[1][0] == coord[1][1]) {
                // if x1 == x2, do y
                if (coord[0][0] == coord[0][1]) {
                    int starty = Math.min(coord[1][0], coord[1][1]), endy = Math.max(coord[1][0], coord[1][1]);
                    for (int i = starty; i <= endy; i++) {
                        grid[i][coord[0][0]]++;
                    }
                }
                // execute if y1 == y2
                if (coord[1][0] == coord[1][1]) {
                    int startx = Math.min(coord[0][0], coord[0][1]), endx = Math.max(coord[0][0], coord[0][1]);
                    for (int i = startx; i <= endx; i++) {
                        grid[coord[1][0]][i]++;
                    }
                }
            } else if (Math.abs(coord[0][1] - coord[0][0]) == Math.abs(coord[1][1] - coord[1][0])){
                int xdiff = coord[0][1] - coord[0][0], ydiff = coord[1][1] - coord[1][0]; // end - start
                // x increase
                if (xdiff >= 0) {
                    if (ydiff >= 0) {
                        for (int i = 0; i <= ydiff; i++) {
                            grid[coord[1][0] + i][coord[0][0] + i]++;
                        }
                    } else {
                        for (int i = 0; i <= Math.abs(ydiff); i++) {
                            grid[coord[1][0] - i][coord[0][0] + i]++;
                        }
                    }
                // x decrease
                } else {
                    if (ydiff >= 0) {
                        for (int i = 0; i <= ydiff; i++) {
                            grid[coord[1][0] + i][coord[0][0] - i]++;
                        }
                    } else {
                        for (int i = 0; i <= Math.abs(ydiff); i++) {
                            grid[coord[1][0] - i][coord[0][0] - i]++;
                        }
                    }
                    }
                }
            }

        int overlaps = 0;

        for (int[] ints : grid) {
            for (int i : ints) {
                if (i > 1)
                    overlaps++;
            }
        }
        return overlaps;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        // code to read from file
        File file = new File("C:\\Users\\lucyw\\Documents\\adventofcode2021\\05\\input.txt");
        // read number of lines
        Scanner scanner = new Scanner(file);
        int lines = 0;
        while(scanner.hasNextLine()) {
            scanner.nextLine();
            lines++;
        }
        // reset scanner to top of file
        scanner = new Scanner(file);
        int[][][] coords = new int[lines][2][2];
        for (int j = 0; j < coords.length; j++) {
            if (!scanner.hasNextLine())
                break;
            String data = scanner.nextLine();
            String[] parts = data.split(" -> ");
            // split input into [[x1, y1], [x2, y2]]
            String[] pair1 = parts[0].split(","), pair2 = parts[1].split(",");
            int[] p1 = new int[pair1.length], p2 = new int[pair2.length];
            for (int i = 0; i < pair1.length; i++) {
                p1[i] = Integer.parseInt(pair1[i]);
            }
            for (int i = 0; i < pair2.length; i++) {
                p2[i] = Integer.parseInt(pair2[i]);
            }
            // get coordinate pairs as [[x1, x2], [y1, y2]]
            int[][] coord = {{p1[0], p2[0]}, {p1[1], p2[1]}};
            coords[j] = coord;
        }

        System.out.println(part1(coords));
        System.out.println("Time after part 1 (seconds): " + ((float)(System.currentTimeMillis() - startTime) / 1000));
        System.out.println(part2(coords));
        System.out.println("End time (seconds): " + ((float)(System.currentTimeMillis() - startTime) / 1000));
    }
}
