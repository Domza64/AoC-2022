import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day_7 {

    private static final File FILE = new File(System.getProperty("user.dir") + "/src/input/input-7.txt");
    private static int tempInt = 0;
    private static final ArrayList<Directory> dirsToDeleteList = new ArrayList<>();

    public static void main(String[] args) {
        Directory root = new Directory(null, "/");
        readInput(root);

        int rootSize = getDirSize(root);
        System.out.println("Total size (unimportant): " + rootSize);
        System.out.println("Part 1 solution: " + tempInt);


        int unusedSpace = 70000000 - rootSize;
        int neededSpace = 30000000 - unusedSpace;

        getDirsToDelete(root, neededSpace);

        int smallestDir = 0;
        // Should probably use binary search or something more efficient than bruteforceing through the list
        for (Directory dir : dirsToDeleteList) {
            int tempSize = getDirSize(dir);
            if (tempSize < smallestDir) {
                smallestDir = tempSize;
                continue;
            }
            if (smallestDir == 0) smallestDir = tempSize;
        }
        System.out.println("Part 2 solution: " + smallestDir);
    }

    private static int getDirSize(Directory dir) {
        int size = 0;
        for (FileX f : dir.files) {
            size += f.size;
        }
        for (Directory d : dir.directories) {
            size += getDirSize(d);
        }
        // tempInt is definitely not the best solution but it works and I am to tiered to think of something better.
        if (size <= 100000) tempInt += size;
        return size;
    }

    private static int getDirsToDelete(Directory dir, int neededSpace) {
        int size = 0;
        for (FileX f : dir.files) {
            size += f.size;
        }
        for (Directory d : dir.directories) {
            int tempSize = getDirsToDelete(d, neededSpace);
            size += tempSize;
            if (tempSize >= neededSpace) {
                dirsToDeleteList.add(d);
            }
        }
        return size;
    }

    private static void readInput(Directory root) {
        Scanner scanner = newScanner();
        Directory currentDir = root;
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();

            if (Character.toString(s.charAt(0)).equals("$")) {
                // It must be a command
                // It is a command, proces it as a command
                String[] command = s.split(" ");
                if (command[1].equals("ls")) {
                    // Print contents of a dir
                    // Actually don't have to do anything here
                }
                else {
                    // Must be cd, so change dir
                    if (command[2].equals("..")) {
                        // Go back one dir
                        currentDir = currentDir.parentDir;
                    } else {
                        // Change dir to specified dir
                        // Should probably use binary search here but not enough time to implement it.
                        for (Directory dir : currentDir.directories) {
                            if (dir.name.equals(command[2])) {
                                currentDir = dir;
                            }
                        }
                    }
                }
            }
            else {
                // It is either a dir or a file
                // Must add it to current dir
                String[] tempArray = s.split(" ");
                if (tempArray[0].equals("dir")) {
                    // It is a dir
                    currentDir.addDir(new Directory(currentDir, tempArray[1]));
                }
                else {
                    // It must be a file
                    currentDir.addFile(new FileX(tempArray[1], Integer.parseInt(tempArray[0])));
                }
            }
        }
    }

    private static Scanner newScanner() {
        try {
            return new Scanner(FILE);
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found. :(");
            throw new RuntimeException(e);
        }
    }

    private static class Directory {
        Directory(Directory parentDir, String name) {
            this.parentDir = parentDir;
            this.name = name;
        }
        String name;
        Directory parentDir;
        ArrayList<Directory> directories = new ArrayList<>();
        ArrayList<FileX> files = new ArrayList<>();

        private void addFile(FileX file) {
            this.files.add(file);
        }

        private void addDir(Directory directory) {
            this.directories.add(directory);
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    private static class FileX {
        public FileX(String name, int size) {
            this.name = name;
            this.size = size;
        }

        String name;
        int size;
    }
}
