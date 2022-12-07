import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day_3 {
    private static final File FILE = new File(System.getProperty("user.dir") + "/src/input/input-3.txt");
    private final static String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static int partOne() {
        Scanner scanner = newScanner();
        int sum = 0;
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();

            int tempNum = s.length() / 2;
            char[] compartmentOne  = s.substring(0, tempNum).toCharArray();
            char[] compartmentTwo = s.substring(tempNum).toCharArray();

            Set<Character> compOneSet = new HashSet<>();
            Set<Character> compTwoSet = new HashSet<>();

            for (char c : compartmentOne) {
                compOneSet.add(c);
            }
            for (char c : compartmentTwo) {
                compTwoSet.add(c);
            }

            Set<Character> sharedChars = new HashSet<>(compOneSet);
            sharedChars.retainAll(compTwoSet);

            for (char c : sharedChars) {
                sum += getPriorityNum(c);
            }
        }
        return sum;
    }

    public static int partTwo() {
        Scanner scanner = newScanner();
        Set<Character> tempSet;
        int sum = 0;

        while (scanner.hasNextLine()) {
            char[] charArray = scanner.nextLine().toCharArray();
            tempSet = charArrayToSet(charArray);

            for (int i = 0; i < 2; i++) {
                if (scanner.hasNextLine()) {
                    charArray = scanner.nextLine().toCharArray();
                    tempSet.retainAll(charArrayToSet(charArray));
                }
                else {
                    System.err.println("Probably only 1-2 elves left for last group; Not 3 but 3 elves is needed for a group");
                }
            }

            if (tempSet.size() > 1) {
                System.err.println("more than one of the same items");
            }
            else {
                sum += getPriorityNum(tempSet.toString().charAt(1));
            }
        }
        return sum;
    }

    private static int getPriorityNum(char c) {
        return ALPHABET.indexOf(c) + 1;
    }

    private static Set<Character> charArrayToSet(char[] a) {
        Set<Character> s = new HashSet<>();
        for (char c : a) {
            s.add(c);
        }
        return s;
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
