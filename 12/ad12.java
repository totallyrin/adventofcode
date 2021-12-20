import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ad12 {

    static List<String> visited;

    public static int part1(String[] input) {
        String[][] lines = new String[input.length][2];
        for (int i = 0; i < input.length; i++) {
            lines[i][0] = input[i].split("-")[0];
            lines[i][1] = input[i].split("-")[1];
        }
        List<List<String>> paths = new ArrayList<>();
        List<String> path = new ArrayList<>();
        for (String[] line : lines) {
            visited = new ArrayList<>();
            if (line[0].equals("start")) {
                path.add(line[0]);
                visited.add(line[0]);
                List<String> temp = pathfind(lines, line[0], line[1], path);
                if (temp.get(temp.size() - 1).equals("end"))
                    paths.add(temp);
            }
            else if (line[1].equals("start")) {
                path.add(line[1]);
                visited.add(line[1]);
                List<String> temp = pathfind(lines, line[1], line[0], path);
                if (temp.get(temp.size() - 1).equals("end"))
                    paths.add(temp);
            }
        }
        for (List<String> p : paths)
            System.out.println(p);
        return paths.size();
    }

    public static List<String> pathfind(String[][] lines, String start, String end, List<String> path) {
        path.add(end);
        // if end has been reached, return
        if (end.equals("end") || path.get(path.size() - 1).equals("end"))
            return path;
        // if visited return
        if (end.equals(end.toLowerCase()) && visited.contains(end))
            return path;
        // if not visited, mark as visited
        if (end.equals(end.toLowerCase()) && !visited.contains(end))
            visited.add(start);

        for (String[] line : lines) {
            if (line[0].equals(end)) {
                path.addAll(pathfind(lines, line[0], line[1], new ArrayList<>()));
            }
            else if (line[1].equals(end) && !visited.contains(start)) {
                path.addAll(pathfind(lines, line[1], line[0], new ArrayList<>()));
            }
        }
        return path;
    }

    public static int part2(String[] input) {
        return 0;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        // code to read from file
        File file = new File("C:\\Users\\lucyw\\Documents\\adventofcode2021\\12\\input.txt");
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
