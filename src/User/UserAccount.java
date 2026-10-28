
package User;

import java.util.ArrayList;

public class UserAccount {

    String email;
    String password;

    static ArrayList<UserAccount> users = new ArrayList<>();



    UserAccount(String email,String Pass){
    	this.email= email;
    	password = Pass;
    }
    
    
    // Register a new user
    public static boolean register(String email, String password) {

        // Check whether the email already exists
        for (UserAccount user : users) {
        	
        	if(user.email.equalsIgnoreCase(email)) {
        		return false;
        	}
        	
        }

        users.add(new UserAccount(email,password));
        return true;
    }

    
    
    
    // Verify login details
    public static boolean verify(String email, String password) {

        for (UserAccount user : users) {
            if (user.email.equalsIgnoreCase(email)
                    && user.password.equals(password)) {
                return true;
            }
        }

        return false;
    }



	
}
