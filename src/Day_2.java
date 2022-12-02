import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day_2 {
    private static final File FILE = new File(System.getProperty("user.dir") + "/src/input/input-2.txt");

    public static int partOne() {
        Scanner scanner = newScanner();
        int score = 0;
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String[] roundChoices = s.split(" "); // index 0 is opponent, index 1 is player
            switch (roundChoices[1]) {
                case "X" -> {
                    score += 1; // Rock
                    roundChoices[1] = "A";
                }
                case "Y" -> {
                    score += 2; // Paper
                    roundChoices[1] = "B";
                }
                case "Z" -> {
                    score += 3; // Scissors
                    roundChoices[1] = "C";
                }
                default -> roundChoices[1] = "Err";
            }
            score += calculatePlayerScore(roundChoices[0], roundChoices[1]);
        }
        return score;
    }

    public static int partTwo() {
        Scanner scanner = newScanner();
        int score = 0;
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String[] roundChoices = s.split(" "); // index 0 is opponent, index 1 is player
            switch (roundChoices[1]) {
                case "X" -> { // loose
                    score += 0;
                    score += characterScore(shouldPlay(roundChoices[0], false));
                }
                case "Y" -> { // draw
                    score += 3;
                    score += characterScore(roundChoices[0]);
                }
                case "Z" -> { // Win
                    score += 6;
                    score += characterScore(shouldPlay(roundChoices[0], true));
                }
                default -> roundChoices[1] = "Err";
            }
        }
        return score;
    }

    private static String shouldPlay(String opponentCharacter, boolean win) { // Returns character that should be played
        // A - Rock
        // B - Paper
        // C - Scissors

        // A > C
        // B > A
        // C > B
        return switch (opponentCharacter) {
            case "A" -> (win) ? "B" : "C";
            case "B" -> (win) ? "C" : "A";
            case "C" -> (win) ? "A" : "B";
            default -> "Err";
        };
    }


    private static int characterScore(String character) { // Returns character that should be played
        switch (character) {
            case "A" -> {
                return 1;
            }
            case "B" -> {
                return 2;
            }
            case "C" -> {
                return 3;
            }
            default -> {
                return -1;
            }
        }
    }

    private static int calculatePlayerScore(String opponent, String player) {
        // A - Rock
        // B - Paper
        // C - Scissors

        // A > C
        // B > A
        // C > B
        if (player.equals(opponent)) {
            return 3;
        }
        switch (opponent) {
            case "A":
                return (player.equals("B")) ? 6 : 0;
            case "B":
                return (player.equals("C")) ? 6 : 0;
            case "C":
                return (player.equals("A")) ? 6 : 0;
        }
        System.err.println("Not supposed to happen.");
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
