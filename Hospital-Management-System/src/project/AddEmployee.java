package project;

import java.io.*;
import java.util.*;
import javax.swing.*;

import project.Employee;
import project.UpdateEmployee;

import java.awt.event.*;


class AddEmployee extends UpdateEmployee {

	//constructor
	public AddEmployee(ArrayList<Employee> employees) {
		super(new Employee());
		setTitle("Add employee");
		update.setText("Add");
		int lastemployee = employees.size() - 1;
		int newId = employees.get(lastemployee).getId() + 1;
		tid.setText(Integer.toString(newId));
	}

	//**********************************************************************************************
	//method that adds a patient to the file

	public void printToFile(Object c) throws IOException{
		PrintWriter fout =  new PrintWriter (new FileWriter("employee.txt", true));//appends to the file
		fout.println(c);
		fout.close();
	}

	//**********************************************************************************************
	//actionListener

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == update) { //actions for the add (update) Button
			String user =  tname.getText();
			try {
				if (!checkUserExists(user) && !user.equals("")) {
					super.actionPerformed(e);
				}
				else 
					JOptionPane.showMessageDialog(null, "Please enter a new name" , "Invaild Name" , JOptionPane.ERROR_MESSAGE);
			}
			catch (FileNotFoundException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "employee File Not Found" , "404" , JOptionPane.ERROR_MESSAGE);
			}
		}
		else { //action for the cancel Button
			super.actionPerformed(e);
		}
	}
}
