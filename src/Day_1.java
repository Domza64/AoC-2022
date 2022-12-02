import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day_1 {
    private static final File FILE = new File(System.getProperty("user.dir") + "/src/input/input-1.txt");

    public static int partOne() {
        Scanner scanner = newScanner();
        int maxCalories = 0;
        int tempCalories = 0;

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();

            if (s.equals("")) {
                maxCalories = Math.max(maxCalories, tempCalories);
                tempCalories = 0;
            }
            else {
                tempCalories += Integer.parseInt(s);
            }
        }
        return maxCalories;
    }

    public static int partTwo() {
        Scanner scanner = newScanner();
        ArrayList<Integer> list = new ArrayList<>();
        int tempCalories = 0;

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            if (s.equals("")) {
                list.add(tempCalories);
                tempCalories = 0;
            }
            else {
                tempCalories += Integer.parseInt(s);
            }
        }

        list.sort(null);
        int solution = 0;
        for (int i = 1; i < 4; i++) {
            solution += list.get(list.size() - i);
        }
        return solution;
    }

    private static Scanner newScanner() {
        try {
            return new Scanner(FILE);
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found. :(");
            throw new RuntimeException(e);
        }

    }
}
