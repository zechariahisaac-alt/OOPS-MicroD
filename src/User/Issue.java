package User;

public class Issue {

    public String name;
    public String description;
    public String status;

    public Issue(String name, String description) {
        this.name = name;
        this.description = description;
        this.status = "Pending";
    }

    public Issue(String name, String description, String status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public String toString() {
        return "Name: " + name
                + "\nIssue: " + description
                + "\nStatus: " + status
                + "\n--------------------------\n";
    }
}