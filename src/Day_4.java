import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day_4 {
    private static final File FILE = new File(System.getProperty("user.dir") + "/src/input/input-4.txt");

    public static int partOne() {
        Scanner scanner = newScanner();
        int sum = 0;
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();

            String[] elvesInGroup = s.split(",");

            String[] elveOne = elvesInGroup[0].split("-");
            String[] elveTwo = elvesInGroup[1].split("-");

            if ((Integer.parseInt(elveOne[0]) >= Integer.parseInt(elveTwo[0])) && (Integer.parseInt(elveOne[1]) <= Integer.parseInt(elveTwo[1]))) {
                sum++;
            }
            else if ((Integer.parseInt(elveOne[0]) <= Integer.parseInt(elveTwo[0])) && (Integer.parseInt(elveOne[1]) >= Integer.parseInt(elveTwo[1]))) {
                sum++;
            }
        }
        return sum;
    }

    public static int partTwo() {
        Scanner scanner = newScanner();
        int sum = 0;
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();

            String[] elvesInGroup = s.split(",");

            String[] elveOne = elvesInGroup[0].split("-");
            String[] elveTwo = elvesInGroup[1].split("-");

            if (Integer.parseInt(elveOne[1]) >= Integer.parseInt(elveTwo[0]) && (Integer.parseInt(elveTwo[1]) >= Integer.parseInt(elveOne[0]))) {
                sum++;
            }
        }
        // Ans 945 -> Too High
        return sum;
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
