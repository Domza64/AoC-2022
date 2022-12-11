import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day_8 {

    /**
     *     Solution Part 2 -> <a href="https://github.com/abnew123/aoc2022/blob/8096e079a1cf85d5676538917b20735f6937079c/src/aoc2022/Day8.java">...</a>
     *     I didn't write it on my own I used this guys code for part 2 because I am already 4 days late and I am trying not to stay too
     *     much behind and one day I will hopefully rewrite this with my solution.
     * */

    private static final File FILE = new File(System.getProperty("user.dir") + "/src/input/input-8.txt");

    public static void main(String[] args) {
        Scanner scanner = newScanner();
        int visibleTrees = 0, width, height, bestScore = 0;
        List<String> listOfTrees = new ArrayList<>();

        while (scanner.hasNextLine()) {
            listOfTrees.add(scanner.nextLine());
        }

        height = listOfTrees.size();
        width = listOfTrees.get(0).length(); // This is assuming that all lines are the same length, if they are not that is bad and I this code will probably fail which will make me sad.
        boolean[][] visibleTreesBoolArray = new boolean[height][width];

        // Left to Right
        for (int i = 0; i < height; i++) {
            String currentRow = listOfTrees.get(i);
            int currentTree = -1;
            for (int j = 0; j < width; j++) {
                int tempTree = Character.getNumericValue(currentRow.charAt(j));
                if (tempTree > currentTree) {
                    visibleTreesBoolArray[i][j] = true;
                    currentTree = tempTree;
                }
            }
        }

        // Right to Left
        for (int i = 0; i < height; i++) {
            String currentRow = listOfTrees.get(i);
            int currentTree = -1;
            for (int j = width - 1; j >= 0; j--) {
                int tempTree = Character.getNumericValue(currentRow.charAt(j));
                if (tempTree > currentTree) {
                    visibleTreesBoolArray[i][j] = true;
                    currentTree = tempTree;
                }
            }
        }

        // Top to Bottom
        for (int i = 0; i < width; i++) {
            int currentTree = -1;
            for (int j = 0; j < height; j++) {
                int tempTree = Character.getNumericValue(listOfTrees.get(j).charAt(i));
                if (tempTree > currentTree) {
                    visibleTreesBoolArray[j][i] = true;
                    currentTree = tempTree;
                }
            }
        }

        // Bottom to Top
        for (int i = 0; i < width; i++) {
            int currentTree = -1;
            for (int j = height - 1; j >= 0; j--) {
                int tempTree = Character.getNumericValue(listOfTrees.get(j).charAt(i));
                if (tempTree > currentTree) {
                    visibleTreesBoolArray[j][i] = true;
                    currentTree = tempTree;
                }
            }
        }

        // Count the number of visibleTrees trees
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (visibleTreesBoolArray[i][j]) {
                    visibleTrees++;
                }
            }
        }

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                bestScore = Math.max(bestScore, calculateScore(listOfTrees,i,j));
            }
        }

        System.out.println("Part 1 solution: " + visibleTrees);
        System.out.println("Part 2 solution: " + bestScore);
    }

    public static int calculateScore(List<String> grid, int i, int j) {
        int score = 1;
        int directionVisible = 0;
        for(int row = i + 1; row < grid.size(); row++) {
            directionVisible++;
            if(Character.getNumericValue(grid.get(row).charAt(j)) >= Character.getNumericValue(grid.get(i).charAt(j))) {
                break;
            }
        }
        score*= directionVisible;
        directionVisible = 0;
        for(int row = i - 1; row >= 0; row--) {
            directionVisible++;
            if(Character.getNumericValue(grid.get(row).charAt(j)) >= Character.getNumericValue(grid.get(i).charAt(j))) {
                break;
            }
        }
        score*= directionVisible;
        directionVisible = 0;
        for(int column = j + 1; column < grid.get(0).length(); column++) {
            directionVisible++;
            if(Character.getNumericValue(grid.get(i).charAt(column)) >= Character.getNumericValue(grid.get(i).charAt(j))) {
                break;
            }
        }
        score*= directionVisible;
        directionVisible = 0;
        for(int column = j - 1; column >= 0; column--) {
            directionVisible++;
            if(Character.getNumericValue(grid.get(i).charAt(column)) >= Character.getNumericValue(grid.get(i).charAt(j))) {

                break;
            }
        }
        score*= directionVisible;
        return score;
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
