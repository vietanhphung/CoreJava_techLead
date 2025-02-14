import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class B9Test {
    public static void main(String[] args) {
        try {
            // Create a test file
            File testFile = new File("test.txt");
            FileWriter writer = new FileWriter(testFile);
            writer.write("hello world hello Java hello world");
            writer.close();

            // Run word count function
            System.out.println("Test Case 1: Word Count on Sample File");
            Map<String, Integer> result = B9.wordCount(testFile);

            // Display results
            for (Map.Entry<String, Integer> entry : result.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            // Expected output:
            // hello: 3
            // world: 2
            // Java: 1

            // Delete test file after use
            testFile.delete();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
