package User;

import java.awt.*;
import java.awt.event.*;

public class Dashboard extends Frame implements ActionListener {

    TextField name;
    TextArea description;
    Button submit, clear;
    Label message;

    Color navy = new Color(25, 45, 75);
    Color background = new Color(242, 245, 249);
    Color blue = new Color(40, 100, 180);

    public Dashboard() {
        setTitle("Police FIR Management System");
        setSize(750, 550);
        setLayout(new BorderLayout(15, 15));
        setBackground(background);

        // Header
        Panel header = new Panel(new GridLayout(2, 1));
        header.setBackground(navy);

        Label heading = new Label(
                "POLICE FIR MANAGEMENT SYSTEM", Label.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setForeground(Color.WHITE);

        Label subtitle = new Label(
                "Submit an issue for police review", Label.CENTER);
        subtitle.setForeground(Color.WHITE);

        header.add(heading);
        header.add(subtitle);
        add(header, BorderLayout.NORTH);

        // Form
        Panel form = new Panel(new GridLayout(4, 1, 10, 10));
        form.setBackground(Color.WHITE);

        Panel namePanel = new Panel(new BorderLayout(5, 5));
        namePanel.setBackground(Color.WHITE);
        namePanel.add(new Label("Your Name"), BorderLayout.NORTH);

        name = new TextField();
        namePanel.add(name, BorderLayout.CENTER);

        Panel descriptionPanel = new Panel(new BorderLayout(5, 5));
        descriptionPanel.setBackground(Color.WHITE);
        descriptionPanel.add(
                new Label("Issue Description"), BorderLayout.NORTH);

        description = new TextArea(5, 30);
        descriptionPanel.add(description, BorderLayout.CENTER);

        form.add(namePanel);
        form.add(descriptionPanel);

        Panel buttons = new Panel(new FlowLayout());
        buttons.setBackground(Color.WHITE);

        submit = new Button("Submit Issue");
        submit.setBackground(blue);
        submit.setForeground(Color.WHITE);

        clear = new Button("Clear Form");

        buttons.add(submit);
        buttons.add(clear);
        form.add(buttons);

        message = new Label("Please provide accurate information.");
        form.add(message);

        Panel outer = new Panel(new BorderLayout(20, 20));
        outer.setBackground(background);
        outer.add(form, BorderLayout.CENTER);

        add(outer, BorderLayout.CENTER);

        submit.addActionListener(this);
        clear.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submit) {
            String n = name.getText().trim();
            String d = description.getText().trim();

            if (n.isEmpty() || d.isEmpty()) {
                message.setText("Please fill in all fields.");
            } else {
                Issue issue = new Issue(n, d);
                IssueFile.addIssue(issue);

                message.setText("Issue submitted successfully!");

                name.setText("");
                description.setText("");
            }

        } else if (e.getSource() == clear) {
            name.setText("");
            description.setText("");
            message.setText("Form cleared.");
        }
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}