package controller;

public class EmailUtility {
	 public static boolean sendPasswordResetEmail(String email) {
	        // Implementation for sending email
	        // For example, using JavaMail API
	        try {
	            // Email sending logic here
	            return true; // Return true if email is sent successfully
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
}
