
package Police;

import java.awt.*;
import java.awt.event.*;
import User.Issue;
import User.IssueFile;

public class PoliceDashboard extends Frame implements ActionListener {

    List issueList;
    TextArea details;
    Checkbox completed, unable, pending;
    Button update, refresh;

    Color navy = new Color(25, 45, 75);
    Color background = new Color(242, 245, 249);

    public PoliceDashboard() {
        setTitle("Police Dashboard - FIR Management");
        setSize(850, 550);
        setLayout(new BorderLayout(10, 10));
        setBackground(background);

        // Header
        Label heading = new Label(
                "POLICE DASHBOARD - ALL FIRs", Label.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setBackground(navy);
        heading.setForeground(Color.WHITE);

        add(heading, BorderLayout.NORTH);

        // Left side: list of issues
        issueList = new List();
        add(issueList, BorderLayout.WEST);

        // Center: selected issue details
        details = new TextArea();
        details.setEditable(false);
        details.setFont(new Font("Arial", Font.PLAIN, 15));
        add(details, BorderLayout.CENTER);

        // Bottom: status controls
        Panel bottom = new Panel(new GridLayout(2, 1, 5, 5));

        CheckboxGroup statusGroup = new CheckboxGroup();

        pending = new Checkbox("Pending", statusGroup, true);
        completed = new Checkbox("Completed", statusGroup, false);
        unable = new Checkbox("Unable to Solve", statusGroup, false);

        Panel choices = new Panel();
        choices.add(pending);
        choices.add(completed);
        choices.add(unable);

        bottom.add(choices);

        Panel buttons = new Panel();

        update = new Button("Update Status");
        refresh = new Button("Refresh Issues");

        buttons.add(update);
        buttons.add(refresh);

        bottom.add(buttons);

        add(bottom, BorderLayout.SOUTH);

        // Events
        issueList.addItemListener(e -> showSelectedIssue());

        update.addActionListener(this);
        refresh.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        loadIssues();
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    // Load issues from the shared list
    void loadIssues() {
        IssueFile.loadIssues();
        issueList.removeAll();

        for (int i = 0; i < IssueFile.issues.size(); i++) {
            Issue issue = IssueFile.issues.get(i);

            issueList.add(
                    (i + 1) + ". " + issue.name
                    + " - " + issue.status
            );
        }

        details.setText("Select an issue to view its details.");
    }

    // Display selected issue
    void showSelectedIssue() {
        int index = issueList.getSelectedIndex();

        if (index >= 0 && index < IssueFile.issues.size()) {
            Issue issue = IssueFile.issues.get(index);

            details.setText(
                    "Complainant: " + issue.name
                    + "\n\nIssue Description:\n"
                    + issue.description
                    + "\n\nCurrent Status: " + issue.status
            );

            pending.setState(issue.status.equals("Pending"));
            completed.setState(issue.status.equals("Completed"));
            unable.setState(issue.status.equals("Unable to Solve"));
        }
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == update) {

            int index = issueList.getSelectedIndex();

            if (index < 0) {
                details.setText("Please select an issue first.");
                return;
            }

            Issue issue = IssueFile.issues.get(index);

            if (pending.getState()) {
                issue.status = "Pending";
            } else if (completed.getState()) {
                issue.status = "Completed";
            } else if (unable.getState()) {
                issue.status = "Unable to Solve";
            }

            // Save updated status permanently
            IssueFile.saveAllIssues();

            loadIssues();
            issueList.select(index);
            showSelectedIssue();

        } else if (e.getSource() == refresh) {
            loadIssues();
        }
    }

    public static void main(String[] args) {
        new PoliceDashboard();
    }
}
