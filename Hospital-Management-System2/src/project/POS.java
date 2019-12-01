package project;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class POS {

	private static Admin activeAccount;

	//***************************************************************
	//getter method
	public static Admin getActiveAccount() {
		return activeAccount;
	}

	//***************************************************************
	//checks if entered credentials are valid
	
	public static void authenticate(String access, String user, String pass) throws FileNotFoundException {

		//opens the correct file
		Scanner fin;
		if (access.equals("Admin")) {
			fin = new Scanner (new FileReader("Admin.txt"));
		}
		else {
			fin = new Scanner (new FileReader("employee.txt"));
		} 

		//***************************************************************
		//attempts to find user, and creates options frame accordingly

		boolean isFound = false;	//flag to check if user is found
		while(!isFound && fin.hasNext()) {
			if (user.equals(fin.next())) {
				isFound = true;	//user is found
				int id = fin.nextInt();

				if (pass.equals(fin.next())) {
					if (access == "Admin") {
						activeAccount = new Admin(user, id, pass);
						//creates active account from text file data
					}
					else {
						activeAccount = new EmployeeLogin(user, id, pass);
						//creates active account from text file data
					}
					new Options(true);
				}
				else {
					activeAccount = null;
					JOptionPane.showMessageDialog(null, "Incorrect Password", "Error: Wrong Password",
							JOptionPane.ERROR_MESSAGE);
					new Options(true);
				}
			}
			else {	//user not in this line of text file
				fin.nextLine();	//skips to next line
			}
		}//end while

		if(!isFound) {
			activeAccount = null;
			JOptionPane.showMessageDialog(null, "Invalid User", "Error: User Not Found",
					JOptionPane.ERROR_MESSAGE);
			new Options(false);
		}

		fin.close();
		//closes scanner

	}
	//**********************************************************************************************
	//main method

	public static void main(String[] args) throws IOException{
		new PrintWriter("Temp.txt").close();//empty the temp file
		new LogIn();
	}

}
