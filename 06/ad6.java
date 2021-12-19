import java.util.ArrayList;

public class ad6 {

    public static int part1(ArrayList<Integer> input) {
        // create copy of list
        ArrayList<Integer> inputs = new ArrayList<>(input);
        // each item in inputs is a fish, number represents timer
        for (int day = 0; day < 80; day++) {
            for (int i = 0; i < inputs.size(); i++) {
                if (inputs.get(i) == 0) {
                    inputs.set(i, 6);
                    inputs.add(9);
                }
                else {
                    inputs.set(i, inputs.get(i) - 1);
                }
            }
        }
        return inputs.size();
    }

    public static long part2(ArrayList<Integer> input) {
        long[] fish = new long[9]; // each index stores the number of fish at that cycle
        for (int ins : input)
            fish[ins]++;
        for (int day = 0; day < 256; day++) {
            long first = fish[0];
            for (int i = 0; i < fish.length - 1; i++)
                fish[i] = fish[i + 1];
            fish[6] += first;
            fish[8] = first;
        }
        long sum = 0;
        for (long num : fish)
            sum += num;
        return sum;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        String data = "1,4,2,4,5,3,5,2,2,5,2,1,2,4,5,2,3,5,4,3,3,1,2,3,2,1,4,4,2,1,1,4,1,4,4,4,1,4,2,4,3,3,3,3,1,1,5,4,2,5,2,4,2,2,3,1,2,5,2,4,1,5,3,5,1,4,5,3,1,4,5,2,4,5,3,1,2,5,1,2,2,1,5,5,1,1,1,4,2,5,4,3,3,1,3,4,1,1,2,2,2,5,4,4,3,2,1,1,1,1,2,5,1,3,2,1,4,4,2,1,4,5,2,5,5,3,3,1,3,2,2,3,4,1,3,1,5,4,2,5,2,4,1,5,1,4,5,1,2,4,4,1,4,1,4,4,2,2,5,4,1,3,1,3,3,1,5,1,5,5,5,1,3,1,2,1,4,5,4,4,1,3,3,1,4,1,2,1,3,2,1,5,5,3,3,1,3,5,1,5,3,5,3,1,1,1,1,4,4,3,5,5,1,1,2,2,5,5,3,2,5,2,3,4,4,1,1,2,2,4,3,5,5,1,1,5,4,3,1,3,1,2,4,4,4,4,1,4,3,4,1,3,5,5,5,1,3,5,4,3,1,3,5,4,4,3,4,2,1,1,3,1,1,2,4,1,4,1,1,1,5,5,1,3,4,1,1,5,4,4,2,2,1,3,4,4,2,2,2,3";
        String[] parts = data.split(",");
        ArrayList<Integer> input = new ArrayList<>();
        for (String part : parts)
            input.add(Integer.parseInt(part));
        System.out.println(part1(input));
        System.out.println("Time after part 1 (seconds): " + ((float)(System.currentTimeMillis() - startTime) / 1000));
        System.out.println(part2(input));
        System.out.println("End time (seconds): " + ((float)(System.currentTimeMillis() - startTime) / 1000));
    }
}
