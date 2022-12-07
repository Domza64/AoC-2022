import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day_5 {

    /*
    * This is incredibly and unrealisticly bad solution don't look pls I just wanted to get it done and I didnt have much time because work and life and I just wanted to make this work,
    * no matter how shitty unoptimized code it takes...
    * */
    private static final File FILE = new File(System.getProperty("user.dir") + "/src/input/input-5.txt");
    private static final int NO_OF_STACKS = 9;

    public static String partOne() {
        Scanner scanner = newScanner();
        ArrayList<ArrayList<String>> listOfStacks = getCreateArrangement(scanner);
        return rearrangeCreates(listOfStacks, scanner);
    }

    public static int partTwo() {
        return -1;
    }

    private static String rearrangeCreates(ArrayList<ArrayList<String>> list, Scanner scanner) {
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            if (s.equals("")) return "THAS ENDE MI FREND";
            String[] split = s.replaceAll("[^\\d.]", " ").strip().split("\\W+");

            int amount = Integer.parseInt(split[0]);
            int moveFrom = Integer.parseInt(split[1]);
            int moveTo = Integer.parseInt(split[2]);

            for (int i = 0; i < amount; i++) {
                ArrayList<String> listToMoveTo = list.get(moveTo - 1);
                ArrayList<String> listToRemoveFrom = list.get(moveFrom - 1);

                // Most shitty solution ever created, should probably use deque or some s**t but no I just want to make this work soo I am done with this.
                Collections.reverse(listToMoveTo);
                listToMoveTo.add(listToRemoveFrom.get(0));
                Collections.reverse(listToMoveTo);

                listToRemoveFrom.remove(0);
            }
        }
        StringBuilder solution = new StringBuilder();
        for (ArrayList<String> aList : list) {
            solution.append(aList.get(0));
        }

        return solution.toString();
    }

    private static ArrayList<ArrayList<String>> getCreateArrangement(Scanner scanner) {

        // Make ArrayList of ArrayList for each create stack
        ArrayList<ArrayList<String>> stacksList = new ArrayList<>();
        for (int i = 0; i < NO_OF_STACKS; i++) {
            stacksList.add(new ArrayList<>());
        }

        // Read all create positions
        boolean bol = true;
        while (bol) {
            String s = scanner.nextLine();
            int i = 0, lineNum = 1;

            while (i < NO_OF_STACKS) { // Scans every line
                char c = s.charAt(lineNum);

                if (Character.isDigit(c)) {
                    bol = false;
                    break;
                }

                if (!Character.toString(c).equals(" ")) {
                    stacksList.get(i).add(Character.toString(c));
                    // Add it to array
                }

                lineNum += 4;
                i++;
            }
        }
        scanner.next();
        return stacksList;
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
