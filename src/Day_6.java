import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day_6 {

    private static final File FILE = new File(System.getProperty("user.dir") + "/src/input/input-6.txt");

    public static void main(String[] args) {
        System.out.println("Part 2: " + getMarkerNum(4));
        System.out.println("Part 2: " + getMarkerNum(14));
    }

    private static int getMarkerNum(int startOfMessageMarker) {
        Scanner scanner = newScanner();
        String s = "";
        if (scanner.hasNextLine()) s = scanner.nextLine();
        char[] charArray = s.toCharArray();

        for (int i = 0; i < charArray.length - startOfMessageMarker - 1; i++) {
            Set<Character> tempSet = new HashSet<>();

            boolean shouldContinue = false;
            for(int j = 0; j < startOfMessageMarker; j++) {
                if (!tempSet.add(charArray[i + j])) {
                    shouldContinue = true;
                    break;
                }
            }
            if (shouldContinue) continue;

            return i + startOfMessageMarker;
        }
        return -1;
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
