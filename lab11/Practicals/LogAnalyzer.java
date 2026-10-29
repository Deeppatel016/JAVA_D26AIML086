import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.*;
import java.util.*;

public class LogAnalyzer {
    public static void main(String[] args) throws IOException {

        String[] files = {"log1.txt", "log2.txt", "log3.txt"};
        String[] data = {
            "Error found\nLogin successful",
            "Error occurred\nWarning found",
            "Login successful\nError found"
        };

        String keyword = "Error";
        int totalLines = 0, matches = 0;

        for (int i = 0; i < files.length; i++)
            Files.writeString(Path.of(files[i]), data[i]);

        for (String file : files) {
            Path path = Path.of(file);
            int fileLines = 0;

            try (Scanner sc = new Scanner(path)) {
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    fileLines++;

                    if (line.contains(keyword))
                        matches++;
                }
            }

            totalLines += fileLines;

            BasicFileAttributes attr =
                    Files.readAttributes(path, BasicFileAttributes.class);

            System.out.println(file + " size: " + attr.size() + " bytes");
        }

        System.out.println("Total lines: " + totalLines);
        System.out.println("Lines containing '" + keyword + "': " + matches);
    }
}