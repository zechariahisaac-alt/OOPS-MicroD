
package User;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Signup extends Frame implements ActionListener {

    TextField name, email, password;
    Button signup, back;
    
   

    public Signup() {

        setTitle("Sign Up Page");
        setSize(700, 450); 
        setLayout(new GridLayout(1, 1));
        setBackground(new Color(235, 240, 248));

        // Main panel fills the window and centers the form
        Panel mainPanel = new Panel(new GridBagLayout());
        mainPanel.setBackground(new Color(235, 240, 248));

        // Form panel
        Panel form = new Panel(new GridLayout(5, 1, 10, 15));
        form.setPreferredSize(new Dimension(450, 300));
        form.setBackground(Color.WHITE);

        // Title
        Panel p1 = new Panel(new FlowLayout(FlowLayout.CENTER));
        p1.setBackground(Color.WHITE);

        Label title = new Label("CREATE ACCOUNT");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(35, 55, 95));

        p1.add(title);
        form.add(p1);

        // Name
        Panel p2 = new Panel(new GridLayout(2, 1, 5, 5));
        p2.setBackground(Color.WHITE);

        Label nameLabel = new Label("Full Name");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setForeground(new Color(55, 65, 81));

        name = new TextField();
        name.setFont(new Font("Arial", Font.PLAIN, 16));
        name.setBackground(new Color(245, 247, 250));

        p2.add(nameLabel);
        p2.add(name);
        form.add(p2);

        // Email
        Panel p3 = new Panel(new GridLayout(2, 1, 5, 5));
        p3.setBackground(Color.WHITE);

        Label emailLabel = new Label("Email Address");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 16));
        emailLabel.setForeground(new Color(55, 65, 81));

        email = new TextField();
        email.setFont(new Font("Arial", Font.PLAIN, 16));
        email.setBackground(new Color(245, 247, 250));

        p3.add(emailLabel);
        p3.add(email);
        form.add(p3);

        // Password
        Panel p4 = new Panel(new GridLayout(2, 1, 5, 5));
        p4.setBackground(Color.WHITE);

        Label passwordLabel = new Label("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordLabel.setForeground(new Color(55, 65, 81));

        password = new TextField();
        password.setEchoChar('*');
        password.setFont(new Font("Arial", Font.PLAIN, 16));
        password.setBackground(new Color(245, 247, 250));

        p4.add(passwordLabel);
        p4.add(password);
        form.add(p4);

        // Buttons
        Panel p5 = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        p5.setBackground(Color.WHITE);

        signup = new Button("Create Account");
        signup.setFont(new Font("Arial", Font.BOLD, 16));
        signup.setBackground(new Color(35, 65, 125));
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);

        back = new Button("Back to Login");
        back.setFont(new Font("Arial", Font.BOLD, 16));
        back.setBackground(new Color(220, 225, 235));
        back.setForeground(new Color(35, 55, 95));
        back.addActionListener(this);

        p5.add(signup);
        p5.add(back);
        form.add(p5);

        // Center form in the window
        mainPanel.add(form);
        add(mainPanel);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    
    
//    main function of signup
    
    	public void actionPerformed(ActionEvent e) {

    	    if (e.getSource() == back) {
    	        new Login();
    	        dispose();
    	    }

    	    else if (e.getSource() == signup) {

    	        String userName = name.getText().trim();
    	        String userEmail = email.getText().trim();
    	        String userPassword = password.getText();

    	        if (userName.isEmpty()
    	                || userEmail.isEmpty()
    	                || userPassword.isEmpty()) {

    	            JOptionPane.showMessageDialog(
    	                this,
    	                "Please fill in all fields!",
    	                "Missing Information",
    	                JOptionPane.WARNING_MESSAGE
    	            );

    	        } 
    	        else if(userPassword.length()<6) {
    	        	 JOptionPane.showMessageDialog(
    	    	                this,
    	    	                "Passoword should be more than 6 characters",
    	    	                "Missing Information",
    	    	                JOptionPane.WARNING_MESSAGE
    	    	            );
    	        }
    	        else if(userName.length()<3) {
   	        	 JOptionPane.showMessageDialog(
   	    	                this,
   	    	                "Name should be more than 3 characters",
   	    	                "Missing Information",
   	    	                JOptionPane.WARNING_MESSAGE
   	    	            );
   	        }
    	        else if (UserAccount.register(userEmail, userPassword)) {

    	            JOptionPane.showMessageDialog(
    	                this,
    	                "Account created successfully!",
    	                "Signup Successful",
    	                JOptionPane.INFORMATION_MESSAGE
    	            );

    	            new Login();
    	            dispose();

    	        } else {

    	            JOptionPane.showMessageDialog(
    	                this,
    	                "This email is already registered!",
    	                "Signup Failed",
    	                JOptionPane.ERROR_MESSAGE
    	            );
    	        }
    	    }
    	
    }

    public static void main(String[] args) {
        new Signup();
    }
}
