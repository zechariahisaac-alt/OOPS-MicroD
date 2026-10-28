
package User;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import Police.PoliceDashboard;

public class Login extends Frame implements ActionListener {

    TextField email, password;
    Button login, signup;

    public Login() {

        setTitle("Login Page");
        setSize(700, 450); // Keeping your original size
        setLayout(new GridLayout(1, 1));
        setBackground(new Color(235, 240, 248));

        // Main panel
        Panel mainPanel = new Panel(new GridBagLayout());
        mainPanel.setBackground(new Color(235, 240, 248));

        // Login form
        Panel form = new Panel(new GridLayout(4, 1, 10, 10));
        form.setPreferredSize(new Dimension(400, 230));
        form.setBackground(Color.WHITE);

        // Title
        Panel p1 = new Panel(new FlowLayout(FlowLayout.CENTER));
        p1.setBackground(Color.WHITE);

        Label title = new Label("LOGIN");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(35, 55, 95));

        p1.add(title);
        form.add(p1);

        // Email
        Panel p2 = new Panel(new GridLayout(2, 1, 5, 5));
        p2.setBackground(Color.WHITE);

        Label emailLabel = new Label("Email Address");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        emailLabel.setForeground(new Color(55, 65, 81));

        email = new TextField();
        email.setFont(new Font("Arial", Font.PLAIN, 14));
        email.setBackground(new Color(245, 247, 250));

        p2.add(emailLabel);
        p2.add(email);
        form.add(p2);

        // Password
        Panel p3 = new Panel(new GridLayout(2, 1, 5, 5));
        p3.setBackground(Color.WHITE);

        Label passwordLabel = new Label("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passwordLabel.setForeground(new Color(55, 65, 81));

        password = new TextField();
        password.setEchoChar('*');
        password.setFont(new Font("Arial", Font.PLAIN, 14));
        password.setBackground(new Color(245, 247, 250));

        p3.add(passwordLabel);
        p3.add(password);
        form.add(p3);

        // Buttons
        Panel p4 = new Panel(new FlowLayout(FlowLayout.CENTER, 10, 8));
        p4.setBackground(Color.WHITE);

        login = new Button("Login");
        login.setFont(new Font("Arial", Font.BOLD, 14));
        login.setBackground(new Color(35, 65, 125));
        login.setForeground(Color.WHITE);
        login.addActionListener(this);

        signup = new Button("Create Account");
        signup.setFont(new Font("Arial", Font.BOLD, 14));
        signup.setBackground(new Color(120, 225, 235));
        signup.setForeground(new Color(35, 55, 95));
        signup.addActionListener(this);

        p4.add(login);
        p4.add(signup);
        form.add(p4);

        // Center the form
        mainPanel.add(form);
        add(mainPanel);

        // Close window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

    	
    	
    	if (e.getSource() == login) {

    	    String userEmail = email.getText().trim();
    	    String userPassword = password.getText();

    	    if (UserAccount.verify(userEmail, userPassword)) {

    	        JOptionPane.showMessageDialog(
    	            this,
    	            "Login Successful!",
    	            "Success",
    	            JOptionPane.INFORMATION_MESSAGE
    	        );
    	        if(userEmail.startsWith("police")) {
    	        	new PoliceDashboard();
    	        	dispose();
    	        }
    	        else {
    	        	new Dashboard();
    	        	dispose();
    	        }

    	    } else {

    	        JOptionPane.showMessageDialog(
    	            this,
    	            "Invalid email or password!",
    	            "Login Failed",
    	            JOptionPane.ERROR_MESSAGE
    	        );
    	    }
    	}
    	
    	if(e.getSource() == signup) {
    			
    		new Signup();
    		dispose();
    	}
        
        
    }

    public static void main(String[] args) {
        new Login();
    }
}
