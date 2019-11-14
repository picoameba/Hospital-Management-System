package project;
import java.io.*;
import java.util.*; 
import javax.swing.*;

import project.Admin.UpdateEmployees;

import java.awt.event.*;
public class Admin {

	private String name; 
	private int id;

	private String pass;
	
	
	public String getName() {
		return name; 
	}
	
	public ​int​ getId() { 
		return​ id;
	}
	public​ String getPass() { 
		return​ pass;
	}

	public​ Admin(String name, ​int​ id, String pass) {
		this​.name = name; this​.id = id; this​.pass = pass;
	}
}













class AddEmployees extends UpdateEmployees {
	//constructor
	public AddEmployees(ArrayList<Employees> Employees) {
		super(new Employees());
		setTitle("Add Employee");
		update.setText("Add");
		int lastEmployee = Employees.size() - 1;
		int newId = Employees.get(lastEmployee).getId() + 1; tid.setText(Integer.toString(newId));
	}
	//************************************************************************************** ********

	private void setTitle(String string) {
		// TODO Auto-generated method stub

	}

	//method that adds a product to the file
	public void printToFile(Object c) throws IOException{
		PrintWriter fout = new PrintWriter (new FileWriter("Employees.txt", true));//appends to the file fout.println(c);
		fout.close();
	}
	//actionListener
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == update) { //actions for the add (update) Button
			String user = tname.getText(); try {
				if (!checkUserExists(user) && !user.equals("")) { super.actionPerformed(e);
				} else
					JOptionPane.showMessageDialog(null, "Please enter a new name" , "Invaild Name" , JOptionPane.ERROR_MESSAGE);
			}
			28
			catch (FileNotFoundException e1) { e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Cashier File Not Found" , "404" , JOptionPane.ERROR_MESSAGE);
			} }
		else { //action for the cancel Button super.actionPerformed(e);
		} 
	}
}

public class UpdateEmployees {

	public UpdateEmployees(Employees employees) {
		// TODO Auto-generated constructor stub
	}

}

