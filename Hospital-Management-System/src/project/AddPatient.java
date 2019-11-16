//class used by the admin to update the details of a patient 

package project;

import java.io.*;
import java.util.*;
import javax.swing.*;

import project.Patient;
import project.UpdatePatient;

import java.awt.event.*;


class AddPatient extends UpdatePatient implements ActionListener{
	
	//constructor
	public AddPatient() {
		super(new Patient());
		setTitle("Add patient");
		update.setText("Add");
		tid.setEditable(true);
	}

	//**********************************************************************************************
	//method that adds a patient to the file

	public void printToFile(Object p) throws IOException{
		PrintWriter fout =  new PrintWriter (new FileWriter("patients.txt", true));//appends to the file
		fout.println(p);
		fout.close();
	}
	
	//**********************************************************************************************
	//method that checks if a patient ID exists

	public boolean checkID(int i) throws FileNotFoundException{
		Scanner fin =  new Scanner (new FileReader("patients.txt"));
		while (fin.hasNextLine()) {
			Scanner cin = new Scanner(fin.nextLine());
			String name = cin.next();
			int id =  cin.nextInt();
			cin.close();
			if (id == i)
				return true;
		}
		fin.close();
		return false;
	}

	//**********************************************************************************************
	//actionListener

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == update) { //actions for the add (update) Button
			int id =  Integer.parseInt(tid.getText());
			try {
				if (!checkID(id) && id > 0) {
					super.actionPerformed(e);
				}
				else 
					JOptionPane.showMessageDialog(null, "Please enter a positive and new ID" , "Invaild ID" , JOptionPane.ERROR_MESSAGE);
			}
			catch (FileNotFoundException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "patient's File Not Found" , "404" , JOptionPane.ERROR_MESSAGE);
			}
		}
		else { //action for the cancel Button
			super.actionPerformed(e);
		}
	}
}
