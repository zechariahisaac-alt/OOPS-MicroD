
package User;

import java.io.*;
import java.util.ArrayList;

public class IssueFile {

    private static final String FILE_NAME = "issues.txt";

    public static ArrayList<Issue> issues = new ArrayList<>();

    static {
        loadIssues();
    }

    // Save a new issue
    public static void addIssue(Issue issue) {
        issues.add(issue);
        saveAllIssues();
    }

    // Save all issues to the text file
    public static void saveAllIssues() {
        try {
            FileWriter writer = new FileWriter(FILE_NAME);

            for (Issue issue : issues) {
                writer.write(issue.name + "\n");
                writer.write(issue.description + "\n");
                writer.write(issue.status + "\n");
                writer.write("--------------------\n");
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving issues: " + e.getMessage());
        }
    }

    // Read issues from the text file
    public static void loadIssues() {
        issues.clear();

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try {
            BufferedReader reader =
                    new BufferedReader(new FileReader(FILE_NAME));

            String name;

            while ((name = reader.readLine()) != null) {
                String description = reader.readLine();
                String status = reader.readLine();
                String separator = reader.readLine();

                if (description == null || status == null) {
                    break;
                }

                issues.add(new Issue(name, description, status));
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Error reading issues: " + e.getMessage());
        }
    }
}
